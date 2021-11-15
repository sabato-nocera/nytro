package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.model.dao.AccountDAO;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;
import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	private static final long serialVersionUID = 8573498926977710169L;
	private IAccountDAO accountDAO = new AccountDAO();

	public void setAccountDAO(IAccountDAO accountDAO){this.accountDAO = accountDAO;}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(password!= null){
			password= Utils.generatePwd(password);
		}
		AccountBean account = new AccountBean();
		System.out.println(username + " " + password);
		if (username != null && password != null) {
			try {
				account = accountDAO.doRetrieveByUsernamePassword(username, password);
			} catch (SQLException e) {
				throw new MyException("Username e/o password vuoti.");
			}
		}

		if (account.getUsername()==null) {						//Controlla che il campo username sia null per indicare che l'utente e' assente
			throw new MyException("Username e/o password non validi.");
		}

		try {
			account.setIp(request.getRemoteAddr());
			accountDAO.doUpdate(account);
		} catch (SQLException e) {
			throw new MyException("Fallimento aggiornamento informazioni ultimo accesso");
		}		
		
		try {
			account = accountDAO.doRetrieveByUsernamePassword(username, password);	//per avere le informazioni circa data e ora aggiornate
		} catch (SQLException e) {
			throw new MyException("Username e/o password vuoti.");
		}
		request.getSession().setAttribute("account", account);
		
		if(account.getRuolo() == 1) {
			try {
				GiocatoreBean x = accountDAO.doRetrieveGiocatore(account);
				request.removeAttribute("account");
				request.getSession().setAttribute("account", x);
				System.out.println("Giocatore settato nella request");
			} catch (SQLException e) {
				throw new MyException("Errore giocatore");
			}
		}
		if(account.getRuolo() == 2) {
			try {
				CasaEditriceBean x = accountDAO.doRetrieveCasaEditrice(account);
				request.removeAttribute("account");
				request.getSession().setAttribute("account", x);
				System.out.println("Casa editrice settata nella request");
			} catch (SQLException e) {
				throw new MyException("Errore casa editrice");
			}
		}

	
		String url = response.encodeURL("jsp/index.jsp");
		response.sendRedirect(url);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
