package nytro.control;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.Cart;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IVideogiocoDAO;

@WebServlet("/GestoreCarrello")
public class GestoreCarrello extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

    public void setVideogiocoDAO(IVideogiocoDAO videogiocoDAO) {
        this.videogiocoDAO = videogiocoDAO;
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AccountBean account = (AccountBean) request.getSession().getAttribute("account");

        Cart cart = (Cart) request.getSession().getAttribute("carrello");

        if (cart == null) {
            cart = new Cart();
            request.getSession().setAttribute("carrello", cart);
        }

        String action = request.getParameter("action");
        String message = null;

        try {
            if (action != null && !action.equals("")) {
                if (action.equalsIgnoreCase("addCart")) {
                    String codiceVideogioco = request.getParameter("codiceVideogioco");
                    String consoleScelta = request.getParameter("consoleScelta");
                    if (codiceVideogioco == null || codiceVideogioco.equals("") || consoleScelta == null || consoleScelta.equals(""))
                        throw new MyException("Codice videogioco assente");
                    VideogiocoBean videogioco = videogiocoDAO.doRetrieveByCodice(Integer.parseInt(codiceVideogioco), null);
                    if (videogioco != null) {
                        for (VideogiocoBean videogiocoBean : cart.getItems()) {
                            if (videogiocoBean.getCodice() == videogioco.getCodice() && videogiocoBean.getConsole().get(0) == consoleScelta) {
                                message = "Videogioco NON inserito correttamente nel carrello";
                            }
                        }
                        if (message == null) {
                            List<String> consoleSceltaList = new ArrayList<>();
                            consoleSceltaList.add(consoleScelta);
                            videogioco.setConsole(consoleSceltaList);
                            cart.addItem(videogioco);
                            message = "Videogioco inserito correttamente nel carrello";
                        }
                    }
                } else if (action.equalsIgnoreCase("deleteCart")) {
                    String codiceVideogioco = request.getParameter("codiceVideogioco");
                    if (codiceVideogioco == null || codiceVideogioco.equals(""))
                        throw new MyException("Codice videogioco assente");
                    int codiceVid = Integer.parseInt(codiceVideogioco);
					cart.getItems().removeIf(videogiocoBean -> videogiocoBean.getCodice()==codiceVid);
					message = "Videogioco rimosso correttamente nel carrello";
                } else if (action.equalsIgnoreCase("clearCart")) {
                    cart.deleteAll();
                    message = "Carrello svuotato con successo";
                } else if (action.equalsIgnoreCase("buy")) {
                    String cartaDiPagamento = request.getParameter("cartaDiPagamento");
                    if (cartaDiPagamento == null || cartaDiPagamento.equals("") || cartaDiPagamento.length()>16)
                        throw new MyException("Carta di pagamento assente");
                    videogiocoDAO.doAcquisto(cart.getItems(), account, cartaDiPagamento);
                    cart.deleteAll();
                    message = "Acquistato effettuato con successo";
                }
            }
        } catch (SQLException | NumberFormatException e) {
            throw new MyException("Errore modifica carrello");
        }

        request.setAttribute("message", message);
        request.getSession().setAttribute("carrello", cart);

        String url = response.encodeURL("jsp/carrello.jsp");
        request.getRequestDispatcher(url).forward(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
