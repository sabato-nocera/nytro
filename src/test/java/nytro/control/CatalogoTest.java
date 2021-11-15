package nytro.control;

import nytro.model.bean.AccountBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.AccountDAO;
import nytro.model.dao.VideogiocoDAO;
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

class CatalogoTest {
    private Catalogo servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Catalogo();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_4.5_1
    @Test
    void doGet1() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "Data_Rilascio";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();

        request.setParameter("order", order);

        when(videogiocoDAO.doRetrieveAll(order, "")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_2
    @Test
    void doGet2() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "Titolo";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();

        request.setParameter("order", order);

        when(videogiocoDAO.doRetrieveAll(order, "")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_3
    @Test
    void doGet3() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "Voto_Medio";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();

        request.setParameter("order", order);

        when(videogiocoDAO.doRetrieveAll(order, "")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_4
    @Test
    void doGet4() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "PEGI";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();

        request.setParameter("order", order);

        when(videogiocoDAO.doRetrieveAll(order, "")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_5
    @Test
    void doGet5() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        String order = "";

        List<VideogiocoBean> videogiocoBeanList = new ArrayList<>();

        request.setParameter("order", order);

        when(videogiocoDAO.doRetrieveAll(order, "")).thenReturn(videogiocoBeanList);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_1
    @Test
    void doGet1I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();

        String order = "Data_Rilascio";

        request.setParameter("order", order);

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_2
    @Test
    void doGet2I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();

        String order = "Titolo";

        request.setParameter("order", order);

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_3
    @Test
    void doGet3I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();

        String order = "Voto_Medio";

        request.setParameter("order", order);

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_4
    @Test
    void doGet4I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();

        String order = "PEGI";

        request.setParameter("order", order);

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }

    // TC_4.5_5
    @Test
    void doGet5I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();

        String order = "";

        request.setParameter("order", order);

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/catalogo.jsp", response.getForwardedUrl());
    }
}