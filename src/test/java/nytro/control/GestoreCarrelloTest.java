package nytro.control;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.AccountBean;
import nytro.model.bean.Cart;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.AccountDAO;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IVideogiocoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyChar;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GestoreCarrelloTest {
    private GestoreCarrello servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new GestoreCarrello();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_9.1_1
    @Test
    void doGetAddCart() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","addCart");
        request.setParameter("codiceVideogioco","1");
        request.setParameter("consoleScelta","PC");

        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(new VideogiocoBean());

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("Videogioco inserito correttamente nel carrello", request.getAttribute("message"));
    }

    // TC_9.2_1
    @Test
    void doGetDeleteCart() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = new VideogiocoBean();
        tmp.setConsole(Collections.singletonList("PS4"));
        tmp.setCodice(1);
        cart.addItem(tmp);
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","deleteCart");
        request.setParameter("codiceVideogioco","1");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("Videogioco rimosso correttamente nel carrello", request.getAttribute("message"));
    }

    // TC_9.3_1
    @Test
    void doGetClearCart() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = new VideogiocoBean();
        tmp.setConsole(Collections.singletonList("PS4"));
        tmp.setCodice(1);
        cart.addItem(tmp);
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","clearCart");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("Carrello svuotato con successo", request.getAttribute("message"));
    }

    // TC_9.4_1
    @Test
    void doGetBuy1() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = new VideogiocoBean();
        tmp.setConsole(Collections.singletonList("PS4"));
        tmp.setCodice(1);
        cart.addItem(tmp);
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","buy");
        request.setParameter("cartaDiPagamento","");

        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_9.4_2
    @Test
    void doGetBuy2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = new VideogiocoBean();
        tmp.setConsole(Collections.singletonList("PS4"));
        tmp.setCodice(1);
        cart.addItem(tmp);
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","buy");
        request.setParameter("cartaDiPagamento","12345678910121516171289");

        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_9.4_3
    @Test
    void doGetBuy3() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = new VideogiocoBean();
        tmp.setConsole(Collections.singletonList("PS4"));
        tmp.setCodice(1);
        cart.addItem(tmp);
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","buy");
        request.setParameter("cartaDiPagamento","1234567890123456");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("Acquistato effettuato con successo", request.getAttribute("message"));
    }

    // TC_9.1_1
    @Test
    void doGetAddCartI() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        request.getSession().setAttribute("carrello", cart);

        request.setParameter("action","addCart");
        request.setParameter("codiceVideogioco","1");
        request.setParameter("consoleScelta","PC");

        servlet.doPost(request, response);
        assertEquals("Videogioco inserito correttamente nel carrello", request.getAttribute("message"));
    }

    // TC_9.2_1
    @Test
    void doGetDeleteCartI() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = videogiocoDAO.doRetrieveByCodice(1,"");
        tmp.setConsole(Collections.singletonList("PS4"));
        cart.addItem(tmp);

        request.getSession().setAttribute("carrello", cart);
        request.setParameter("action","deleteCart");
        request.setParameter("codiceVideogioco","1");

        servlet.doPost(request, response);
        assertEquals("Videogioco rimosso correttamente nel carrello", request.getAttribute("message"));
    }

    // TC_9.3_1
    @Test
    void doGetClearCartI() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = videogiocoDAO.doRetrieveByCodice(1,"");
        tmp.setConsole(Collections.singletonList("PS4"));
        cart.addItem(tmp);

        request.getSession().setAttribute("carrello", cart);
        request.setParameter("action","clearCart");

        servlet.doPost(request, response);
        assertEquals("Carrello svuotato con successo", request.getAttribute("message"));
    }

    // TC_9.4_1
    @Test
    void doGetBuy1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = videogiocoDAO.doRetrieveByCodice(1,"");
        tmp.setConsole(Collections.singletonList("PS4"));
        cart.addItem(tmp);

        request.getSession().setAttribute("carrello", cart);
        request.setParameter("action","buy");
        request.setParameter("cartaDiPagamento","");

        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_9.4_2
    @Test
    void doGetBuy2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = videogiocoDAO.doRetrieveByCodice(1,"");
        tmp.setConsole(Collections.singletonList("PS4"));
        cart.addItem(tmp);

        request.getSession().setAttribute("carrello", cart);
        request.setParameter("action","buy");
        request.setParameter("cartaDiPagamento","12345678910121516171289");

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_9.4_3
    @Test
    void doGetBuy3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        Cart cart = new Cart();
        VideogiocoBean tmp = videogiocoDAO.doRetrieveByCodice(1,"");
        tmp.setConsole(Collections.singletonList("PC"));
        cart.addItem(tmp);

        request.getSession().setAttribute("carrello", cart);
        request.setParameter("action","buy");
        request.setParameter("cartaDiPagamento","1234567890123456");

        servlet.doPost(request, response);
        assertEquals("Acquistato effettuato con successo", request.getAttribute("message"));
        doDeleteFromAcquisti(accountBean.getUsername(), tmp.getCodice(), tmp.getConsole().get(0));
    }

    public boolean doDeleteFromAcquisti(String username, int codiceVideogiocoDaCancellare, String Console) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result;

        String deleteSQL="DELETE FROM ACQUISTA WHERE Username = ? AND Videogioco = ? AND Nome_Console = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, codiceVideogiocoDaCancellare);
            preparedStatement.setString(3, Console);

            System.out.println("doDeleteFromAcquisti: " + preparedStatement.toString());

            result=preparedStatement.executeUpdate();
            connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if(preparedStatement!=null)
                    preparedStatement.close();
            } finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return (result != 0);
    }


}