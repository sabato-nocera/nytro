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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

class FriendlistTest {
    private Friendlist servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new Friendlist();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_10.1_1
    @Test
    void doGetAggiungereAmico1() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

    // TC_10.1_2
    @Test
    void doGetAggiungereAmico2() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.addParameter("futuroAmico", "giallo");

        servlet.setAccountDAO(accountDAO);

        doThrow(Exception.class).when(accountDAO).doAggiungiAmicoFriendlist(any(),any());

        assertThrows(Exception.class, () -> servlet.doPost(request, response));
    }

    // TC_10.1_3
    @Test
    void doGetAggiungereAmico3() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.addParameter("futuroAmico", "rosanera89");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

    // TC_10.2_1
    @Test
    void doGetRimuoviAmico2() throws ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO  = mock(AccountDAO.class);
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");
        request.getSession().setAttribute("account", accountBean);

        request.addParameter("eliminatoAmico", "rosanera89");

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

    // TC_10.1_1
    @Test
    void doGetAggiungereAmico1I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("lucagialli01");

        request.getSession().setAttribute("account", accountBean);

        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

    // TC_10.1_2
    @Test
    void doGetAggiungereAmico2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("lucagialli01");
        request.getSession().setAttribute("account", accountBean);

        request.addParameter("futuroAmico", "giallo");

        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

    // TC_10.1_3
    @Test
    void doGetAggiungereAmico3I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("lucagialli01");
        request.getSession().setAttribute("account", accountBean);

        request.addParameter("futuroAmico", "rosanera89");

        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

    // TC_10.2_1
    @Test
    void doGetRimuoviAmico2I() throws ServletException, IOException, SQLException {
        MockitoAnnotations.initMocks(this);
        AccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("lucagialli01");
        request.getSession().setAttribute("account", accountBean);

        request.addParameter("eliminatoAmico", "rosanera89");

        servlet.doPost(request, response);
        assertEquals("jsp/friendlist.jsp", response.getForwardedUrl());
    }

}