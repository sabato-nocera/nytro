package nytro.control;

import lombok.SneakyThrows;
import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.GiocatoreBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockPart;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrazioneUtenteTest {
    private RegistrazioneUtente servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new RegistrazioneUtente();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    //TC_2.1_1
    @Test
    void doGet1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_2
    @Test
    void doGet2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "GiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);

        doThrow(Exception.class).when(accountDAO).doSaveGiocatore(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.1_3
    @Test
    void doGet3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore&");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }


    //TC_2.1_4
    @Test
    void doGet4() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "rosanera89");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveGiocatore(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.1_5
    @Test
    void doGet5() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_6
    @Test
    void doGet6() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.");
        request.setParameter("passwordConferma", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234..");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_7
    @Test
    void doGet7() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "PASSWORD1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_8
    @Test
    void doGet8() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_9
    @Test
    void doGet9() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_10
    @Test
    void doGet10() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_11
    @Test
    void doGet11() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_12
    @Test
    void doGet12() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_13
    @Test
    void doGet13() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "accountaccountaccounta@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveGiocatore(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.1_14
    @Test
    void doGet14() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_15
    @Test
    void doGet15() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_16
    @SneakyThrows
    @Test
    void doGet16() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "accountaccountaccounta@principale.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveGiocatore(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.1_17
    @Test
    void doGet17() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_18
    @Test
    void doGet18() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_19
    @Test
    void doGet19() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "123");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    //TC_2.1_20
    @Test
    void doGet20() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_21
    @Test
    void doGet21() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_22
    @Test
    void doGet22() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_23
    @Test
    void doGet23() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request,response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_24
    @Test
    void doGet24() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }



    //TC_2.1_1
    @Test
    void doGet1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_2
    @Test
    void doGet2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "GiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatoreGiocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_3
    @Test
    void doGet3I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore&");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_4
    @Test
    void doGet4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "rosanera89");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con succcesso", request.getAttribute("message"));
    }

    //TC_2.1_5
    @Test
    void doGet5I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_6
    @Test
    void doGet6I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.");
        request.setParameter("passwordConferma", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234..");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_7
    @Test
    void doGet7I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "PASSWORD1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_8
    @Test
    void doGet8I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_9
    @Test
    void doGet9I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_10
    @Test
    void doGet10I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_11
    @Test
    void doGet11I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_12
    @Test
    void doGet12I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_13
    @Test
    void doGet13I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "accountaccountaccounta@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_14
    @Test
    void doGet14I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_15
    @Test
    void doGet15I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_16
    @SneakyThrows
    @Test
    void doGet16I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "accountaccountaccounta@principale.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_17
    @Test
    void doGet17I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_18
    @Test
    void doGet18I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_19
    @Test
    void doGet19I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "123");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    //TC_2.1_20
    @Test
    void doGet20I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_21
    @Test
    void doGet21I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);


        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_22
    @Test
    void doGet22I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));

        AccountBean accountBean1 = accountDAO.doRetrieveByUsername("Giocatore");
        accountDAO.doDelete(accountBean1);
    }

    //TC_2.1_23
    @Test
    void doGet23I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request,response);
        assertNotEquals("Registrazione effettuata con successo", request.getAttribute("message"));
    }

    //TC_2.1_24
    @Test
    void doGet24I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "Giocatore");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "account@principale.it");
        request.setParameter("emailRec", "account@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("bornDate", "1990-10-10");
        request.setParameter("genere", "m");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request,response);
        assertEquals("Registrazione effettuata con successo", request.getAttribute("message"));

        AccountBean accountBean1 = accountDAO.doRetrieveByUsername("Giocatore");
        accountDAO.doDelete(accountBean1);
    }
}