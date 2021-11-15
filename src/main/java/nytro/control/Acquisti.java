package nytro.control;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IVideogiocoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;

@WebServlet("/Acquisti")
public class Acquisti extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
		this.videogiocoDAO=videogiocoDAO;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		Collection<Map> acquisti = null;

		String order = request.getParameter("order");

		try {
			acquisti = videogiocoDAO.doRetrieveAllAcqusiti(account.getUsername(), order);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}

		request.setAttribute("acquisti", acquisti);
		
		String url = response.encodeURL("jsp/acquisti.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
