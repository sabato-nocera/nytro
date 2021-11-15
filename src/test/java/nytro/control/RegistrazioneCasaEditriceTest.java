package nytro.control;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.*;

import static org.junit.jupiter.api.Assertions.*;import lombok.SneakyThrows;
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

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegistrazioneCasaEditriceTest {
    private RegistrazioneCasaEditrice servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new RegistrazioneCasaEditrice();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    //TC_2.2_1
    @Test
    void doGet1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "");
        request.setParameter("ISIN", "012345678901");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_2
    @Test
    void doGet2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda S.r.l.");
        request.setParameter("ISIN", "012345678901");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_3
    @Test
    void doGet3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_4
    @Test
    void doGet4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "001");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_5
    @Test
    void doGet5() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_6
    @Test
    void doGet6() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_7
    @Test
    void doGet7() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_8
    @Test
    void doGet8() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_9
    @Test
    void doGet9() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_10
    @Test
    void doGet10() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1&");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_11
    @Test
    void doGet11() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "squareenix");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_12
    @Test
    void doGet12() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_13
    @Test
    void doGet13() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.");
        request.setParameter("passwordConferma", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_14
    @Test
    void doGet14() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "PASSWORD1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_15
    @Test
    void doGet15() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_16
    @Test
    void doGet16() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_17
    @Test
    void doGet17() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_18
    @Test
    void doGet18() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_19
    @Test
    void doGet19() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_20
    @Test
    void doGet20() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "accountaccountaccounta@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_21
    @Test
    void doGet21() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_22
    @Test
    void doGet22() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_23
    @Test
    void doGet23() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "accountaccountaccounta@principale.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_24
    @Test
    void doGet24() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_25
    @Test
    void doGet25() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_26
    @Test
    void doGet26() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "123");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_27
    @Test
    void doGet27() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_28
    @Test
    void doGet28() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.aziendaaziendaaziendaaziendaaziendaaziendaaziendaaziendaazienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doSaveCasaEditrice(any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_29
    @Test
    void doGet29() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "azienda");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_30
    @Test
    void doGet30() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_31
    @Test
    void doGet31() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_32
    @Test
    void doGet32() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }


    //TC_2.2_1
    @Test
    void doGet1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "");
        request.setParameter("ISIN", "012345678901");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_2
    @Test
    void doGet2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda Azienda S.r.l.");
        request.setParameter("ISIN", "012345678901");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_3
    @Test
    void doGet3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_4
    @Test
    void doGet4I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "001");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_5
    @Test
    void doGet5I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_6
    @Test
    void doGet6I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_7
    @Test
    void doGet7I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_8
    @Test
    void doGet8I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_9
    @Test
    void doGet9I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_10
    @Test
    void doGet10I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1&");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_11
    @Test
    void doGet11I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "squareenix");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_12
    @Test
    void doGet12I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_13
    @Test
    void doGet13I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.");
        request.setParameter("passwordConferma", "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_14
    @Test
    void doGet14I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "PASSWORD1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_15
    @Test
    void doGet15I() throws ServletException, IOException,SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_16
    @Test
    void doGet16I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_17
    @Test
    void doGet17I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_18
    @Test
    void doGet18I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_19
    @Test
    void doGet19I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);
        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_20
    @Test
    void doGet20I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "accountaccountaccounta@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_21
    @Test
    void doGet21I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_22
    @Test
    void doGet22I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_23
    @Test
    void doGet23I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "accountaccountaccounta@principale.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_24
    @Test
    void doGet24I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_25
    @Test
    void doGet25I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_26
    @Test
    void doGet26I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "123");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_2.2_27
    @Test
    void doGet27I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_28
    @Test
    void doGet28I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account",accountBean);
        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.aziendaaziendaaziendaaziendaaziendaaziendaaziendaaziendaazienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/registrazioneCasaEditrice.jsp", response.getForwardedUrl());
    }

    //TC_2.2_29
    @Test
    void doGet29I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "azienda");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_30
    @Test
    void doGet30I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237695450775");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());

        AccountBean accountBean1 = accountDAO.doRetrieveByUsername("Azienda1");
        accountDAO.doDelete(accountBean1);
    }

    //TC_2.2_31
    @Test
    void doGet31I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237648450745");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "azienda");
        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.2_32
    @Test
    void doGet32I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account",accountBean);

        request.setParameter("nomeCasaEditrice", "Azienda S.r.l.");
        request.setParameter("ISIN", "237695450775");
        request.setParameter("nomeCEO", "Az Ienda");
        request.setParameter("username", "Azienda1");
        request.setParameter("password", "Password1234.");
        request.setParameter("passwordConferma", "Password1234.");
        request.setParameter("email", "azienda@principale.it");
        request.setParameter("emailRec", "azienda@recupero.it");
        request.setParameter("phone", "1234567890");
        request.setParameter("sitoWeb", "www.azienda.it");
        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());

        AccountBean accountBean1 = accountDAO.doRetrieveByUsername("Azienda1");
        accountDAO.doDelete(accountBean1);
    }
}