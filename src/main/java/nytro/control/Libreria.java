package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IVideogiocoDAO;

@WebServlet("/Libreria")
public class Libreria extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
		this.videogiocoDAO = videogiocoDAO;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		Collection<VideogiocoBean> libreria = null;
		
		String cancellaVideogioco=request.getParameter("cancellaVideogioco");
		if(cancellaVideogioco!=null && !cancellaVideogioco.equals("")) {
			int codiceVideogiocoDaCancellare = Integer.parseInt(cancellaVideogioco);
			try {
				videogiocoDAO.doDeleteFromLibreria(account.getUsername(), codiceVideogiocoDaCancellare);
				libreria = videogiocoDAO.doRetrieveAllLibreria(account.getUsername(), null);
				String url = response.encodeURL("/NYTRO/Libreria");
				response.sendRedirect(url);
				return;
			} catch (SQLException e) {
				throw new MyException("Errore cancellazione videogioco.");
			}
		} 
		
		String aggiungiVideogioco=request.getParameter("aggiungiVideogioco");
		if(aggiungiVideogioco!=null && !aggiungiVideogioco.equals("")) {
			int codiceVideogiocoDaAggiungere = Integer.parseInt(aggiungiVideogioco);
			try {
				videogiocoDAO.doSaveToLibreria(account.getUsername(), codiceVideogiocoDaAggiungere);
				libreria = videogiocoDAO.doRetrieveAllLibreria(account.getUsername(), null);
				String url = response.encodeURL("/NYTRO/Libreria");
				response.sendRedirect(url);
				return;
			} catch (SQLException e) {
				throw new MyException("Errore inserimento videogioco.");
			}
		}
		
		String order = request.getParameter("order");

		try {
			libreria = videogiocoDAO.doRetrieveAllLibreria(account.getUsername(), order);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		String libreriaAmicoDaVisualizzare=request.getParameter("libreriaAmicoDaVisualizzare");
		if(libreriaAmicoDaVisualizzare!=null && !libreriaAmicoDaVisualizzare.equals("")) {
			try {
				libreria = videogiocoDAO.doRetrieveAllLibreria(libreriaAmicoDaVisualizzare, order);
				request.setAttribute("libreriaAmicoDaVisualizzare", libreriaAmicoDaVisualizzare);
			} catch (SQLException e) {
				throw new MyException("Errore estrazione videogiochi.");
			}
		}

		Collection<Map> acquisti = null;

		try {
			acquisti = videogiocoDAO.doRetrieveAllAcqusiti(account.getUsername(), order);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}

		request.setAttribute("acquisti", acquisti);
			
		request.setAttribute("libreria", libreria);
		
		String url = response.encodeURL("jsp/libreria.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
