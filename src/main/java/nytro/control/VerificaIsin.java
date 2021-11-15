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

@WebServlet("/VerificaIsin")
public class VerificaIsin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IAccountDAO accountDAO = new AccountDAO();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String isin = request.getParameter("isin");
		response.setContentType("text/xml");
		AccountBean bean;
		if (isin != null && isin.length() >= 6 && isin.matches("^\\w{12}$")) {
			try {
				bean = accountDAO.doRetrieveByIsin(isin);
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
