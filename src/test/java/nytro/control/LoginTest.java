package nytro.control;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import nytro.exceptions.MyException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class LoginTest {
    private Login servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Login();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    //TC_1.1_1
    @Test
    void doLogin1() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        String username = null;
        request.setParameter("username",username);
        String password = "admin";
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_2
    @Test
    void doLogin2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        String username = "administrator";
        request.setParameter("username",username);
        String password = "admin";
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        when(accountDAO.doRetrieveByUsernamePassword(username,password)).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_3
    @Test
    void doLogin3() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        String username = "admin";
        request.setParameter("username",username);
        String password = null;
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        when(accountDAO.doRetrieveByUsernamePassword(username,password)).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_4
    @Test
    void doLogin4() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        String username = "admin";
        request.setParameter("username",username);
        String password = "admin1234.";
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        when(accountDAO.doRetrieveByUsernamePassword(username,password)).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_5
    @Test
    void doLogin5() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        String username = "admin";
        request.setParameter("username",username);
        String password = "admin";
        request.setParameter("password",password);
        int ruolo =0;

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);
        accountBean.setRuolo(ruolo);

        when(accountDAO.doRetrieveByUsernamePassword(username, Utils.generatePwd(password))).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getRedirectedUrl());
    }

    /***/

    // TC_1.1_1
    @Test
    void doLogin1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        String username = null;
        request.setParameter("username",username);
        String password = "admin";
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_2
    @Test
    void doLogin2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        String username = "administrator";
        request.setParameter("username",username);
        String password = "admin";
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_3
    @Test
    void doLogin3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        String username = "admin";
        request.setParameter("username",username);
        String password = null;
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_4
    @Test
    void doLogin4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        String username = "admin";
        request.setParameter("username",username);
        String password = "admin1234.";
        request.setParameter("password",password);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    //TC_1.1_5
    @Test
    void doLogin5I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);

        String username = "admin";
        request.setParameter("username",username);
        String password = "admin";
        request.setParameter("password",password);
        int ruolo =0;

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername(username);
        accountBean.setRuolo(ruolo);

        servlet.doPost(request, response);
        assertEquals("jsp/index.jsp", response.getRedirectedUrl());
    }
}