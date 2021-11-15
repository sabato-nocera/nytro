package nytro.control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.bean.AmministratoreBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;

@WebServlet("/AggiornaProfilo")
@MultipartConfig(maxFileSize = 16177215)
public class AggiornaProfilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IAccountDAO accountDAO = new AccountDAO();

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO=accountDAO;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		if(account==null)
			throw new MyException("Account non loggato");
		
		String cambiaPassword = request.getParameter("cambiaPassword");
		String cambiaPasswordConferma = request.getParameter("cambiaPasswordConferma");
		String vecchiaPassword = request.getParameter("vecchiaPassword");

		if(!(cambiaPassword==null || cambiaPassword.equals("")) && cambiaPassword.length() >= 8
			&& cambiaPassword.toUpperCase() != cambiaPassword && cambiaPassword.toLowerCase() != cambiaPassword) {
			if(vecchiaPassword!=null && !cambiaPasswordConferma.equals("") && !vecchiaPassword.equals("")
				&& !(cambiaPasswordConferma == null)) {
				if(!cambiaPasswordConferma.equals(cambiaPassword))
					throw new MyException("Password missmatch: "+cambiaPasswordConferma+" "+cambiaPassword);
				if(!account.getPassword().equals(Utils.generatePwd(vecchiaPassword)))
					throw new MyException("Vecchia password sbagliata");
				cambiaPassword = Utils.generatePwd(cambiaPassword);
				account.setPassword(cambiaPassword);
				try {
					accountDAO.doUpdate(account);
				} catch (SQLException e) {
					throw new MyException("Fallimento aggiornamento password");
				}
			}			
		}
		
		String cambiaEmail = request.getParameter("cambiaEmail");
		if(cambiaEmail!=null && !cambiaEmail.equals("") && cambiaEmail.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			account.setEmail(cambiaEmail);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento email");
			}
		}
		
		String cambiaEmailRecupero = request.getParameter("cambiaEmailRecupero");
		if(cambiaEmailRecupero!=null && !cambiaEmailRecupero.equals("") && cambiaEmailRecupero.matches("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$")) {
			account.setEmailRecupero(cambiaEmailRecupero);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento email recupero");
			}
		}
		
		String phone = request.getParameter("phone");
		if(phone!=null && !phone.equals("") && phone.matches("\\d{10}")) {
			account.setCellulare(phone);
			try {
				accountDAO.doUpdate(account);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento telefono");
			}
		}
		
		String cambiaNome= request.getParameter("cambiaNome");
		if(cambiaNome!=null && !cambiaNome.equals("")) {
			AmministratoreBean tmp = (AmministratoreBean) account;
			tmp.setNome(cambiaNome);
			try {
				accountDAO.doUpdateAmministratore(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento nome amministratore");
			}
		}
		
		String cambiaCognome= request.getParameter("cambiaCognome");
		if(cambiaCognome!=null && !cambiaCognome.equals("")) {
			AmministratoreBean tmp = (AmministratoreBean) account;
			tmp.setCognome(cambiaCognome);
			try {
				accountDAO.doUpdateAmministratore(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento cognome amministratore");
			}
		}
	
		if (request.getPart("photo") != null && request.getPart("photo").getSize() > 0
				&& Utils.checkImg(request.getPart("photo").getSubmittedFileName())) {
			account.setImgProfilo(request.getPart("photo").getInputStream());
			try {
				accountDAO.doUploadImage(account);
			} catch(SQLException exception) {
				throw new MyException("Fallimento aggiornamento immagine");
			}
		}
		
		String cambiaNomeCasaEditrice = request.getParameter("cambiaNomeCasaEditrice");
		if(cambiaNomeCasaEditrice!=null && !cambiaNomeCasaEditrice.equals("")) {
			CasaEditriceBean tmp = (CasaEditriceBean) account;
			tmp.setNomeCasaEditrice(cambiaNomeCasaEditrice);
			try {
				accountDAO.doUpdateCasaEditrice(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento nome casa editrice");
			}
		}
		
		String cambiaCEO = request.getParameter("cambiaCEO");
		if(cambiaCEO!=null && !cambiaCEO.equals("")) {
			CasaEditriceBean tmp = (CasaEditriceBean) account;
			tmp.setCEO(cambiaCEO);
			try {
				accountDAO.doUpdateCasaEditrice(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento CEO");
			}
		}
		
		String cambiaSitoWEB = request.getParameter("cambiaSitoWEB");
		if(cambiaSitoWEB!=null && !cambiaSitoWEB.equals("")) {
			CasaEditriceBean tmp = (CasaEditriceBean) account;
			tmp.setSitoWeb(cambiaSitoWEB);
			try {
				accountDAO.doUpdateCasaEditrice(tmp);
			} catch (SQLException e) {
				throw new MyException("Fallimento aggiornamento sito web");
			}
		}
		
		String url = response.encodeURL("jsp/profilo.jsp");
		request.getRequestDispatcher(url).forward(request,response);
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
