package nytro.control;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.AccountBean;
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
import org.springframework.mock.web.MockPart;

import javax.servlet.ServletException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PubblicazioniTest {
    private Pubblicazioni servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Pubblicazioni();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_7.1_1
    @Test
    void doGet1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_2
    @Test
    void doGet2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "Data_Rilascio");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_3
    @Test
    void doGet3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "Titolo");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_4
    @Test
    void doGet4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "Voto_Medio");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_5
    @Test
    void doGet5() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "PEGI");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_1
    @Test
    void doGetAggiungiVideogioco1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_2
    @Test
    void doGetAggiungiVideogioco2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        doThrow(Exception.class).when(videogiocoDAO).doSaveVideogiocoPagamento(any(), any(), any());

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_3
    @Test
    void doGetAggiungiVideogioco3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_4
    @Test
    void doGetAggiungiVideogioco4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", ".");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_5
    @Test
    void doGetAggiungiVideogioco5() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_6
    @Test
    void doGetAggiungiVideogioco6() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "generegeneregenere");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        doThrow(Exception.class).when(videogiocoDAO).doSaveVideogiocoPagamento(any(), any(), any());

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_7
    @Test
    void doGetAggiungiVideogioco7() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_8
    @Test
    void doGetAggiungiVideogioco8() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_9
    @Test
    void doGetAggiungiVideogioco9() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", null);
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_10
    @Test
    void doGetAggiungiVideogioco10() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.pdf", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_11
    @Test
    void doGetAggiungiVideogioco11() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_12
    @Test
    void doGetAggiungiVideogioco12() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", ".");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_13
    @Test
    void doGetAggiungiVideogioco13() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_14
    @Test
    void doGetAggiungiVideogioco14() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSElz1w_uW3lSElz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_15
    @Test
    void doGetAggiungiVideogioco15() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "ciao");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_16
    @Test
    void doGetAggiungiVideogioco16() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        when(videogiocoDAO.doRetrieveByCodice(0, "")).thenReturn(new VideogiocoBean());

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.3_1
    @Test
    void doGetRimuovereVideogioco() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("cancelVideogioco", "1");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setCodice(1);
        videogiocoBean.setDataRimozione("2020-10-10");

        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        when(videogiocoDAO.doRetrieveByCodice(1, "")).thenReturn(videogiocoBean);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.4_1
    @Test
    void doGetReintegraVideogioco() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("nintendo");
        accountBean.setPassword("0c644c07df798e30c5b4b3f377e97d08cbf1e54f");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("setDisponibleVideogioco", "true");
        request.setParameter("codiceVideogioco", "1");

        when(videogiocoDAO.doRetrieveByCodice(0, "")).thenReturn(new VideogiocoBean());

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }



    // TC_7.1_1
    @Test
    void doGet1I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_2
    @Test
    void doGet2I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "Data_Rilascio");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_3
    @Test
    void doGet3I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "Titolo");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_4
    @Test
    void doGet4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "Voto_Medio");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.1_5
    @Test
    void doGet5I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("order", "PEGI");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_1
    @Test
    void doGetAggiungiVideogioco1I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_2
    @Test
    void doGetAggiungiVideogioco2I() throws ServletException, IOException, SQLException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3 Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_3
    @Test
    void doGetAggiungiVideogioco3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_4
    @Test
    void doGetAggiungiVideogioco4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", ".");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_5
    @Test
    void doGetAggiungiVideogioco5I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_6
    @Test
    void doGetAggiungiVideogioco6I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "generegeneregenere");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_7
    @Test
    void doGetAggiungiVideogioco7I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_8
    @Test
    void doGetAggiungiVideogioco8I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5 PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_9
    @Test
    void doGetAggiungiVideogioco9I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", null);
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_10
    @Test
    void doGetAggiungiVideogioco10I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.pdf", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_11
    @Test
    void doGetAggiungiVideogioco11I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_12
    @Test
    void doGetAggiungiVideogioco12I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", ".");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_7.2_13
    @Test
    void doGetAggiungiVideogioco13I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_14
    @Test
    void doGetAggiungiVideogioco14I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSElz1w_uW3lSElz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_15
    @Test
    void doGetAggiungiVideogioco15I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        request.setParameter("aggTitolo", "Kingdom Hearts 3");
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "ciao");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    // TC_7.2_16
    @Test
    void doGetAggiungiVideogioco16I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("aggiungiVideogioco", "true");

        String titolo = "Kingdom Hearts 3";
        request.setParameter("aggTitolo", titolo);
        request.setParameter("aggPegi", "12");
        request.setParameter("aggGenere", "Picchiaduro");
        request.setParameter("aggConsole", "PS5");
        request.setParameter("aggPrezzo", "50");
        request.setParameter("aggTrailer", "https://www.youtube.com/embed/lz1w_uW3lSE");
        MockPart mockPart = new MockPart("photo", "Kh3.png", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());

        List<VideogiocoBean> giochi = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAll("","");
        for(VideogiocoBean x : giochi){
            if(x.getTitolo().equals(titolo)){
                doDeleteVideogioco(x);
            }
        }

    }

    // TC_7.3_1
    @Test
    void doGetRimuovereVideogiocoI() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        VideogiocoBean videogiocoBean = videogiocoDAO.doRetrieveByCodice(2,"");
        if(videogiocoBean.getDataRimozione()!=null){
            videogiocoDAO.doSetDisponibleVideogioco(2);
        }

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("cancelVideogioco", "2");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());

        videogiocoDAO.doSetDisponibleVideogioco(2);
    }

    // TC_7.4_1
    @Test
    void doGetReintegraVideogiocoI() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);

        VideogiocoBean videogiocoBean = videogiocoDAO.doRetrieveByCodice(2,"");
        if(videogiocoBean.getDataRimozione()==null){
            videogiocoDAO.doDeleteVideogioco(videogiocoBean);
        }

        request.setParameter("setDisponibleVideogioco", "true");
        request.setParameter("codiceVideogioco", "1");

        servlet.doPost(request, response);
        assertEquals("jsp/pubblicazioni.jsp", response.getForwardedUrl());
    }

    public boolean doDeleteVideogioco(VideogiocoBean gioco) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result;

        String deleteSQL="DELETE FROM Videogioco WHERE Codice = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);
            preparedStatement.setInt(1, gioco.getCodice());

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