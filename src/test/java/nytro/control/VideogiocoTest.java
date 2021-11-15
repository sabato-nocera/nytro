package nytro.control;

import nytro.exceptions.MyException;
import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.AccountBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.RecensioneBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.AccountDAO;
import nytro.model.dao.RecensioneDAO;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IRecensioneDAO;
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
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class VideogiocoTest {
    private Videogioco servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Videogioco();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_4.1_1
    @Test
    void doGetModificaImmagine1() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        MockPart mockPart = new MockPart("photo", null);
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setVideogiocoDAO(videogiocoDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.1_2
    @Test
    void doGetModificaImmagine2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setVideogiocoDAO(videogiocoDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.1_3
    @Test
    void doGetModificaImmagine3() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO  = mock(VideogiocoDAO.class);

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);
        servlet.setVideogiocoDAO(videogiocoDAO);

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_1
    @Test
    void doGetTrailer1() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);


        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaTrailer", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_2
    @Test
    void doGetTrailer2() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaTrailer", "VideoVideoVideoVideoVideoVideoVideoVideoVideoVideoVideoVideo");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_3
    @Test
    void doGetTrailer3() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);


        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaTrailer", "trailer");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_4
    @Test
    void doGetTrailer4() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);


        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaTrailer", "https://www.youtube.com/embed/TUzZKLirkrE");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.3_1
    @Test
    void doGetCambiaGenere1() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaGenere", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.3_2
    @Test
    void doGetCambiaGenere2() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaGenere", "cambiaGenere");
        request.setParameter("newGenere", "GenereGenereGenere");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.3_3
    @Test
    void doGetCambiaGenere3() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaGenere", "cambiaGenere");
        request.setParameter("newGenere", "RPG");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.4_1
    @Test
    void doGetCambiaPrezzo1() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaPrezzo", "cambiaPrezzo");
        request.setParameter("newPrezzo", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.4_2
    @Test
    void doGetCambiaPrezzo2() throws SQLException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaPrezzo", "cambiaPrezzo");
        request.setParameter("newPrezzo", ".");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_4.4_3
    @Test
    void doGetCambiaPrezzo3() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);

        request.setParameter("cambiaPrezzo", "cambiaPrezzo");
        request.setParameter("newPrezzo", "50");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_1
    @Test
    void doGetAggiungiRecensione1() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("votoRecensione", "");
        request.setParameter("commentoRecensione", "Bellissimo");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_2
    @Test
    void doGetAggiungiRecensione2() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("votoRecensione", "9");
        request.setParameter("commentoRecensione", "Bellissimo");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_3
    @Test
    void doGetAggiungiRecensione3() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("votoRecensione", "-.-");
        request.setParameter("commentoRecensione", "Bellissimo");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.1_4
    @Test
    void doGetAggiungiRecensione4() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("votoRecensione", "5");
        request.setParameter("commentoRecensione", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_5
    @Test
    void doGetAggiungiRecensione5() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("votoRecensione", "5");
        request.setParameter("commentoRecensione", "commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooo");

        doThrow(Exception.class).when(recensioneDAO).doSave(any());

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.1_6
    @Test
    void doGetAggiungiRecensione6() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("votoRecensione", "5");
        request.setParameter("commentoRecensione", "Videogioco Bellissimo!");
        when(recensioneDAO.doCheck(any())).thenReturn(true);

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.2_1
    @Test
    void doGetRimuoviRecensione2() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);


        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rimuovereRecensione", "rimuovereRecensione");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_1
    @Test
    void doGetFiltrareRecensioni1() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_2
    @Test
    void doGetFiltrareRecensioni2() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "+");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.3_3
    @Test
    void doGetFiltrareRecensioni3() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "");
        request.setParameter("max", "1");
        request.setParameter("orderRecensioni", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_4
    @Test
    void doGetFiltrareRecensioni4() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "+");
        request.setParameter("orderRecensioni", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.3_5
    @Test
    void doGetFiltrareRecensioni5() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_6
    @Test
    void doGetFiltrareRecensioni6() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "Num_Recensione");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_7
    @Test
    void doGetFiltrareRecensioni7() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "Username");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_8
    @Test
    void doGetFiltrareRecensioni8() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);
        IRecensioneDAO recensioneDAO = mock(RecensioneDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setConsole(new ArrayList<>());
        videogiocoBean.getConsole().add("PS4");
        videogiocoBean.getConsole().add("PS5");
        videogiocoBean.setGeneri(new ArrayList<>());
        videogiocoBean.getGeneri().add("Picchiaduro");
        videogiocoBean.getGeneri().add("Azione");
        videogiocoBean.setCodice(1);
        videogiocoBean.setTitolo("Final Fantasy XIV");

        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "1");
        when(videogiocoDAO.doRetrieveByCodice(1, null)).thenReturn(videogiocoBean);
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "Voto");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }



    // TC_4.1_1
    @Test
    void doGetModificaImmagine1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");

        request.setParameter("codiceVideogioco", "1");

        MockPart mockPart = new MockPart("photo", null);
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.1_2
    @Test
    void doGetModificaImmagine2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        request.setParameter("codiceVideogioco", "1");

        AccountBean accountBean = accountDAO.doRetrieveByUsername("ubisoft");

        MockPart mockPart = new MockPart("photo", "Prova.txt", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.1_3
    @Test
    void doGetModificaImmagine3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        request.setParameter("codiceVideogioco", "1");

        CasaEditriceBean accountBean = accountDAO.doRetrieveCasaEditrice(accountDAO.doRetrieveByUsername("ubisoft"));
        VideogiocoBean videogiocoBean = videogiocoDAO.doRetrieveByCodice(1, accountBean.getISIN());

        MockPart mockPart = new MockPart("photo", "immagineProfilo.jpg", "prova".getBytes(StandardCharsets.UTF_8));
        request.addPart(mockPart);

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());

        videogiocoDAO.doUpdate(videogiocoBean);
    }

    // TC_4.2_1
    @Test
    void doGetTrailer1I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaTrailer", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_2
    @Test
    void doGetTrailer2I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaTrailer", "VideoVideoVideoVideoVideoVideoVideoVideoVideoVideoVideoVideo");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_3
    @Test
    void doGetTrailer3I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaTrailer", "trailer");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.2_4
    @Test
    void doGetTrailer4I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaTrailer", "https://www.youtube.com/embed/TUzZKLirkrE");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());

        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(2,"");
        gioco.setTrailer("https://www.youtube.com/embed/TUzZKLirkrE");
        videogiocoDAO.doUpdate(gioco);
    }

    // TC_4.3_1
    @Test
    void doGetCambiaGenere1I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaGenere", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.3_2
    @Test
    void doGetCambiaGenere2I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaGenere", "cambiaGenere");
        request.setParameter("newGenere", "GenereGenereGenere");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_4.3_3
    @Test
    void doGetCambiaGenere3I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaGenere", "cambiaGenere");
        request.setParameter("newGenere", "RPG");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());

        doDeleteFromGenere("RPG", 2);
    }

    // TC_4.4_1
    @Test
    void doGetCambiaPrezzo1I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaPrezzo", "cambiaPrezzo");
        request.setParameter("newPrezzo", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_4.4_2
    @Test
    void doGetCambiaPrezzo2I() throws SQLException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");
        request.setParameter("cambiaPrezzo", "cambiaPrezzo");
        request.setParameter("newPrezzo", ".");

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_4.4_3
    @Test
    void doGetCambiaPrezzo3I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("nintendo");

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("codiceVideogioco", "2");

        request.setParameter("cambiaPrezzo", "cambiaPrezzo");
        request.setParameter("newPrezzo", "50");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());

        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(2,"");
        gioco.setPrezzo(69.99F);
        videogiocoDAO.doUpdate(gioco);
    }

    // TC_5.1_1
    @Test
    void doGetAggiungiRecensione1I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("votoRecensione", "");
        request.setParameter("commentoRecensione", "Bellissimo");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_2
    @Test
    void doGetAggiungiRecensione2I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("votoRecensione", "9");
        request.setParameter("commentoRecensione", "Bellissimo");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_3
    @Test
    void doGetAggiungiRecensione3I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("votoRecensione", "-.-");
        request.setParameter("commentoRecensione", "Bellissimo");

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.1_4
    @Test
    void doGetAggiungiRecensione4I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("votoRecensione", "5");
        request.setParameter("commentoRecensione", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.1_5
    @Test
    void doGetAggiungiRecensione5I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("votoRecensione", "5");
        request.setParameter("commentoRecensione", "commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooocommentooo commentooo");

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.1_6
    @Test
    void doGetAggiungiRecensione6I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("votoRecensione", "5");
        request.setParameter("commentoRecensione", "Videogioco Bellissimo!");


        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());

        RecensioneBean rec = recensioneDAO.doRetrieveByUsername("mariorossi91",3);
        recensioneDAO.doDelete(rec);
    }

    // TC_5.2_1
    @Test
    void doGetRimuoviRecensione2I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");

        if(recensioneDAO.doRetrieveByUsername("mariorossi91",3) != null){
            RecensioneBean rec = new RecensioneBean();
            rec.setNumRecensione(3);
            rec.setVoto(5F);
            rec.setCodVideogioco(3);
            rec.setUsername("mariorossi91");
            rec.setCommento("Bellissimo");
            recensioneDAO.doSave(rec);
        }

        request.setParameter("rimuovereRecensione", "rimuovereRecensione");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_1
    @Test
    void doGetFiltrareRecensioni1I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_2
    @Test
    void doGetFiltrareRecensioni2I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "+");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "");

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.3_3
    @Test
    void doGetFiltrareRecensioni3I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "");
        request.setParameter("max", "1");
        request.setParameter("orderRecensioni", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_4
    @Test
    void doGetFiltrareRecensioni4I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "+");
        request.setParameter("orderRecensioni", "");

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_5.3_5
    @Test
    void doGetFiltrareRecensioni5I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_6
    @Test
    void doGetFiltrareRecensioni6I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "Num_Recensione");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_7
    @Test
    void doGetFiltrareRecensioni7I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "Username");

        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    // TC_5.3_8
    @Test
    void doGetFiltrareRecensioni8I() throws SQLException, ServletException, IOException{
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
        IRecensioneDAO recensioneDAO = new RecensioneDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.setParameter("codiceVideogioco", "3");
        request.setParameter("rangeRecensione", "rangeRecensione");
        request.setParameter("min", "1");
        request.setParameter("max", "5");
        request.setParameter("orderRecensioni", "Voto");

        servlet.setAccountDAO(accountDAO);
        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.setRecensioneDAO(recensioneDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/videogioco.jsp", response.getForwardedUrl());
    }

    public boolean doDeleteFromGenere(String gen, int codiceVideogiocoDaCancellare) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        int result;

        String deleteSQL="DELETE FROM Genere WHERE Nome = ? AND Videogioco = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setString(1, gen);
            preparedStatement.setInt(2, codiceVideogiocoDaCancellare);

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