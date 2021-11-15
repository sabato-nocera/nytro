package nytro.control;

import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IVideogiocoDAO;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LibreriaTest {
    private Libreria servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

   @BeforeEach
   void setUp(){
       servlet = new Libreria();
       request = new MockHttpServletRequest();
       response = new MockHttpServletResponse();
   }

    //TC_8.1_1
    @Test
    void ordinareLibreria1()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        String order = "";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_2
    @Test
    void ordinareLibreria2()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        String order = "Data_Rilascio";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_3
    @Test
    void ordinareLibreria3()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        String order = "Titolo";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_4
    @Test
    void ordinareLibreria4()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        String order = "Voto_Medio";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_5
    @Test
    void ordinareLibreria5()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        String order = "PEGI";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.2
    @Test
    void includereLibreria()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        request.setParameter("aggiungiVideogioco","1");
        request.getSession().setAttribute("account",accountBean);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertNull(response.getForwardedUrl());
    }

   //TC_8.3_1
    @Test
    void escludereDallaLibreria()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IVideogiocoDAO videogiocoDAO = mock(VideogiocoDAO.class);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        String cancellaVideogioco = "1";
        request.setParameter("cancellaVideogioco",cancellaVideogioco);
        request.getSession().setAttribute("account",accountBean);

        String order = "titolo";
        request.setParameter("order",order);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertNull(response.getForwardedUrl());
    }

    //TC_8.1_1
    @Test
    void ordinareLibreria1I()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        String order = "";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_2
    @Test
    void ordinareLibreria2I()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        String order = "Data_Rilascio";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_3
    @Test
    void ordinareLibreria3I()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        String order = "Titolo";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_4
    @Test
    void ordinareLibreria4I()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        String order = "Voto_Medio";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.1_5
    @Test
    void ordinareLibreria5I()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        String order = "PEGI";
        request.setParameter("order",order);
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/libreria.jsp", response.getForwardedUrl());
    }

    //TC_8.2
    @Test
    void includereLibreriaI()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        request.setParameter("aggiungiVideogioco","3");
        request.getSession().setAttribute("account",accountBean);

        servlet.doPost(request, response);
        assertNull(response.getForwardedUrl());

        videogiocoDAO.doDeleteFromLibreria(accountBean.getUsername(),3);
    }

    //TC_8.3_1
    @Test
    void escludereDallaLibreriaI()  throws SQLException, ServletException, IOException  {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");

        String cancellaVideogioco = "1";
        request.setParameter("cancellaVideogioco",cancellaVideogioco);
        request.getSession().setAttribute("account",accountBean);

        String order = "titolo";
        request.setParameter("order",order);

        servlet.setVideogiocoDAO(videogiocoDAO);
        servlet.doPost(request, response);
        assertNull(response.getForwardedUrl());

        videogiocoDAO.doSaveToLibreria(accountBean.getUsername(),1);
    }
}