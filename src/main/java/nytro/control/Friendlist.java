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


@WebServlet("/Friendlist")
public class Friendlist extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	private IAccountDAO accountDAO = new AccountDAO();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");		
		
		String futuroAmico = request.getParameter("futuroAmico");
		if(futuroAmico!=null && !futuroAmico.equals("")) {
			try {
				accountDAO.doAggiungiAmicoFriendlist(account, futuroAmico);
			} catch (SQLException e) {
				;
			}
		}
		
		String eliminatoAmico = request.getParameter("eliminatoAmico");
		if(eliminatoAmico!=null && !eliminatoAmico.equals("")) {			
			try {
				accountDAO.doRimuoviAmicoFriendlist(account, eliminatoAmico);
			} catch (SQLException e) {
				;
			}
		}
		
		Collection<AccountBean> amici = null;
		Boolean possessori = false;
		try {
			amici = accountDAO.doRetrieveAllFriendsByUsername(account.getUsername());		
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		String possessedutoAmici=request.getParameter("possessedutoAmici");
		if(possessedutoAmici!=null && !possessedutoAmici.equals("")) {
			try {
				String codiceVideogioco=request.getParameter("codiceVideogioco");
				amici = accountDAO.doRetrieveAllFriendsByVideogioco(account, Integer.parseInt(codiceVideogioco));
				possessori = true;
			} catch (SQLException e) {
				throw new MyException("Errore estrazione videogiochi.");
			}
		}
		
		request.removeAttribute("amici");
		request.setAttribute("amici", amici);
		request.setAttribute("possessori", possessori);

		String url = response.encodeURL("jsp/friendlist.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
