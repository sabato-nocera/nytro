package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;

@WebServlet("/RecuperaPassword")
public class RecuperaPassword extends HttpServlet {
	
	private static final long serialVersionUID = 9456335;

	private IAccountDAO accountDAO = new AccountDAO();

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String emailRecupero = request.getParameter("emailRecupero");
		String password = request.getParameter("password");
		String passwordConferma = request.getParameter("passwordConferma");
		
		AccountBean account = null;
		System.out.println(username);
		
		if(username==null || emailRecupero==null || username.equals("") || emailRecupero.equals("") || password==null || passwordConferma==null || password.equals("") || passwordConferma.equals(""))
			throw new MyException("Username e/o email di recupero vuota.");
		
		if(!password.equals(passwordConferma))
			throw new MyException("Campo password differente dal campo password conferma.");
		
		try {
			account = accountDAO.doRetrieveByUsername(username);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione account.");
		}
		
		if(account.getUsername()==null || account.getUsername().equals(""))
			throw new MyException("Account assente");

		if(!account.getEmailRecupero().equals(emailRecupero))
			throw new MyException("Email di recupero inserita non valida: " + account.getEmailRecupero() +" " + emailRecupero);

		password = Utils.generatePwd(password);
        account.setPassword(password);
		
		try {
			accountDAO.doUpdate(account);
		} catch (SQLException e) {
			throw new MyException("Fallimento cambiamento password");
		}
		
		String message="Password aggiornata con successo.";
		
		request.setAttribute("message", message);

		String url = response.encodeURL("jsp/loginForm.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)	throws ServletException, IOException {
		doGet(request, response);
	}

}
