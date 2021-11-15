package nytro.control;

import nytro.exceptions.MyException;
import nytro.model.bean.AccountBean;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class RecuperaPasswordTest {
    private RecuperaPassword servlet;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        servlet = new RecuperaPassword();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    // TC_1.3_1
    @Test
    void doGet1() {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_2
    @Test
    void doGet2() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "administrator");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        when(accountDAO.doRetrieveByUsername("administrator")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_3
    @Test
    void doGet3() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_4
    @Test
    void doGet4() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "PASSWORD5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_5
    @Test
    void doGet5() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "password5678.");
        request.setParameter("passwordConferma", "password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_6
    @Test
    void doGet6() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_7
    @Test
    void doGet7() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "");
        request.setParameter("emailRecupero", "account@recupero.it");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_8
    @Test
    void doGet8() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "account@recupero.it");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_9
    @Test
    void doGet9() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_10
    @Test
    void doGet10() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "account");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_11
    @Test
    void doGet11() throws SQLException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "admin@gmail.com");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(new AccountBean());

        servlet.setAccountDAO(accountDAO);
        assertThrows(MyException.class, () -> servlet.doPost(request, response));
    }

    // TC_1.3_12
    @Test
    void doGet12() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = mock(AccountDAO.class);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "adminrecupero@gmail.com");

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setEmailRecupero("adminrecupero@gmail.com");

        when(accountDAO.doRetrieveByUsername("admin")).thenReturn(accountBean);

        servlet.setAccountDAO(accountDAO);
        servlet.doPost(request, response);
        assertEquals("Password aggiornata con successo.", request.getAttribute("message"));
    }

    // TC_1.3_1
    @Test
    void doGet1I() throws SQLException{
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_2
    @Test
    void doGet2I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "administrator");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_3
    @Test
    void doGet3I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_4
    @Test
    void doGet4I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "PASSWORD5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_5
    @Test
    void doGet5I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "password5678.");
        request.setParameter("passwordConferma", "password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_6
    @Test
    void doGet6I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_7
    @Test
    void doGet7I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_8
    @Test
    void doGet8I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "account@recupero.it");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_9
    @Test
    void doGet9I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_10
    @Test
    void doGet10I() throws SQLException {
        MockitoAnnotations.initMocks(this);

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "account");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_11
    @Test
    void doGet11I() throws SQLException {
        MockitoAnnotations.initMocks(this);


        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678");
        request.setParameter("emailRecupero", "admin@gmail.com");

        assertThrows(MyException.class, () -> servlet.doPost(request, response));

        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }

    // TC_1.3_12
    @Test
    void doGet12I() throws SQLException, ServletException, IOException {
        MockitoAnnotations.initMocks(this);
        IAccountDAO accountDAO = new AccountDAO();

        request.setParameter("username", "admin");
        request.setParameter("password", "Password5678.");
        request.setParameter("passwordConferma", "Password5678.");
        request.setParameter("emailRecupero", "adminrecupero@gmail.com");

        servlet.doPost(request, response);
        assertEquals("Password aggiornata con successo.", request.getAttribute("message"));

        AccountBean accountBean = accountDAO.doRetrieveByUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountDAO.doUpdate(accountBean);
    }
}