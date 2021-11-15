package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.dao.AccountDAO;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;

/**
 * Servlet implementation class RegistrazioneCasaEditrice
 */
@WebServlet("/RegistrazioneCasaEditrice")
@MultipartConfig(maxFileSize = 16177215)
public class RegistrazioneCasaEditrice extends HttpServlet {
	private static final long serialVersionUID = -5219907705325033810L;

	private IAccountDAO accountDAO = new AccountDAO();

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CasaEditriceBean utente = new CasaEditriceBean();
		String nomeAzienda = request.getParameter("nomeCasaEditrice");
		String username = request.getParameter("username");
		String ceo = request.getParameter("nomeCEO");
		String isin = request.getParameter("ISIN");
		String password = request.getParameter("password");
		String passwordConf = request.getParameter("passwordConferma");
		String email = request.getParameter("email");
		String emailRec = request.getParameter("emailRec");
		String phone = request.getParameter("phone");
		String website = request.getParameter("sitoWeb");
		boolean usernameOk, passwordOk, emailOk, emailRecOk, photoOk, nameOk, isinOk, siteOk, ceoOk;
		
		utente.setUsername(username);
		System.out.println("Questo e' lo username : " + username);
		
		if(nomeAzienda.equals("") || nomeAzienda == null) {
			nameOk = false;
		} else nameOk = true;
		
		if(!isin.matches("^\\w{12}$") || isin.equals("") || isin == null) {
			isinOk = false;
		} else isinOk = true;
		
		if(ceo.equals("") || ceo == null) {
			ceoOk = false;
		} else ceoOk = true;
		
		//if(!website.matches("^(?:http(s)?:\\/\\/)?[\\w.-]+(?:\\.[\\w\\.-]+)+[\\w\\-\\._~:/?#[\\]@!\\$&\\(\\)\\*\\+,;=.]+$") || website.equals("") || website == null) {
		if(website.equals("") || website == null) {
			siteOk = false;
		} else siteOk = true;
		
		if(!username.matches("^[0-9a-zA-Z]+$") || username == null || username.equals("") || username.length() < 6) {
			usernameOk = false;
		} else usernameOk = true;

		if(password == null || password.equals("") || passwordConf == null || passwordConf.equals("") || !password.equals(passwordConf)
			|| password.length() < 8 || password.toUpperCase().equals("password") || password.toLowerCase().equals("password")) {
			passwordOk = false;
		} else {
			passwordOk = true;
			password = Utils.generatePwd(password);
		}
		
		if(!email.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$") || email == null || email.equals("")) {
			emailOk = false;
		} else emailOk = true;
		
		if(!emailRec.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$") || emailRec == null || emailRec.equals("")) {
			emailRecOk = false;
		} else emailRecOk = true;
		
		if(phone!= null && !phone.matches("^\\d{10}$") || phone.isEmpty()) {
			if (phone.length() > 0 && phone.length() < 10) {
				throw new MyException("Lunghezza cellulare sbagliata, diversa da 10");
			}
			phone = null;
		}

		if(usernameOk && passwordOk && emailOk && emailRecOk && nameOk && isinOk && ceoOk && siteOk) {
			utente.setPassword(password);
			utente.setEmail(email);
			utente.setEmailRecupero(emailRec);
			utente.setCellulare(phone);
			utente.setRuolo(2);
			utente.setCEO(ceo);
			utente.setISIN(isin);
			if(request.getPart("photo")!=null){
				utente.setImgProfilo(request.getPart("photo").getInputStream());
			} else {
				utente.setImgProfilo(null);
			}
			utente.setNomeCasaEditrice(nomeAzienda);
			utente.setSitoWeb(website);
			utente.setIp(request.getRemoteAddr());

			try {
				accountDAO.doSaveCasaEditrice(utente);
				if (request.getPart("photo") != null && request.getPart("photo").getSize() > 0
				&& Utils.checkImg(request.getPart("photo").getSubmittedFileName()))
					accountDAO.doUploadImage(utente);
				String url = response.encodeURL("jsp/index.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			} catch(SQLException exception) {
				String url = response.encodeURL("jsp/registrazioneCasaEditrice.jsp");
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		} else {
			String url = response.encodeURL("jsp/registrazioneCasaEditrice.jsp");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
	}	

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
