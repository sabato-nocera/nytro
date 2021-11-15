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

@WebServlet("/CatalogoCasaEditrice")
public class CatalogoCasaEditrice extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
		this.videogiocoDAO = videogiocoDAO;
	}

	private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
		String order = request.getParameter("order");
		String isinCasaEditrice = request.getParameter("isinCasaEditrice");
		
		ArrayList<String> generiPresenti = new ArrayList<String>();
		
		Collection<VideogiocoBean> catalogoCasaEditrice = null, catalogoTmp = null;
		
		try {
			catalogoCasaEditrice = videogiocoDAO.doRetrieveAll(order, isinCasaEditrice);
			catalogoTmp = videogiocoDAO.doRetrieveAll(order, isinCasaEditrice);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		for(VideogiocoBean x : catalogoTmp) {
			if(x.getDataRimozione()!=null)
				catalogoCasaEditrice.remove(x);
		}
		
		Collection<VideogiocoBean> catalogoRichiesto = new LinkedList<VideogiocoBean>();
		
		String genere = request.getParameter("genere");
		if(genere!=null && !genere.equals("")) {
			for(VideogiocoBean x : catalogoCasaEditrice) {
				List<String> tmp = x.getGeneri();
				if(tmp!=null){
					for(String y : tmp)
						if(y.toLowerCase().equalsIgnoreCase(genere))
							catalogoRichiesto.add(x);
				}
			}
		} else {
			catalogoRichiesto = catalogoCasaEditrice;
		}
		
		String nome = isinCasaEditrice;
		
		for(VideogiocoBean x : catalogoRichiesto) {
			try {
				List<String> tmp = x.getGeneri();
				nome = videogiocoDAO.doRetrieveCasaByCodice(x.getCodice());
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
		
		request.setAttribute("isinCasaEditrice", isinCasaEditrice);
		request.setAttribute("generiPresenti", generiPresenti);
		request.setAttribute("catalogoCasaEditrice", catalogoRichiesto);
		request.setAttribute("nomeCasaEditrice", nome);

		String url = response.encodeURL("jsp/catalogoCasaEditrice.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
