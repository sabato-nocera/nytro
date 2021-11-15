package nytro.control;

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

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class CatalogoCasaEditriceTest {
    private CatalogoCasaEditrice servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new CatalogoCasaEditrice();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_6.2_1
    @Test
    void doGet1() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "";
        String genere = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("Picchiaduro");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_2
    @Test
    void doGet2() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "Data_Rilascio";
        String genere = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("Picchiaduro");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_3
    @Test
    void doGet3() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "Titolo";
        String genere = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("Picchiaduro");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_4
    @Test
    void doGet4() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "Voto_Medio";
        String genere = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("Picchiaduro");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_5
    @Test
    void doGet5() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "PEGI";
        String genere = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("Picchiaduro");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_6
    @Test
    void doGet6() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "";
        String genere = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("Picchiaduro");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_7
    @Test
    void doGet7() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "";
        String genere = "MMORPG";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();
        VideogiocoBean tmp1 = new VideogiocoBean();
        tmp1.setCodice(1);
        tmp1.setDataRimozione(null);
        tmp1.setGeneri(new ArrayList<>());
        tmp1.getGeneri().add("Picchiaduro");
        tmp1.getGeneri().add("Azione");
        VideogiocoBean tmp2 = new VideogiocoBean();
        tmp2.setCodice(2);
        tmp2.setGeneri(new ArrayList<>());
        tmp2.setDataRimozione(null);
        tmp2.getGeneri().add("Azione");
        tmp2.getGeneri().add("MMORPG");
        VideogiocoBean tmp3 = new VideogiocoBean();
        tmp3.setCodice(3);

        videogiocoBeanList.add(tmp1);
        videogiocoBeanList.add(tmp2);
        videogiocoBeanList.add(tmp3);

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        when(videogiocoDAO.doRetrieveAll(order, "123456789012")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_1
    @Test
    void doGet1I() throws SQLException, ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        String order = "";
        String genere = "";
        String isin = "675648205634";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", isin);

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_2
    @Test
    void doGet2I() throws SQLException, ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        String order = "Data_Rilascio";
        String genere = "";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_3
    @Test
    void doGet3I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();

        String order = "Titolo";
        String genere = "";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_4
    @Test
    void doGet4I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        String order = "Voto_Medio";
        String genere = "";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");


        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_5
    @Test
    void doGet5I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        String order = "PEGI";
        String genere = "";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_6
    @Test
    void doGet6I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        String order = "";
        String genere = "";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }

    // TC_6.2_7
    @Test
    void doGet7I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        String order = "";
        String genere = "MMORPG";

        request.setParameter("order", order);
        request.setParameter("genere", genere);
        request.setParameter("isinCasaEditrice", "123456789012");

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogoCasaEditrice.jsp", response.getForwardedUrl());
    }
}