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
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IVideogiocoDAO;


@WebServlet("/Index")
public class Index extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private final int numeroMaxVideogiochiDaMostrare = 5;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Collection<VideogiocoBean> videogiochiPiuAcquistati, videogiochiPiuGiocati, videogiochiPiuVotati, 
		videogiochiPiuGiocatiFemmine, videogiochiPiuGiocatiMaschi, videogiochiPiuGiocatiGenderless, 
		videogiochiPiuGiocatiFemmineCasaEditrice = null, videogiochiPiuGiocatiMaschiCasaEditrice = null , videogiochiPiuGiocatiGenderlessCasaEditrice = null,
		videogiochiPiuVotatiCasaEditrice = null, videogiochiPiuGiocatoInGeneraleCasaEditrice = null;
		
		
		try {
			videogiochiPiuAcquistati = videogiocoDAO.doRetrievePiuAcquistati(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuGiocati = videogiocoDAO.doRetrievePiuGiocati(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuVotati = videogiocoDAO.doRetrievePiuVotati(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuGiocatiMaschi = videogiocoDAO.doRetrievePiuGiocatiMaschi(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuGiocatiFemmine = videogiocoDAO.doRetrievePiuGiocatiFemmine(numeroMaxVideogiochiDaMostrare);
			videogiochiPiuGiocatiGenderless = videogiocoDAO.doRetrievePiuGiocatiGenderless(numeroMaxVideogiochiDaMostrare);
			
			
			AccountBean account = (AccountBean) request.getSession().getAttribute("account");
			if(account!=null && account.getRuolo()==2) {
				CasaEditriceBean casaEditriceBean = (CasaEditriceBean) account; 
				videogiochiPiuGiocatiFemmineCasaEditrice = videogiocoDAO.doRetrievePiuGiocatiFemmineCasaEditrice(numeroMaxVideogiochiDaMostrare, casaEditriceBean.getISIN());
				videogiochiPiuGiocatiMaschiCasaEditrice = videogiocoDAO.doRetrievePiuGiocatiMaschiCasaEditrice(numeroMaxVideogiochiDaMostrare, casaEditriceBean.getISIN());
				videogiochiPiuGiocatiGenderlessCasaEditrice = videogiocoDAO.doRetrievePiuGiocatiGenderlessCasaEditrice(numeroMaxVideogiochiDaMostrare, casaEditriceBean.getISIN());
				videogiochiPiuVotatiCasaEditrice = videogiocoDAO.doRetrievePiuVotatiCasaEditrice(numeroMaxVideogiochiDaMostrare, casaEditriceBean.getISIN());

			}
			
		} catch (SQLException e) {
			throw new MyException("Errore SQL per index");
		}
		
		request.setAttribute("videogiochiPiuAcquistati", videogiochiPiuAcquistati);
		request.setAttribute("videogiochiPiuGiocati", videogiochiPiuGiocati);
		request.setAttribute("videogiochiPiuVotati", videogiochiPiuVotati);
		
		request.setAttribute("videogiochiPiuGiocatiMaschi", videogiochiPiuGiocatiMaschi);
		request.setAttribute("videogiochiPiuGiocatiFemmine", videogiochiPiuGiocatiFemmine);
		request.setAttribute("videogiochiPiuGiocatiGenderless", videogiochiPiuGiocatiGenderless);
		
		request.setAttribute("videogiochiPiuGiocatiFemmineCasaEditrice", videogiochiPiuGiocatiFemmineCasaEditrice);
		request.setAttribute("videogiochiPiuGiocatiMaschiCasaEditrice", videogiochiPiuGiocatiMaschiCasaEditrice);
		request.setAttribute("videogiochiPiuGiocatiGenderlessCasaEditrice", videogiochiPiuGiocatiGenderlessCasaEditrice);
		request.setAttribute("videogiochiPiuVotatiCasaEditrice", videogiochiPiuVotatiCasaEditrice);


		String url = response.encodeURL("jsp/index.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
