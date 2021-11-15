package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IVideogiocoDAO;

@WebServlet("/Profilo")
public class Profilo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IAccountDAO accountDAO = new AccountDAO();
	private final IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
		AccountBean detailedAccount=null;
		try {
			detailedAccount = accountDAO.doRetrieveDetailed(account);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione detailedAccount partendo dall'account: "+account);
		}
		
		request.getSession().removeAttribute("account");
		
		if(detailedAccount.getUsername()!=null)
			request.getSession().setAttribute("account", detailedAccount);
		else
			request.getSession().setAttribute("account", account);
		
		
		ArrayList<String> contributo = null;
		String contributoAnnuale = request.getParameter("contributoAnnuale");
		if(contributoAnnuale!=null && !contributoAnnuale.equals("")) {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(endDate!=null && startDate!=null && !startDate.equals("") && !endDate.equals("")) {
				try {
					contributo=accountDAO.doRetrieveContributoAdmin(startDate, endDate);
				} catch (SQLException e) {
					throw new MyException("Errore estrazione incassi piattaforma");
				}
			}
			request.setAttribute("contributo", contributo);
		}
		
		String contributoAnnualeCasaEditrice = request.getParameter("contributoAnnualeCasaEditrice");
		if(contributoAnnualeCasaEditrice!=null && !contributoAnnualeCasaEditrice.equals("")) {
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			if(endDate!=null && startDate!=null && !startDate.equals("") && !endDate.equals("")) {
				try {					
					contributo=accountDAO.doRetrieveContributoCasaEditrice(accountDAO.doRetrieveIsinByUsername(account.getUsername()), startDate, endDate);
				} catch (SQLException e) {
					throw new MyException("Errore estrazione contributo casa editrice");
				}
			}
			request.setAttribute("contributo", contributo);
		}
		
		String listaVideogiochiRimossi = request.getParameter("listaVideogiochiRimossi");
		if(listaVideogiochiRimossi!=null && !listaVideogiochiRimossi.equals("")) {
			String annoRimozione = request.getParameter("annoRimozione");
			ArrayList<VideogiocoBean> videogiochiRimossiInAnno = null;
			if(annoRimozione!=null && annoRimozione!=null) {
				try {					
					annoRimozione+="-__-__";
					videogiochiRimossiInAnno = videogiocoDAO.doRetrievePerAnnoDiRimozione(annoRimozione);
				} catch (SQLException e) {
					throw new MyException("Errore estrazione videogiochi in un certo anno");
				}
			}
			request.setAttribute("videogiochiRimossiInAnno", videogiochiRimossiInAnno);
		}
		
		String rangeEtaGiocatori = request.getParameter("rangeEtaGiocatori");
		if(rangeEtaGiocatori!=null && !rangeEtaGiocatori.equals("")) {
			String minEta = request.getParameter("minEta");
			String maxEta = request.getParameter("maxEta");
			int n=-1;
			if(maxEta!=null && minEta!=null && !maxEta.equals("") && !minEta.equals("")) {
				try {					
					n=accountDAO.doRetrieveNumeroGiocatori(Integer.parseInt(minEta), Integer.parseInt(maxEta));
				} catch (SQLException e) {
					throw new MyException("Errore estrazione numero di giocatori di eta' compresa tra x e y");
				}
			}
			request.setAttribute("quantiGiocatori", "Numero di giocatori di eta' compresa tra "+minEta+" e "+maxEta+" risulta essere: "+n);
		}
		
		String rangeEtaGiocatoriVideogioco = request.getParameter("rangeEtaGiocatoriVideogioco");
		if(rangeEtaGiocatoriVideogioco!=null && !rangeEtaGiocatoriVideogioco.equals("")) {
			String minEtaVideogioco = request.getParameter("minEtaVideogioco");
			String maxEtaVideogioco = request.getParameter("maxEtaVideogioco");
			VideogiocoBean videogiocoPiuGiocatoDa=null;
			if(maxEtaVideogioco!=null && minEtaVideogioco!=null && !maxEtaVideogioco.equals("") && !minEtaVideogioco.equals("")) {
				try {					
					videogiocoPiuGiocatoDa=videogiocoDAO.doRetrieveVideogiocoPiuGiocatoDa(Integer.parseInt(minEtaVideogioco), Integer.parseInt(maxEtaVideogioco));
				} catch (SQLException e) {
					throw new MyException("Errore estrazione videogioco piu' giocato dai giocatori di eta' compresa tra x e y");
				}
			}
			request.setAttribute("videogiocoPiuGiocatoDa", videogiocoPiuGiocatoDa);
		}
		
		try {
			request.setAttribute("piuGiocatoMaschi", videogiocoDAO.doRetrievePiuGiocatoMaschi());
			request.setAttribute("piuGiocatoFemmine", videogiocoDAO.doRetrievePiuGiocatoFemmine());
		} catch (SQLException e) {
			throw new MyException("Fallimento estrazione videogioco piu' giocato da maschi/femmine");
		}
		
		String url = response.encodeURL("jsp/profilo.jsp");
		RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
		requestDispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
