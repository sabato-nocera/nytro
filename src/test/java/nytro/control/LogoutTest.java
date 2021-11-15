package nytro.control;

import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.dao.VideogiocoDAO;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IVideogiocoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

class LogoutTest {
    private Logout servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Logout();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_1.2_1
    @Test
    void doGetLogout() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        accountBean.setPassword("e4187f73b067afa42e5d24245e770c65a6038927");

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);

        assertNull(request.getSession().getAttribute("account"));
    }

    // TC_1.2_1
    @Test
    void doGetLogoutI() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);

        assertNull(request.getSession().getAttribute("account"));
    }
}