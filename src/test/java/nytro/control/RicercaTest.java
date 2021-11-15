package nytro.control;

import nytro.exceptions.MyException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RicercaTest {
    private Ricerca servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Ricerca();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_4.6_1
    @Test
    void doGet1() {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        request.setParameter("testoParziale", "");

        servlet.setVideogiocoDAO(videogiocoDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_4.6_2
    @Test
    void doGet2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        request.setParameter("testoParziale", "titolotitolotitolotitolotitolotitolo");

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/ricerca.jsp", response.getForwardedUrl());
        List<VideogiocoBean> list = (List<VideogiocoBean>) request.getAttribute("listaVideogiochi");
        assertEquals(0, list.size());
    }

    // TC_4.6_3
    @Test
    void doGet() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        request.setParameter("testoParziale", "Super Smash Bros. Ultimate");

        List<VideogiocoBean> videogiochi = new ArrayList<>();
        VideogiocoBean videogiocoBean = new VideogiocoBean();
        videogiocoBean.setTitolo("Super Smash Bros. Ultimate");
        videogiochi.add(videogiocoBean);

        when(videogiocoDAO.doRetrieveByTitolo("Super Smash Bros. Ultimate")).thenReturn(videogiochi);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/ricerca.jsp", response.getForwardedUrl());
        List<VideogiocoBean> list = (List<VideogiocoBean>) request.getAttribute("listaVideogiochi");
        assertEquals(1, list.size());
        assertEquals("Super Smash Bros. Ultimate", list.get(0).getTitolo());
    }

    // TC_4.6_1
    @Test
    void doGet1I() throws SQLException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("testoParziale", "");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_4.6_2
    @Test
    void doGet2I() throws ServletException, IOException,SQLException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);

        request.setParameter("testoParziale", "titolotitolotitolotitolotitolotitolo");

        servlet.doPost(request, response);
        assertEquals("jsp/ricerca.jsp", response.getForwardedUrl());
    }

    // TC_4.6_3
    @Test
    void doGetI() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        request.getSession().setAttribute("account",accountBean);
        request.setParameter("testoParziale", "Super Smash Bros. Ultimate");

        servlet.doPost(request, response);
        assertEquals("jsp/ricerca.jsp", response.getForwardedUrl());
        List<VideogiocoBean> list = (List<VideogiocoBean>) request.getAttribute("listaVideogiochi");
        assertEquals(1, list.size());
        assertEquals("Super Smash Bros. Ultimate", list.get(0).getTitolo());
    }
}