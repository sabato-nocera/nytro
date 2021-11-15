package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.Cart;
import nytro.model.bean.RecensioneBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.AccountDAO;
import nytro.model.dao.RecensioneDAO;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IRecensioneDAO;
import nytro.model.idao.IVideogiocoDAO;
import nytro.other.Utils;

@WebServlet("/Videogioco")
@MultipartConfig(maxFileSize = 16177215)
public class Videogioco extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
    private IRecensioneDAO recensioneDAO = new RecensioneDAO();
    private IAccountDAO accountDAO = new AccountDAO();

    public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
        this.videogiocoDAO = videogiocoDAO;
    }

    public void setRecensioneDAO(IRecensioneDAO recensioneDAO) {
        this.recensioneDAO = recensioneDAO;
    }

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountBean account = (AccountBean) request.getSession().getAttribute("account");

        String codiceVideogioco = request.getParameter("codiceVideogioco");
        System.out.println(codiceVideogioco);
        if (codiceVideogioco == null || codiceVideogioco.equals(""))
            throw new MyException("Parametro codice videogioco vuoto");

        VideogiocoBean videogiocoDetailed;

        try {
            videogiocoDetailed = videogiocoDAO.doRetrieveByCodice(Integer.parseInt(codiceVideogioco), null);
        } catch (NumberFormatException e) {
            throw new MyException("Codice non valido");
        } catch (SQLException e) {
            throw new MyException("Errore SQL per videogioco");
        }

        String cambiaImmagineVideogioco = request.getParameter("cambiaImmagineVideogioco");
        if (cambiaImmagineVideogioco != null && !cambiaImmagineVideogioco.equals("")) {
            if (request.getPart("photo") != null && request.getPart("photo").getSize() > 0
                    && Utils.checkImg(request.getPart("photo").getSubmittedFileName())) {
                videogiocoDetailed.setImg(request.getPart("photo").getInputStream());
                try {
                    videogiocoDAO.doUploadImageByCodice(videogiocoDetailed);
                } catch (SQLException e) {
                    throw new MyException("Errore cambiamento immagine videogioco");
                }
            }
        }

        String cambiaPrezzo = request.getParameter("cambiaPrezzo");
        if (cambiaPrezzo != null && !cambiaPrezzo.equals("")) {
            String newPrezzo = request.getParameter("newPrezzo");
            if (newPrezzo != null && !newPrezzo.equals("")) {
                VideogiocoBean tmp = (VideogiocoBean) videogiocoDetailed;
                tmp.setPrezzo(Float.parseFloat(newPrezzo));
                try {
                    videogiocoDAO.doUpdate(tmp);
                } catch (SQLException e) {
                    throw new MyException("Errore cambiamento prezzo videogioco");
                }
            }
        }

        String cambiaGenere = request.getParameter("cambiaGenere");
        if (cambiaGenere != null && !cambiaGenere.equals("")) {
            String newGenere = request.getParameter("newGenere");
            if (newGenere != null && !newGenere.equals("")) {
                try {
                    videogiocoDAO.doInsertGenere(newGenere, videogiocoDetailed);
                } catch (SQLException e) {
                    throw new MyException("Errore cambiamento genere videogioco");
                }
            }
        }

        String cambiaTrailer = request.getParameter("cambiaTrailer");
        if (cambiaTrailer != null && !cambiaTrailer.equals("") && cambiaTrailer.matches("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]")) {
            try {
                videogiocoDetailed.setTrailer(cambiaTrailer);
                videogiocoDAO.doUpdate(videogiocoDetailed);
            } catch (SQLException e) {
                throw new MyException("Errore cambiamento trailer videogioco");
            }
        }

        String rimuovereRecensione = request.getParameter("rimuovereRecensione");
        if (rimuovereRecensione != null && !rimuovereRecensione.equals("")) {
            RecensioneBean recensione = new RecensioneBean();
            try {
                recensione = recensioneDAO.doRetrieveByUsername(account.getUsername(), Integer.parseInt(codiceVideogioco));
                recensioneDAO.doDelete(account.getUsername(), Integer.parseInt(codiceVideogioco));
            } catch (NumberFormatException | SQLException e) {
                throw new MyException("Impossibile cancellare recensione");
            }
        }

        String commentoRecensione = request.getParameter("commentoRecensione");
        String votoRecensione = request.getParameter("votoRecensione");

        if (commentoRecensione != null && votoRecensione != null && !commentoRecensione.equals("") && !votoRecensione.equals("")) {
            RecensioneBean recensione = new RecensioneBean();
            recensione.setCodVideogioco(Integer.parseInt(codiceVideogioco));
            recensione.setCommento(commentoRecensione);
            double voto = Double.parseDouble(votoRecensione);
            recensione.setVoto(Double.parseDouble(votoRecensione));
            if (voto >= 0 && voto <= 5) {
                recensione.setUsername(account.getUsername());
                try {
                    if (recensioneDAO.doCheck(recensione))
                        recensioneDAO.doSave(recensione);
                    else
                        throw new MyException("Recensione gia' rilasciata per questo gioco");
                } catch (SQLException e) {
                    throw new MyException("Errore inserimento nuova recensione");
                }
            }
        }

        String orderRecensioni = request.getParameter("orderRecensioni");
        if (orderRecensioni == null)
            orderRecensioni = "";

        Collection<RecensioneBean> recensioni = null;

        try {
            recensioni = recensioneDAO.doRetrieveAllByCodice(orderRecensioni, videogiocoDetailed.getCodice());
        } catch (SQLException e) {
            throw new MyException("Errore SQL per recensioni");
        }

        String rangeRecensione = request.getParameter("rangeRecensione");
        if (rangeRecensione != null && !rangeRecensione.equals("")) {
            String min = request.getParameter("min");
            String max = request.getParameter("max");
            if (min != null && max != null && !min.equals("") && !max.equals("")) {
                try {
                    recensioni = recensioneDAO.doRetrieveAllByRange(videogiocoDetailed.getCodice(), orderRecensioni, Integer.parseInt(min), Integer.parseInt(max));
                } catch (NumberFormatException | SQLException e) {
                    throw new MyException("Impossibile ricavare recensioni");
                }
            }
        }

        boolean possibileAggiungereAllaLibreria = false;

        try {
            possibileAggiungereAllaLibreria = videogiocoDAO.doRetrieveAppartenenzaAllaLibreria(Integer.parseInt(codiceVideogioco), account.getUsername());
            System.out.println(possibileAggiungereAllaLibreria);
        } catch (NumberFormatException | SQLException e) {
            throw new MyException("Non si sa se si puo' aggiungere alla libreria");
        }

        if (possibileAggiungereAllaLibreria)
            request.setAttribute("possibileAggiungereAllaLibreria", "true");

        Collection<AccountBean> amici = null;

        try {
            amici = accountDAO.doRetrieveAllFriendsByVideogioco(account, Integer.parseInt(codiceVideogioco));
        } catch (SQLException e) {
            ;
        }

        try {
            videogiocoDetailed = videogiocoDAO.doRetrieveByCodice(Integer.parseInt(codiceVideogioco), null);
        } catch (NumberFormatException e) {
            throw new MyException("Codice non valido");
        } catch (SQLException e) {
            throw new MyException("Errore SQL per videogioco");
        }

        String casa = videogiocoDetailed.getISIN();
        try {
            casa = videogiocoDAO.doRetrieveCasaByCodice(videogiocoDetailed.getCodice());
        } catch (SQLException e) {
            throw new MyException("Errore SQL per casa videogioco");
        }

        boolean possibileAggiungereAgliAcquisti = false;

        try {
            List<String> consoleAcquistate = IVideogiocoDAO.doGetConsoleAcquistate(Integer.parseInt(codiceVideogioco), account.getUsername());
            List<String> consoleDisponibili = new ArrayList<>();

            Cart cart = (Cart) request.getSession().getAttribute("carrello");
            if (cart != null) {
                for (VideogiocoBean videogiocoBean : cart.getItems()) {
                    if (videogiocoBean.getCodice() == videogiocoDetailed.getCodice()) {
                        consoleAcquistate.add(videogiocoBean.getConsole().get(0));
                    }
                }
            }

            if (consoleAcquistate != null) {
                for (String console : videogiocoDetailed.getConsole()) {
                    if (!consoleAcquistate.contains(console)) {
                        consoleDisponibili.add(console);
                    }
                }
            }
            request.setAttribute("consoleDisponibili", consoleDisponibili);
            if (consoleDisponibili.size() == 0) {
                possibileAggiungereAgliAcquisti = false;
            } else {
                possibileAggiungereAgliAcquisti = true;
            }
            System.out.println(possibileAggiungereAgliAcquisti);
        } catch (NumberFormatException | SQLException e) {
            throw new MyException("Non si sa se si puo' aggiungere agli acquisti");
        }

        if (possibileAggiungereAgliAcquisti) {
            request.setAttribute("possibileAggiungereAgliAcquisti", "true");
        }

        request.setAttribute("videogiocoDetailed", videogiocoDetailed);
        request.setAttribute("recensioni", recensioni);
        request.setAttribute("amici", amici);
        request.setAttribute("nomeCasaEd", casa);

        String url = response.encodeURL("jsp/videogioco.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
