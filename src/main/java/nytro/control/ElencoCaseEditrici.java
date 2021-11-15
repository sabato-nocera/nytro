package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;

@WebServlet("/ElencoCaseEditrici")
public class ElencoCaseEditrici extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	private IAccountDAO accountDAO = new AccountDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String order = request.getParameter("order");
		
		Collection<AccountBean> caseEditrici = null;
		
		try {
			caseEditrici = accountDAO.doRetrieveAll(order, 2);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione case editrici.");
		}
		
		request.setAttribute("caseEditrici", caseEditrici);

		String url = response.encodeURL("jsp/elencoCaseEditrici.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
