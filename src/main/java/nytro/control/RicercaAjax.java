package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.idao.IVideogiocoDAO;
import org.json.JSONArray;

import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;

@WebServlet("/RicercaAjax")
public class RicercaAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray videogiochiJson = new JSONArray();
		String query = request.getParameter("testoParziale");
		List<VideogiocoBean> videogiochi=null;
		if (query != null) {			
			try {
				videogiochi = videogiocoDAO.doRetrieveByTitolo(query);
			} catch (SQLException e) {
				;
			}
			for (VideogiocoBean x : videogiochi) {
				System.out.println("Inserisco in videogiochiJson: " + x.getTitolo());
				videogiochiJson.put(x.getTitolo());
			}
		}
		System.out.println("videogiochiJson.toString():\t"+videogiochiJson.toString());
		response.setContentType("application/json");
		response.getWriter().append(videogiochiJson.toString());
		//Non forward/redirecto in quanto viene chiamata via AJAX
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
