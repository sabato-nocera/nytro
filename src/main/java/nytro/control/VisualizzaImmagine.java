package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.dao.AccountDAO;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IVideogiocoDAO;

/**
 * Servlet implementation class VisualizzaImmagine
 */
@WebServlet("/image")
public class VisualizzaImmagine extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static IAccountDAO accountDao = new AccountDAO();
	private static IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	public static void setAccountDao(IAccountDAO accountDao) {
		VisualizzaImmagine.accountDao = accountDao;
	}

	public static void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
		VisualizzaImmagine.videogiocoDAO = videogiocoDAO;
	}


    public VisualizzaImmagine() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = (String) request.getParameter("id");
        String codice = (String) request.getParameter("codice");
        
        if (id != null) {
	        try {
	        	byte[] image = accountDao.doDisplayImage(id);
	        	if (image != null) {
	                response.setContentLength(image.length);
	                response.getOutputStream().write(image);
	                response.setContentType("image/jpg");
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            } 
	        	response.getOutputStream().close();
	        }catch (SQLException e) {
	            throw new ServletException("Something failed at SQL/DB level.", e);
	        }
        } else if (codice != null) {
	        try {
	        	int code = Integer.parseInt(codice);
	        	byte[] image = videogiocoDAO.doDisplayImage(code);
	        	if (image != null) {
	                response.setContentLength(image.length);
	                response.getOutputStream().write(image);
	                response.setContentType("image/jpg");
	            } else {
	                response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
	            } 
	        	response.getOutputStream().close();
	        }catch (SQLException e) {
	            throw new ServletException("Something failed at SQL/DB level.", e);
	        }
        }
    }	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
