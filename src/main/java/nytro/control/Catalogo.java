package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

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


@WebServlet("/Catalogo")
public class Catalogo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
		this.videogiocoDAO = videogiocoDAO;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
		ArrayList<String> generiPresenti = new ArrayList<String>();
		
		String order = request.getParameter("order");
		
		Collection<VideogiocoBean> catalogo = null;
		Collection<VideogiocoBean> catalogoTmp = null;
		
		try {
			catalogo = videogiocoDAO.doRetrieveAll(order, "");
			catalogoTmp = videogiocoDAO.doRetrieveAll(order, "");
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		for(VideogiocoBean x : catalogoTmp) {
			if(x.getDataRimozione()!=null) {
				catalogo.remove(x);
			}
		}
		
		Collection<VideogiocoBean> catalogoRichiesto = new LinkedList<VideogiocoBean>();
		
		String genere = request.getParameter("genere");
		if(genere!=null && !genere.equals("")) {
			for(VideogiocoBean x : catalogo) {
				List<String> tmp = x.getGeneri();
				if(tmp!=null){
					for(String y : tmp)
						if(y.toLowerCase().equalsIgnoreCase(genere))
							catalogoRichiesto.add(x);
				}
			}
		} else {
			catalogoRichiesto = catalogo;
		}
		
		List<String> nome = new ArrayList<String>();
		
		for(VideogiocoBean x : catalogoRichiesto) {
			try {
				nome.add(videogiocoDAO.doRetrieveCasaByCodice(x.getCodice()));
				List<String> tmp = x.getGeneri();
				if(tmp!=null){
					for(String y : tmp) {
						if(!generiPresenti.contains(y.toLowerCase()))
							generiPresenti.add(y.toLowerCase());
					}
				}
			} catch (SQLException e) {
				;
			}
		}
		
		request.setAttribute("generiPresenti", generiPresenti);
		request.setAttribute("catalogo", catalogoRichiesto);
		request.setAttribute("nomeCasa", nome);

		String url = response.encodeURL("jsp/catalogo.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
