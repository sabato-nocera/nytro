package nytro.control;

import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet("/RicercaGiocatoreAjax")
public class RicercaGiocatoreAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private IAccountDAO accountDAO = new AccountDAO();

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray giocatoriJson = new JSONArray();
		String testoParziale = request.getParameter("futuroAmico");
		Collection<AccountBean> giocatori =null;
		if (testoParziale != null && !testoParziale.equals("")) {
			try {
				giocatori = accountDAO.doRetrieveAll("giocatore.Username", 1);
			} catch (SQLException e) {
				;
			}
			if(giocatori!=null){
				for (AccountBean account : giocatori) {
					String username = account.getUsername();
					if(username.contains(testoParziale.toLowerCase())) {
						System.out.println("Inserisco in giocatoriJson: " + username);
						giocatoriJson.put(username);
					}
				}
			}
		}
		System.out.println("giocatoriJson.toString():\t"+giocatoriJson.toString());
		response.setContentType("application/json");
		response.getWriter().append(giocatoriJson.toString());
		//Non forward/redirecto in quanto viene chiamata via AJAX
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
