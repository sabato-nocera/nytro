package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
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
import nytro.other.Utils;

@WebServlet("/Pubblicazioni")
@MultipartConfig(maxFileSize = 16177215)
public class Pubblicazioni extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
	private IAccountDAO accountDAO = new AccountDAO();

	public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
		this.videogiocoDAO = videogiocoDAO;
	}

	public void setAccountDAO(IAccountDAO accountDAO) {
		this.accountDAO = accountDAO;
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		AccountBean account = (AccountBean) request.getSession().getAttribute("account");
		
		String isin=null;
		try {
			isin = accountDAO.doRetrieveIsinByUsername(account.getUsername());
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String aggiungiVideogioco = request.getParameter("aggiungiVideogioco");
		if(aggiungiVideogioco!=null && !aggiungiVideogioco.equals("")) {
			String aggPegi = request.getParameter("aggPegi");
			String aggGenere = request.getParameter("aggGenere");
			String aggTitolo = request.getParameter("aggTitolo");
			String aggConsole = request.getParameter("aggConsole");
			String aggPrezzo = request.getParameter("aggPrezzo");
			String aggTrailer = request.getParameter("aggTrailer");
			
			if(aggTitolo!=null && aggPegi!=null && aggGenere!=null && aggConsole!=null && aggPrezzo!=null && aggTrailer!=null&&
					!aggTitolo.equals("") && !aggPegi.equals("") && !aggGenere.equals("") && !aggConsole.equals("") && !aggPrezzo.equals("") && !aggTrailer.equals("") &&
					Utils.checkImg(request.getPart("photo").getSubmittedFileName()) && aggTrailer.length()<=50 && aggConsole.length()<=15 && aggTrailer.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {

				VideogiocoBean bean = new VideogiocoBean();
				bean.setISIN(isin);

				Date dataRilascio = new Date();
				String modifiedDate= new SimpleDateFormat("yyyy-MM-dd").format(dataRilascio);
				bean.setDataRilascio(modifiedDate);

				bean.setTitolo(aggTitolo);
				bean.setPEGI(Integer.parseInt(aggPegi));
				bean.setImg(request.getPart("photo").getInputStream());
				bean.setPrezzo(Float.parseFloat(aggPrezzo));
				bean.setTrailer(aggTrailer);

				try {
					videogiocoDAO.doSaveVideogiocoPagamento(bean,aggGenere, aggConsole);
					videogiocoDAO.doRetrieveAll(null, isin);
					if(request.getPart("photo") != null && request.getPart("photo").getSize() > 0)
						videogiocoDAO.doUploadImage(bean);
					while(videogiocoDAO.doRetrieveByCodice(bean.getCodice(), "")==null)
						;
				} catch (SQLException e) {
					throw new MyException("Videogioco a pagamento non salvato con successo");
				}
			}
		}
		
		String cancelVideogioco = request.getParameter("cancelVideogioco");
		if(cancelVideogioco!=null && !cancelVideogioco.equals("")) {
			try {
				VideogiocoBean bean = videogiocoDAO.doRetrieveByCodice(Integer.parseInt(cancelVideogioco), null);
				videogiocoDAO.doDeleteVideogioco(bean);
				while(videogiocoDAO.doRetrieveByCodice(bean.getCodice(), "").getDataRimozione()==null)
					;
			} catch (NumberFormatException | SQLException e) {
				throw new MyException("Errore cancellazione videogioco");
			}
		}

		String setDisponibleVideogioco = request.getParameter("setDisponibleVideogioco");
		if(setDisponibleVideogioco!=null && !setDisponibleVideogioco.equals("")) {
			try {
				int codice = Integer.parseInt(request.getParameter("codiceVideogioco"));
				videogiocoDAO.doSetDisponibleVideogioco(codice);
			} catch (NumberFormatException | SQLException e) {
				throw new MyException("Errore ripristino disponibilita' videogioco");
			}
		}
		
		
		String order = request.getParameter("order");
		
		Collection<VideogiocoBean> videogiochi;
		try {
			videogiochi = videogiocoDAO.doRetrieveAll(order, isin);
		} catch (SQLException e) {
			throw new MyException("Errore estrazione videogiochi.");
		}
		
		request.setAttribute("videogiochi", videogiochi);

		String url = response.encodeURL("jsp/pubblicazioni.jsp");
		request.getRequestDispatcher(url).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
