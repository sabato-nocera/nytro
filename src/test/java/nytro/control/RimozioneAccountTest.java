package nytro.control;

import nytro.model.bean.AccountBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RimozioneAccountTest {
    private RimozioneAccount servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new RimozioneAccount();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    //TC_2.5_1 e TC_2.6_1 e TC_2.7_1 e TC_2.8_1
    @Test
    void doGet1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "");
        request.setParameter("option", "1");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("<no/>"));
    }

    //TC_2.5_2 e TC_2.7_2
    @Test
    void doGet2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "rosanera8999");
        request.setParameter("option", "1");

        when(accountDAO.doRetrieveByUsername("rosanera8999")).thenReturn(null);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("<no/>"));
    }

    //TC_2.5_3
    @Test
    void doGet3() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "rosanera89");
        request.setParameter("option", "1");

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("rosanera89");
        accountBean.setEmail("rosanera@mail.com");
        accountBean.setRuolo(1);

        when(accountDAO.doRetrieveByUsername("rosanera89")).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("</ok>"));
    }

    //TC_2.6_2 e TC_2.8_2
    @Test
    void doGet4() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "squareenixx");
        request.setParameter("option", "1");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("<no/>"));
    }

    //TC_2.6_3
    @Test
    void doGet5() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "squareenix");
        request.setParameter("option", "1");

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("squareenix");
        accountBean.setEmail("squareenix@mail.com");
        accountBean.setRuolo(2);

        when(accountDAO.doRetrieveByUsername("squareenix")).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("</ok>"));
    }

    //TC_2.7_3
    @Test
    void doGet6() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "rosanera89");
        request.setParameter("option", "2");

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("rosanera89");
        accountBean.setEmail("rosanera@mail.com");
        accountBean.setRuolo(1);

        when(accountDAO.doRetrieveByUsername("rosanera89")).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.8_3
    @Test
    void doGet7() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "rosanera89");
        request.setParameter("option", "2");

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("squareenix");
        accountBean.setEmail("squareenix@mail.com");
        accountBean.setRuolo(1);

        when(accountDAO.doRetrieveByUsername(Mockito.any())).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }


    //TC_2.5_1 e TC_2.6_1 e TC_2.7_1 e TC_2.8_1
    @Test
    void doGet1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "");
        request.setParameter("option", "1");

        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("<no/>"));
    }

    //TC_2.5_2 e TC_2.7_2
    @Test
    void doGet2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "rosanera8999");
        request.setParameter("option", "1");

        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("<no/>"));
    }

    //TC_2.5_3
    @Test
    void doGet3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "rosanera89");
        request.setParameter("option", "1");

        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("</ok>"));
    }

    //TC_2.6_2 e TC_2.8_2
    @Test
    void doGet4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "squareenixx");
        request.setParameter("option", "1");

        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("<no/>"));
    }

    //TC_2.6_3
    @Test
    void doGet5I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("username", "squareenix");
        request.setParameter("option", "1");

        servlet.doPost(request, response);
        assertTrue(response.getContentAsString().contains("</ok>"));
    }

    //TC_2.7_3
    @Test
    void doGet6I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        AccountBean accountbean = new AccountBean();
        GiocatoreBean bean = new GiocatoreBean(accountbean);

        bean.setUsername("Giocatore");
        bean.setPassword(Utils.generatePwd("Password1234."));
        bean.setEmail( "account@principale.it");
        bean.setEmailRecupero("account@recupero.it");
        bean.setCellulare( "1234567890");
        bean.setDataNascita( "1990-10-10");
        bean.setGenere( "m");
        bean.setRuolo(1);
        bean.setIp("0.0.0.0");
        bean.setDataIscrizione("2021-06-06");
        accountDAO.doSaveGiocatore(bean);

        request.setParameter("username", bean.getUsername());
        request.setParameter("option", "2");

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }

    //TC_2.8_3
    @Test
    void doGet7I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account",accountBean);

        AccountBean account = new AccountBean();
        CasaEditriceBean casa = new CasaEditriceBean(account);

        casa.setNomeCasaEditrice("Azienda S.r.l.");
        casa.setISIN( "237648450777");
        casa.setCEO("Az Ienda");
        casa.setUsername("Azienda1");
        casa.setPassword("Password1234.");
        casa.setEmail( "azienda@principale.it");
        casa.setEmailRecupero("azienda@recupero.it");
        casa.setCellulare("1234567890");
        casa.setSitoWeb("www.azienda.it");
        casa.setIp("0.0.0.0");
        casa.setData("2021-05-05");
        accountDAO.doSaveCasaEditrice(casa);

        request.setParameter("username", "Azienda1");
        request.setParameter("option", "2");

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getForwardedUrl());
    }
}