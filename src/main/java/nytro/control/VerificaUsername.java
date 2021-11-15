package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;

@WebServlet("/VerificaUsername")
public class VerificaUsername extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IAccountDAO accountDAO = new AccountDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		response.setContentType("text/xml");
		AccountBean bean;
		if (username != null && username.length() >= 6 && username.matches("^[0-9a-zA-Z]+$")) {
			try {
				bean = accountDAO.doRetrieveByUsername(username);
				if(bean.getUsername() == null) {
					response.getWriter().append("<ok/>");
					
				} else {
					response.getWriter().append("<no/>");
				}
				
			} catch (SQLException e) {
				response.getWriter().append("<no/>");
			}

		} else {
			response.getWriter().append("<no/>");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
