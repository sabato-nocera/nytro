package nytro.filter;

import nytro.model.bean.AccountBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;
import nytro.model.dao.AccountDAO;
import nytro.model.idao.IAccountDAO;
import nytro.other.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.mock;

class LogFilterTest {
    private LogFilter logFilter;
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;

    @BeforeEach
    void setUp() {
        logFilter = new LogFilter();
        request = new MockHttpServletRequest();
        response = new MockHttpServletResponse();
    }

    @Test
    void doFilterAccountNullo() throws IOException, ServletException {
        FilterChain filterChain = mock(FilterChain.class);

        logFilter.doFilter(request, response, filterChain);

        assertEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
    }

    @Test
    void doFilterOspite() throws IOException, ServletException {
        String[] noRuolo = {"Ricerca", "RicercaAjax", "ricerca.jsp", "Login", "LoginForm", "RecuperaPassword", "RegistrazioneForm", "RegistrazioneUtente", "VerificaUsername", "error.jsp", "VerificaIsin", "loginForm.jsp", "registrazioneForm.jsp", "image"};

        for (String path : noRuolo) {
            FilterChain filterChain = mock(FilterChain.class);
            request.setRequestURI(path);
            logFilter.doFilter(request, response, filterChain);
            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
        }
    }

    @Test
    void doFilterAdmin() throws IOException, ServletException, SQLException {
        String[] adminRuolo = {"Ricerca", "RicercaAjax", "ricerca.jsp", "AggiornaProfilo", "Catalogo", "CatalogoCasaEditrice", "ListaCaseEditrici", "RegistrazioneCasaEditrice", "RimozioneAccount", "VerificaIsin", "ListaGiocatori", "Logout", "Profilo", "VerificaUsername", "Videogioco", "error.jsp", "profilo.jsp", "listaCaseEditrici.jsp", "listaGiocatori.jsp", "registrazioneCasaEditrice.jsp", "rimozioneAccount.jsp", "videogioco.jsp", "image", "elencoCaseEditrici.jsp", "ElencoCaseEditrici"};

        for (String path : adminRuolo) {
            FilterChain filterChain = mock(FilterChain.class);
            AccountBean accountBean = new AccountBean();
            accountBean.setUsername("admin");
            accountBean.setRuolo(0);

            request.getSession().setAttribute("account", accountBean);
            request.setRequestURI(path);

            logFilter.doFilter(request, response, filterChain);

            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
        }
    }

    @Test
    void doFilterGiocatore() throws IOException, ServletException, SQLException {
        String[] giocatoreRuolo = {"Ricerca", "RicercaAjax", "RicercaGiocatoreAjax", "ricerca.jsp", "AggiornaProfilo", "AggiungiFriend", "Catalogo", "CatalogoCasaEditrice", "Friendlist", "GestoreCarrello", "VerificaIsin", "Libreria", "ElencoCaseEditrici", "Logout", "Profilo", "RimuoviFriend", "VerificaUsername", "Videogioco", "error.jsp", "profilo.jsp", "carrello.jsp", "catalogo.jsp", "catalogoCasaEditrice.jsp", "friendlist.jsp", "libreria.jsp", "elencoCaseEditrici.jsp", "videogioco.jsp", "image", "acquisti.jsp", "Acquisti"};

        for (String path : giocatoreRuolo) {
            FilterChain filterChain = mock(FilterChain.class);

            GiocatoreBean accountBean = new GiocatoreBean();
            accountBean.setUsername("mariorossi91");
            accountBean.setRuolo(1);

            request.getSession().setAttribute("account", accountBean);
            request.setRequestURI(path);

            logFilter.doFilter(request, response, filterChain);

            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
        }
    }

    @Test
    void doFilterCasaEditrice() throws IOException, ServletException, SQLException {
        String[] aziendaRuolo = {"Ricerca", "RicercaAjax", "ricerca.jsp", "AggiornaProfilo", "Logout", "Profilo", "Catalogo", "CatalogoCasaEditrice", "Pubblicazioni", "VerificaIsin", "VerificaUsername", "Videogioco", "error.jsp", "profilo.jsp", "ElencoCaseEditrici", "catalogoCasaEditrice.jsp", "pubblicazioni.jsp", "videogioco.jsp", "image", "elencoCaseEditrici.jsp"};

        for (String path : aziendaRuolo) {
            FilterChain filterChain = mock(FilterChain.class);

            CasaEditriceBean accountBean = new CasaEditriceBean();
            accountBean.setUsername("nintendo");
            accountBean.setRuolo(2);

            request.getSession().setAttribute("account", accountBean);
            request.setRequestURI(path);

            logFilter.doFilter(request, response, filterChain);

            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
        }
    }

    @Test
    void doFilterAdminI() throws IOException, ServletException, SQLException {
        String[] adminRuolo = {"Ricerca", "RicercaAjax", "ricerca.jsp", "AggiornaProfilo", "Catalogo", "CatalogoCasaEditrice", "ListaCaseEditrici", "RegistrazioneCasaEditrice", "RimozioneAccount", "VerificaIsin", "ListaGiocatori", "Logout", "Profilo", "VerificaUsername", "Videogioco", "error.jsp", "profilo.jsp", "listaCaseEditrici.jsp", "listaGiocatori.jsp", "registrazioneCasaEditrice.jsp", "rimozioneAccount.jsp", "videogioco.jsp", "image", "elencoCaseEditrici.jsp", "ElencoCaseEditrici"};

        for (String path : adminRuolo) {
            FilterChain filterChain = mock(FilterChain.class);
            IAccountDAO accountDAO = new AccountDAO();

            AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("admin", Utils.generatePwd("admin"));
            request.getSession().setAttribute("account", accountDAO.doRetrieveDetailed(accountBean));
            request.setRequestURI(path);

            logFilter.doFilter(request, response, filterChain);

            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
        }
    }

    @Test
    void doFilterGiocatoreI() throws IOException, ServletException, SQLException {
        String[] giocatoreRuolo = {"Ricerca", "RicercaAjax", "RicercaGiocatoreAjax", "ricerca.jsp", "AggiornaProfilo", "AggiungiFriend", "Catalogo", "CatalogoCasaEditrice", "Friendlist", "GestoreCarrello", "VerificaIsin", "Libreria", "ElencoCaseEditrici", "Logout", "Profilo", "RimuoviFriend", "VerificaUsername", "Videogioco", "error.jsp", "profilo.jsp", "carrello.jsp", "catalogo.jsp", "catalogoCasaEditrice.jsp", "friendlist.jsp", "libreria.jsp", "elencoCaseEditrici.jsp", "videogioco.jsp", "image", "acquisti.jsp", "Acquisti"};

        for (String path : giocatoreRuolo) {
            FilterChain filterChain = mock(FilterChain.class);
            IAccountDAO accountDAO = new AccountDAO();

            AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("mariorossi91", Utils.generatePwd("123stella"));

            request.getSession().setAttribute("account", accountDAO.doRetrieveDetailed(accountBean));
            request.setRequestURI(path);

            logFilter.doFilter(request, response, filterChain);

            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());
        }
    }

    @Test
    void doFilterCasaEditriceI() throws IOException, ServletException, SQLException {
        String[] aziendaRuolo = {"Ricerca", "RicercaAjax", "ricerca.jsp", "AggiornaProfilo", "Logout", "Profilo", "Catalogo", "CatalogoCasaEditrice", "Pubblicazioni", "VerificaIsin", "VerificaUsername", "Videogioco", "error.jsp", "profilo.jsp", "ElencoCaseEditrici", "catalogoCasaEditrice.jsp", "pubblicazioni.jsp", "videogioco.jsp", "image", "elencoCaseEditrici.jsp"};

        for (String path : aziendaRuolo) {
            FilterChain filterChain = mock(FilterChain.class);
            IAccountDAO accountDAO = new AccountDAO();

            AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword("nintendo", Utils.generatePwd("456nintendo"));
            request.getSession().setAttribute("account", accountDAO.doRetrieveDetailed(accountBean));
            request.setRequestURI(path);

            logFilter.doFilter(request, response, filterChain);

            assertNotEquals("/NYTRO/jsp/index.jsp", response.getRedirectedUrl());

        }
    }
}