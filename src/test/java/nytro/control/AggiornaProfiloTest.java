package nytro.control;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.AmministratoreBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;

class AggiornaProfiloTest {
    private AggiornaProfilo servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new AggiornaProfilo();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_3.1_1
    @Test
    void doGetModificaPassword1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.1_2
    @Test
    void doGetModificaPassword2() {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "administrator";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_3.1_3
    @Test
    void doGetModificaPassword3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.1_4
    @Test
    void doGetModificaPassword4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.";
        String cambiaPasswordConferma = "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.1_5
    @Test
    void doGetModificaPassword5() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.1_6
    @Test
    void doGetModificaPassword6() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "PASSWORD1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.1_7
    @Test
    void doGetModificaPassword7() {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_3.1_8
    @Test
    void doGetModificaPassword8() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.1_9
    @Test
    void doGetModificaPassword9() {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_3.1_10
    @Test
    void doGetModificaPassword10() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.2_1
    @Test
    void doGetModificaImmagine1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        MockPart mockPart = new MockPart("photo", null);
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.2_2
    @Test
    void doGetModificaImmagine2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.2_3
    @Test
    void doGetModificaImmagine3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.3_1
    @Test
    void doGetModificaEmail1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmail = null;

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.3_2
    @Test
    void doGetModificaEmail2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmail = "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail@em.it";

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);


        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.3_3
    @Test
    void doGetModificaEmail3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmail = "email";

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.3_4
    @Test
    void doGetModificaEmail4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmail = "email2@principale.it";

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.4_1
    @Test
    void doGetModificaEmailRecupero1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmailRecupero = null;

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.4_2
    @Test
    void doGetModificaEmailRecupero2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmailRecupero = "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail@em.it";

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.4_3
    @Test
    void doGetModificaEmailRecupero3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmailRecupero = "email";

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.4_4
    @Test
    void doGetModificaEmailRecupero4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaEmailRecupero = "email2@principale.it";

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.5_1
    @Test
    void doGetModificaCellulare1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String phone = null;

        request.setParameter("phone",phone);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.5_2
    @Test
    void doGetModificaCellulare2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String phone = "123";

        request.setParameter("phone",phone);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.5_3
    @Test
    void doGetModificaCellulare3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String phone = "1231231231";

        request.setParameter("phone",phone);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.6_1
    @Test
    void doGetModificaNome1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AmministratoreBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaNome = null;

        request.setParameter("cambiaNome",cambiaNome);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.6_2
    @Test
    void doGetModificaNome2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AmministratoreBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaNome = "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome";

        request.setParameter("cambiaNome",cambiaNome);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.6_3
    @Test
    void doGetModificaNome3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AmministratoreBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaNome = "Nome nuovo";

        request.setParameter("cambiaNome",cambiaNome);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.7_1
    @Test
    void doGetModificaCognome1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AmministratoreBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaCognome = null;

        request.setParameter("cambiaCognome",cambiaCognome);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.7_2
    @Test
    void doGetModificaCognome2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AmministratoreBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaCognome = "NoCognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome";

        request.setParameter("cambiaCognome",cambiaCognome);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.7_3
    @Test
    void doGetModificaCognome3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new AmministratoreBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaCognome = "Cognome nuovo";

        request.setParameter("cambiaCognome",cambiaCognome);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.8_1
    @Test
    void doGetModificaSitoWeb1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaSitoWEB = null;

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.8_2
    @Test
    void doGetModificaSitoWeb2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaSitoWEB = "www.sitositositositositosito" +
                "sitositositositositositositosito" +
                "sitositositositositositositosito.it";

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.8_3
    @Test
    void doGetModificaSitoWeb3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaSitoWEB = "sito";

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.8_4
    @Test
    void doGetModificaSitoWeb4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaSitoWEB = "www.sito.it";

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.9_1
    @Test
    void doGetModificaCeo1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaCEO = null;

        request.setParameter("cambiaCEO",cambiaCEO);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.9_2
    @Test
    void doGetModificaCeo2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaCEO = "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO";

        request.setParameter("cambiaCEO",cambiaCEO);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.9_3
    @Test
    void doGetModificaCeo3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaCEO = "CEO";

        request.setParameter("cambiaCEO",cambiaCEO);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.10_1
    @Test
    void doGetModificaNomeCasaEditrice1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaNomeCasaEditrice = null;

        request.setParameter("cambiaNomeCasaEditrice",cambiaNomeCasaEditrice);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.10_2
    @Test
    void doGetModificaNomeCasaEditrice2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaNomeCasaEditrice = "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO";

        request.setParameter("cambiaNomeCasaEditrice",cambiaNomeCasaEditrice);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.10_3
    @Test
    void doGetModificaNomeCasaEditrice3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        AccountBean accountBean = new CasaEditriceBean(new AccountBean());
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        String cambiaNomeCasaEditrice = "Nome nuovo";

        request.setParameter("cambiaNomeCasaEditrice",cambiaNomeCasaEditrice);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    /***/

    // TC_3.1_1
    @Test
    void doGetModificaPassword1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_2
    @Test
    void doGetModificaPassword2I() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "administrator";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_3
    @Test
    void doGetModificaPassword3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_4
    @Test
    void doGetModificaPassword4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.";
        String cambiaPasswordConferma = "Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_5
    @Test
    void doGetModificaPassword5I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_6
    @Test
    void doGetModificaPassword6I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "PASSWORD1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_7
    @Test
    void doGetModificaPassword7I() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_8
    @Test
    void doGetModificaPassword8I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_9
    @Test
    void doGetModificaPassword9I() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.1_10
    @Test
    void doGetModificaPassword10I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String vecchiaPassword = "admin";
        String cambiaPassword = "Password1234.";
        String cambiaPasswordConferma = "Password1234.";

        request.setParameter("cambiaPassword",cambiaPassword);
        request.setParameter("cambiaPasswordConferma",cambiaPasswordConferma);
        request.setParameter("vecchiaPassword",vecchiaPassword);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.2_1
    @Test
    void doGetModificaImmagine1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        MockPart mockPart = new MockPart("photo", null);
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.2_2
    @Test
    void doGetModificaImmagine2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.2_3
    @Test
    void doGetModificaImmagine3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        MockPart mockPart = new MockPart("photo", "ImmagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setAccountDAO(accountDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountDAO.doUploadImage(accountBean);
    }

    // TC_3.3_1
    @Test
    void doGetModificaEmail1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));

        String cambiaEmail = null;

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }


    // TC_3.3_2
    @Test
    void doGetModificaEmail2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");


        String cambiaEmail = "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail@em.it";

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_3.3_3
    @Test
    void doGetModificaEmail3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaEmail = "email";

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.3_4
    @Test
    void doGetModificaEmail4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaEmail = "email2@principale.it";

        request.setParameter("cambiaEmail",cambiaEmail);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setEmail("siteadmin@gmail.com");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.4_1
    @Test
    void doGetModificaEmailRecupero1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaEmailRecupero = null;

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.4_2
    @Test
    void doGetModificaEmailRecupero2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaEmailRecupero = "emailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemailemail@em.it";

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);


        assertThrows(MyException.class, () ->servlet.doPost(request, response));
    }

    // TC_3.4_3
    @Test
    void doGetModificaEmailRecupero3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new  AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaEmailRecupero = "email";

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.4_4
    @Test
    void doGetModificaEmailRecupero4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaEmailRecupero = "email2@principale.it";

        request.setParameter("cambiaEmailRecupero",cambiaEmailRecupero);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setEmailRecupero("adminrecupero@gmail.com");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.5_1
    @Test
    void doGetModificaCellulare1I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("luigiverdi94");

        String phone = null;

        request.setParameter("phone",phone);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.5_2
    @Test
    void doGetModificaCellulare2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("luigiverdi94");

        String phone = "123";

        request.setParameter("phone",phone);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.5_3
    @Test
    void doGetModificaCellulare3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("luigiverdi94");

        String phone = "1231231231";

        request.setParameter("phone",phone);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        accountBean.setCellulare("3391234567");
        accountDAO.doUpdate(accountBean);
    }

    // TC_3.6_1
    @Test
    void doGetModificaNome1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaNome = null;

        request.setParameter("cambiaNome",cambiaNome);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.6_2
    @Test
    void doGetModificaNome2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaNome = "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome";

        request.setParameter("cambiaNome",cambiaNome);

        request.getSession().setAttribute("account", accountBean);

        assertThrows(ClassCastException.class, ()-> servlet.doPost(request, response) );
    }

    // TC_3.6_3
    @Test
    void doGetModificaNome3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        AmministratoreBean admin = (AmministratoreBean) accountDAO.doRetrieveDetailed(accountBean);

        String cambiaNome = "Nome nuovo";

        request.setParameter("cambiaNome",cambiaNome);

        request.getSession().setAttribute("account", admin);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        admin.setNome("Veronica");
        accountDAO.doUpdateAmministratore(admin);
    }

    // TC_3.7_1
    @Test
    void doGetModificaCognome1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaCognome = null;

        request.setParameter("cambiaCognome",cambiaCognome);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.7_2
    @Test
    void doGetModificaCognome2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        String cambiaCognome = "NoCognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome" +
                "Cognome Cognome Cognome";

        request.setParameter("cambiaCognome",cambiaCognome);

        request.getSession().setAttribute("account", accountBean);

        assertThrows(ClassCastException.class, ()-> servlet.doPost(request, response) );
    }

    // TC_3.7_3
    @Test
    void doGetModificaCognome3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        AmministratoreBean admin = (AmministratoreBean) accountDAO.doRetrieveDetailed(accountBean);

        String cambiaCognome = "Cognome nuovo";

        request.setParameter("cambiaCognome",cambiaCognome);

        request.getSession().setAttribute("account", admin);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        admin.setCognome("Volpicelli");
        accountDAO.doUpdateAmministratore(admin);
    }

    // TC_3.8_1
    @Test
    void doGetModificaSitoWeb1I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaSitoWEB = null;

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.8_2
    @Test
    void doGetModificaSitoWeb2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaSitoWEB = "www.sitositositositositosito" +
                "sitositositositositositositosito" +
                "sitositositositositositositosito.it";

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", casa);

        assertThrows(MyException.class, () ->servlet.doPost(request, response));
    }

    // TC_3.8_3
    @Test
    void doGetModificaSitoWeb3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaSitoWEB = "sito";

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.8_4
    @Test
    void doGetModificaSitoWeb4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);
        String cambiaSitoWEB = "www.sito.it";

        request.setParameter("cambiaSitoWEB",cambiaSitoWEB);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        casa.setSitoWeb("www.ubisoft.com");
        accountDAO.doUpdateCasaEditrice(casa);
    }

    // TC_3.9_1
    @Test
    void doGetModificaCeo1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaCEO = null;

        request.setParameter("cambiaCEO",cambiaCEO);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.9_2
    @Test
    void doGetModificaCeo2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaCEO = "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO" +
                "CEO CEO CEO CEO CEO";

        request.setParameter("cambiaCEO",cambiaCEO);

        request.getSession().setAttribute("account", casa);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_3.9_3
    @Test
    void doGetModificaCeo3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaCEO = "CEO";

        request.setParameter("cambiaCEO",cambiaCEO);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        casa.setCEO("Yves Guillemot");
        accountDAO.doUpdateCasaEditrice(casa);
    }

    // TC_3.10_1
    @Test
    void doGetModificaNomeCasaEditrice1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaNomeCasaEditrice = null;

        request.setParameter("cambiaNomeCasaEditrice",cambiaNomeCasaEditrice);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());
    }

    // TC_3.10_2
    @Test
    void doGetModificaNomeCasaEditrice2I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaNomeCasaEditrice = "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" +
                "Nome Nome Nome Nome Nome" ;

        request.setParameter("cambiaNomeCasaEditrice",cambiaNomeCasaEditrice);

        request.getSession().setAttribute("account", casa);

        assertThrows(MyException.class, ()-> servlet.doPost(request, response));
    }

    // TC_3.10_3
    @Test
    void doGetModificaNomeCasaEditrice3I() throws ServletException, IOException, SQLException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");
        CasaEditriceBean casa = accountDAO.doRetrieveCasaEditrice(accountBean);

        String cambiaNomeCasaEditrice = "Nome nuovo";

        request.setParameter("cambiaNomeCasaEditrice",cambiaNomeCasaEditrice);

        request.getSession().setAttribute("account", casa);

        servlet.doPost(request, response);
        assertEquals("jsp/profilo.jsp", response.getForwardedUrl());

        casa.setNomeCasaEditrice("Ubisoft");
        accountDAO.doUpdateCasaEditrice(casa);
    }
}