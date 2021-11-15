package nytro.control;

import nytro.model.bean.AccountBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ElencoCaseEditriciTest {
    private ElencoCaseEditrici servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new ElencoCaseEditrici();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_6.1_1
    @Test
    void doGet1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        String order = "";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_2
    @Test
    void doGet2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        String order = "account.Username";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_3
    @Test
    void doGet3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        String order = "account.Data";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_4
    @Test
    void doGet4() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        String order = "account.IP";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_1
    @Test
    void doGet1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        String order = "";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_2
    @Test
    void doGet2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        String order = "account.Username";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_3
    @Test
    void doGet3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        String order = "account.Data";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

    // TC_6.1_4
    @Test
    void doGet4I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = new AccountDAO();

        AccountBean accountBean = accountDAO.doRetrieveByUsername("mariorossi91");
        String order = "account.IP";

        request.getSession().setAttribute("account", accountBean);
        request.setParameter("order", order);

        servlet.doPost(request, response);
        assertEquals("jsp/elencoCaseEditrici.jsp", response.getForwardedUrl());
    }

}