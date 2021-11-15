package nytro.model.dao;

import nytro.model.bean.AccountBean;
import nytro.model.bean.AmministratoreBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AccountDAOTest {
    private final AccountDAO accountDAO = new AccountDAO();

    @Test
    void doRetrieveAll() throws SQLException {
        List<AccountBean> list = new ArrayList<>();

        AccountBean accountBean1 = new AccountBean();
        accountBean1.setUsername("admin");
        AccountBean accountBean2 = new AccountBean();
        accountBean2.setUsername("annaarancio97");
        AccountBean accountBean3 = new AccountBean();
        accountBean3.setUsername("freebirdgames");
        AccountBean accountBean4 = new AccountBean();
        accountBean4.setUsername("lucagialli01");
        AccountBean accountBean5 = new AccountBean();
        accountBean5.setUsername("luigiverdi94");
        AccountBean accountBean6 = new AccountBean();
        accountBean6.setUsername("mariablu98");
        AccountBean accountBean7 = new AccountBean();
        accountBean7.setUsername("mariorossi91");
        AccountBean accountBean8 = new AccountBean();
        accountBean8.setUsername("nintendo");
        AccountBean accountBean9 = new AccountBean();
        accountBean9.setUsername("rosanera89");
        AccountBean accountBean10 = new AccountBean();
        accountBean10.setUsername("squareenix");
        AccountBean accountBean11 = new AccountBean();
        accountBean11.setUsername("tobyfox");
        AccountBean accountBean12 = new AccountBean();
        accountBean12.setUsername("ubisoft");

        list.add(accountBean1);
        list.add(accountBean2);
        list.add(accountBean3);
        list.add(accountBean4);
        list.add(accountBean5);
        list.add(accountBean6);
        list.add(accountBean7);
        list.add(accountBean8);
        list.add(accountBean9);
        list.add(accountBean10);
        list.add(accountBean11);
        list.add(accountBean12);

        Collection<AccountBean> accountBeanListRetrieved = accountDAO.doRetrieveAll(null, -1);

        assertEquals(list.size(), accountBeanListRetrieved.size());

        List<AccountBean> accounts = new ArrayList<>();
        for (AccountBean accountBean : accountBeanListRetrieved) {
            accounts.add(accountBean);
        }

        for (int i = 0; i < accountBeanListRetrieved.size(); i++) {
            if (!list.get(i).getUsername().equals(accounts.get(i).getUsername())) {
                fail();
            }
        }

    }

    @Test
    void doRetrieveAllOrdinato() throws SQLException {
        List<AccountBean> list = new ArrayList<>();

        AccountBean accountBean1 = new AccountBean();
        accountBean1.setUsername("admin");
        AccountBean accountBean2 = new AccountBean();
        accountBean2.setUsername("annaarancio97");
        AccountBean accountBean3 = new AccountBean();
        accountBean3.setUsername("freebirdgames");
        AccountBean accountBean4 = new AccountBean();
        accountBean4.setUsername("lucagialli01");
        AccountBean accountBean5 = new AccountBean();
        accountBean5.setUsername("luigiverdi94");
        AccountBean accountBean6 = new AccountBean();
        accountBean6.setUsername("mariablu98");
        AccountBean accountBean7 = new AccountBean();
        accountBean7.setUsername("mariorossi91");
        AccountBean accountBean8 = new AccountBean();
        accountBean8.setUsername("nintendo");
        AccountBean accountBean9 = new AccountBean();
        accountBean9.setUsername("rosanera89");
        AccountBean accountBean10 = new AccountBean();
        accountBean10.setUsername("squareenix");
        AccountBean accountBean11 = new AccountBean();
        accountBean11.setUsername("tobyfox");
        AccountBean accountBean12 = new AccountBean();
        accountBean12.setUsername("ubisoft");

        list.add(accountBean1);
        list.add(accountBean2);
        list.add(accountBean3);
        list.add(accountBean4);
        list.add(accountBean5);
        list.add(accountBean6);
        list.add(accountBean7);
        list.add(accountBean8);
        list.add(accountBean9);
        list.add(accountBean10);
        list.add(accountBean11);
        list.add(accountBean12);

        Collection<AccountBean> accountBeanListRetrieved = accountDAO.doRetrieveAll("username", -1);

        assertEquals(list.size(), accountBeanListRetrieved.size());

        List<AccountBean> accounts = new ArrayList<>();
        for (AccountBean accountBean : accountBeanListRetrieved) {
            accounts.add(accountBean);
        }

        for (int i = 0; i < accountBeanListRetrieved.size(); i++) {
            if (!list.get(i).getUsername().equals(accounts.get(i).getUsername())) {
                fail();
            }
        }

    }

    @Test
    void doRetrieveAllAdmin() throws SQLException {
        List<AccountBean> list = new ArrayList<>();

        AccountBean accountBean1 = new AccountBean();
        accountBean1.setUsername("admin");

        list.add(accountBean1);

        Collection<AccountBean> accountBeanListRetrieved = accountDAO.doRetrieveAll("", 0);

        assertEquals(list.size(), accountBeanListRetrieved.size());

        List<AccountBean> accounts = new ArrayList<>();
        for (AccountBean accountBean : accountBeanListRetrieved) {
            accounts.add(accountBean);
        }

        for (int i = 0; i < accountBeanListRetrieved.size(); i++) {
            if (!list.get(i).getUsername().equals(accounts.get(i).getUsername())) {
                fail();
            }
        }

    }

    @Test
    void doRetrieveAllGiocatore() throws SQLException {
        List<AccountBean> list = new ArrayList<>();

        AccountBean accountBean2 = new AccountBean();
        accountBean2.setUsername("annaarancio97");
        AccountBean accountBean4 = new AccountBean();
        accountBean4.setUsername("lucagialli01");
        AccountBean accountBean5 = new AccountBean();
        accountBean5.setUsername("luigiverdi94");
        AccountBean accountBean6 = new AccountBean();
        accountBean6.setUsername("mariablu98");
        AccountBean accountBean7 = new AccountBean();
        accountBean7.setUsername("mariorossi91");
        AccountBean accountBean9 = new AccountBean();
        accountBean9.setUsername("rosanera89");


        list.add(accountBean2);
        list.add(accountBean4);
        list.add(accountBean5);
        list.add(accountBean6);
        list.add(accountBean7);
        list.add(accountBean9);

        Collection<AccountBean> accountBeanListRetrieved = accountDAO.doRetrieveAll(null, 1);

        assertEquals(list.size(), accountBeanListRetrieved.size());

        List<AccountBean> accounts = new ArrayList<>();
        for (AccountBean accountBean : accountBeanListRetrieved) {
            accounts.add(accountBean);
        }

        for (int i = 0; i < accountBeanListRetrieved.size(); i++) {
            if (!list.get(i).getUsername().equals(accounts.get(i).getUsername())) {
                fail();
            }
        }

    }

    @Test
    void doRetrieveAllAzienda() throws SQLException {
        List<AccountBean> list = new ArrayList<>();

        AccountBean accountBean3 = new AccountBean();
        accountBean3.setUsername("freebirdgames");
        AccountBean accountBean8 = new AccountBean();
        accountBean8.setUsername("nintendo");
        AccountBean accountBean10 = new AccountBean();
        accountBean10.setUsername("squareenix");
        AccountBean accountBean11 = new AccountBean();
        accountBean11.setUsername("tobyfox");
        AccountBean accountBean12 = new AccountBean();
        accountBean12.setUsername("ubisoft");

        list.add(accountBean3);
        list.add(accountBean8);
        list.add(accountBean10);
        list.add(accountBean11);
        list.add(accountBean12);

        Collection<AccountBean> accountBeanListRetrieved = accountDAO.doRetrieveAll(null, 2);

        assertEquals(list.size(), accountBeanListRetrieved.size());

        List<AccountBean> accounts = new ArrayList<>();
        for (AccountBean accountBean : accountBeanListRetrieved) {
            accounts.add(accountBean);
        }

        for (int i = 0; i < accountBeanListRetrieved.size(); i++) {
            if (!list.get(i).getUsername().equals(accounts.get(i).getUsername())) {
                fail();
            }
        }

    }

    @Test
    void doRetrieveGiocatore() throws SQLException {
        List<AccountBean> list = new ArrayList<>();

        AccountBean accountBean2 = new AccountBean();
        accountBean2.setUsername("annaarancio97");

        GiocatoreBean giocatoreBean = accountDAO.doRetrieveGiocatore(accountBean2);

        assertEquals("1997-09-17", giocatoreBean.getDataNascita());
        assertEquals("f", giocatoreBean.getGenere());
    }

    @Test
    void doRetrieveGiocatoreEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRetrieveGiocatore(null);
        });
    }

    @Test
    void doRetrieveCasaEditrice() throws SQLException {
        CasaEditriceBean accountBean8 = new CasaEditriceBean();
        accountBean8.setUsername("nintendo");

        CasaEditriceBean casaEditriceBeanRetrieved = accountDAO.doRetrieveCasaEditrice(accountBean8);

        assertEquals("210987654321", casaEditriceBeanRetrieved.getISIN());
        assertEquals("Shuntaro Furukawa", casaEditriceBeanRetrieved.getCEO());
        assertEquals("www.nintendo.com", casaEditriceBeanRetrieved.getSitoWeb());
        assertEquals("Nintendo", casaEditriceBeanRetrieved.getNomeCasaEditrice());
    }

    @Test
    void doRetrieveCasaEditriceEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRetrieveCasaEditrice(null);
        });
    }

    @Test
    void doRetrieveByUsernamePassword() throws SQLException {
        String username = "freebirdgames";
        String password = "fda7cad7975c96f23753413c041ddafc527e9d8c";
        AccountBean accountBean = accountDAO.doRetrieveByUsernamePassword(username, password);

        assertEquals("freebirdgames", accountBean.getUsername());
        assertEquals("fda7cad7975c96f23753413c041ddafc527e9d8c", accountBean.getPassword());
        assertEquals("freebird@gmail.com", accountBean.getEmail());
        assertEquals("fbrecovery@gmail.com", accountBean.getEmailRecupero());
        assertEquals(null, accountBean.getCellulare());
        assertEquals("2011-06-03", accountBean.getData());
        assertEquals("943.356.78.1", accountBean.getIp());
        assertEquals(2, accountBean.getRuolo());
        assertEquals(null, accountBean.getImgProfilo());

    }

    @Test
    void doRetrieveContributoAdmin() throws SQLException {
        String dataInizio = "2000-01-01";
        String dataFine = "2020-12-31";

        List<String> list = new ArrayList<>();
        list.add("Ubisoft");
        list.add("119.98");
        list.add("134.2");
        list.add("Nintendo");
        list.add("139.98");
        list.add("136.4");
        list.add("Square Enix");
        list.add("89.98");
        list.add("130.9");
        list.add("Freebird Games");
        list.add("39.96");
        list.add("25.13");

        List<String> listRetrieved = accountDAO.doRetrieveContributoAdmin(dataInizio, dataFine);

        assertEquals(list.size(), listRetrieved.size());
    }

    @Test
    void doRetrieveContributoAdminEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRetrieveContributoAdmin("-1", "-1");
        });
    }

    @Test
    void doRetrieveContributoCasaEditrice() throws SQLException {
        String dataInizio = "2000-01-01";
        String dataFine = "2020-12-31";
        String isin = "123456789012";

        List<String> list = new ArrayList<>();
        list.add("Incassi");
        list.add("Contributi_Annuali");
        list.add("119.98");
        list.add("134.2");

        List<String> listRetrieved = accountDAO.doRetrieveContributoCasaEditrice(isin, dataInizio, dataFine);

        assertEquals(list.size(), listRetrieved.size());
    }

    @Test
    void doRetrieveContributoCasaEditriceEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRetrieveContributoCasaEditrice("-1", "-1", "-1");
        });
    }

    @Test
    void doRetrieveIsinByUsername() throws SQLException {
        String username = "nintendo";
        String isin = "210987654321";

        assertEquals(isin, accountDAO.doRetrieveIsinByUsername(username));
    }

    @Test
    void doRetrieveByUsername() throws SQLException {
        String username = "lucagialli01";

        AccountBean accountBean = accountDAO.doRetrieveByUsername(username);

        assertEquals(username, accountBean.getUsername());
        assertEquals("187fe79300c9e68c78d480ab1c5646beea964e2d", accountBean.getPassword());
        assertEquals("lucagialli@gmail.com", accountBean.getEmail());
        assertEquals("lucarecupero@gmail.com", accountBean.getEmailRecupero());
        assertEquals(null, accountBean.getCellulare());
        assertEquals("2016-07-04", accountBean.getData());
        assertEquals("456.123.34.78", accountBean.getIp());
        assertEquals(1, accountBean.getRuolo());
        assertEquals(null, accountBean.getImgProfilo());

    }

    @Test
    void doRetrieveByIsin() throws SQLException {
        String isin = "210987654321";

        AccountBean accountBean = accountDAO.doRetrieveByIsin(isin);

        assertEquals("nintendo", accountBean.getUsername());
        assertEquals("0c644c07df798e30c5b4b3f377e97d08cbf1e54f", accountBean.getPassword());
        assertEquals("manager@nintendo.com", accountBean.getEmail());
        assertEquals("recovery@nintendo.com", accountBean.getEmailRecupero());
        assertEquals(null, accountBean.getCellulare());
        assertEquals("2013-05-03", accountBean.getData());
        assertEquals("863.467.23.79", accountBean.getIp());
        assertEquals(2, accountBean.getRuolo());
        assertEquals(null, accountBean.getImgProfilo());
    }

    @Test
    void doSaveAccount() throws SQLException {
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("username1");
        accountBean.setPassword("c1be695ccac688780d3d3d589fbfe681b2817f1f");
        accountBean.setEmail("user@name.it");
        accountBean.setEmailRecupero("name@user.it");
        accountBean.setCellulare(null);
        accountBean.setData("2011-02-16");
        accountBean.setOra("22:06:31");
        accountBean.setIp("454.657.12.46");
        accountBean.setRuolo(0);
        accountBean.setImgProfilo(null);

        accountDAO.doSaveAccount(accountBean);

        AccountBean accountBeanRetrieved = accountDAO.doRetrieveByUsername(accountBean.getUsername());

        assertEquals(accountBeanRetrieved.getUsername(), accountBean.getUsername());
        assertEquals(accountBeanRetrieved.getPassword(), accountBean.getPassword());
        assertEquals(accountBeanRetrieved.getEmail(), accountBean.getEmail());
        assertEquals(accountBeanRetrieved.getEmailRecupero(), accountBean.getEmailRecupero());
        assertEquals(accountBeanRetrieved.getCellulare(), accountBean.getCellulare());
        assertEquals(accountBeanRetrieved.getData(), accountBean.getData());
        assertEquals(accountBeanRetrieved.getOra(), accountBean.getOra());
        assertEquals(accountBeanRetrieved.getIp(), accountBean.getIp());
        assertEquals(accountBeanRetrieved.getRuolo(), accountBean.getRuolo());
        assertEquals(accountBeanRetrieved.getImgProfilo(), accountBean.getImgProfilo());

        accountDAO.doDelete(accountBeanRetrieved);
    }

    @Test
    void doSaveAccountEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doSaveAccount(null);
        });
    }

    @Test
    void doSaveGiocatore() throws SQLException {
        GiocatoreBean accountBean = new GiocatoreBean();
        accountBean.setUsername("username1");
        accountBean.setPassword("c1be695ccac688780d3d3d589fbfe681b2817f1f");
        accountBean.setEmail("user@name.it");
        accountBean.setEmailRecupero("name@user.it");
        accountBean.setCellulare(null);
        accountBean.setData("2011-02-16");
        accountBean.setOra("22:06:31");
        accountBean.setIp("454.657.12.46");
        accountBean.setRuolo(0);
        accountBean.setImgProfilo(null);
        accountBean.setGenere("f");
        accountBean.setData(null);

        accountDAO.doSaveGiocatore(accountBean);

        GiocatoreBean accountBeanRetrieved = accountDAO.doRetrieveGiocatore(accountBean);

        assertEquals(accountBeanRetrieved.getUsername(), accountBean.getUsername());
        assertEquals(accountBeanRetrieved.getPassword(), accountBean.getPassword());
        assertEquals(accountBeanRetrieved.getEmail(), accountBean.getEmail());
        assertEquals(accountBeanRetrieved.getEmailRecupero(), accountBean.getEmailRecupero());
        assertEquals(accountBeanRetrieved.getCellulare(), accountBean.getCellulare());
        assertEquals(accountBeanRetrieved.getData(), accountBean.getData());
        assertEquals(accountBeanRetrieved.getOra(), accountBean.getOra());
        assertEquals(accountBeanRetrieved.getIp(), accountBean.getIp());
        assertEquals(accountBeanRetrieved.getRuolo(), accountBean.getRuolo());
        assertEquals(accountBeanRetrieved.getImgProfilo(), accountBean.getImgProfilo());
        assertEquals(accountBeanRetrieved.getDataNascita(), accountBean.getDataNascita());
        assertEquals(accountBeanRetrieved.getGenere(), accountBean.getGenere());

        accountDAO.doDelete(accountBeanRetrieved);
    }

    @Test
    void doSaveGiocatoreEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doSaveGiocatore(null);
        });
    }

    @Test
    void doSaveCasaEditrice() throws SQLException {
        CasaEditriceBean accountBean = new CasaEditriceBean();
        accountBean.setUsername("username33");
        accountBean.setPassword("c1be695ccac688780d3d3d589fbfe681b2817f1f");
        accountBean.setEmail("user@name.it");
        accountBean.setEmailRecupero("name@user.it");
        accountBean.setCellulare(null);
        accountBean.setData("2011-02-16");
        accountBean.setOra("22:06:31");
        accountBean.setIp("454.657.12.46");
        accountBean.setRuolo(2);
        accountBean.setImgProfilo(null);
        accountBean.setISIN("453133618853");
        accountBean.setNomeCasaEditrice("Nom Ec");
        accountBean.setCEO("C Eo");
        accountBean.setSitoWeb("www.website.com");

        accountDAO.doSaveCasaEditrice(accountBean);

        CasaEditriceBean accountBeanRetrieved = accountDAO.doRetrieveCasaEditrice(accountBean);

        assertEquals(accountBeanRetrieved.getUsername(), accountBean.getUsername());
        assertEquals(accountBeanRetrieved.getPassword(), accountBean.getPassword());
        assertEquals(accountBeanRetrieved.getEmail(), accountBean.getEmail());
        assertEquals(accountBeanRetrieved.getEmailRecupero(), accountBean.getEmailRecupero());
        assertEquals(accountBeanRetrieved.getCellulare(), accountBean.getCellulare());
        assertEquals(accountBeanRetrieved.getData(), accountBean.getData());
        assertEquals(accountBeanRetrieved.getOra(), accountBean.getOra());
        assertEquals(accountBeanRetrieved.getIp(), accountBean.getIp());
        assertEquals(accountBeanRetrieved.getRuolo(), accountBean.getRuolo());
        assertEquals(accountBeanRetrieved.getImgProfilo(), accountBean.getImgProfilo());
        assertEquals(accountBeanRetrieved.getISIN(), accountBean.getISIN());
        assertEquals(accountBeanRetrieved.getCEO(), accountBean.getCEO());
        assertEquals(accountBeanRetrieved.getNomeCasaEditrice(), accountBean.getNomeCasaEditrice());
        assertEquals(accountBeanRetrieved.getSitoWeb(), accountBean.getSitoWeb());

        accountDAO.doDelete(accountBeanRetrieved);
    }

    @Test
    void doSaveCasaEditriceEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doSaveCasaEditrice(null);
        });
    }

    @Test
    void doUpdate() throws SQLException {
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("username1");
        accountBean.setPassword("c1be695ccac688780d3d3d589fbfe681b2817f1f");
        accountBean.setEmail("user@name.it");
        accountBean.setEmailRecupero("name@user.it");
        accountBean.setCellulare(null);
        accountBean.setData("2011-02-16");
        accountBean.setOra("22:06:31");
        accountBean.setIp("454.657.12.46");
        accountBean.setRuolo(0);
        accountBean.setImgProfilo(null);

        accountDAO.doSaveAccount(accountBean);

        accountBean.setEmailRecupero("em@rec.it");

        accountDAO.doUpdate(accountBean);

        AccountBean accountBeanRetrieved = accountDAO.doRetrieveByUsername(accountBean.getUsername());

        System.out.println(accountBeanRetrieved);
        System.out.println(accountBean);

        assertEquals(accountBeanRetrieved.getEmailRecupero(), accountBean.getEmailRecupero());

        accountDAO.doDelete(accountBeanRetrieved);
    }

    @Test
    void doUpdateEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doUpdate(null);
        });
    }

    @Test
    void doUpdateGiocatore() throws SQLException {
        GiocatoreBean accountBean = new GiocatoreBean();
        accountBean.setUsername("username1");
        accountBean.setPassword("c1be695ccac688780d3d3d589fbfe681b2817f1f");
        accountBean.setEmail("user@name.it");
        accountBean.setEmailRecupero("name@user.it");
        accountBean.setCellulare(null);
        accountBean.setData("2011-02-16");
        accountBean.setOra("22:06:31");
        accountBean.setIp("454.657.12.46");
        accountBean.setRuolo(0);
        accountBean.setImgProfilo(null);
        accountBean.setGenere("f");
        accountBean.setDataNascita("1990-05-20");
        accountBean.setDataIscrizione("2020-05-10");
        accountBean.setData(null);

        accountDAO.doSaveGiocatore(accountBean);

        accountBean.setGenere("m");

        accountDAO.doUpdateGiocatore(accountBean);

        GiocatoreBean accountBeanRetrieved = accountDAO.doRetrieveGiocatore(accountBean);

        assertEquals(accountBeanRetrieved.getGenere(), accountBean.getGenere());

        accountDAO.doDelete(accountBeanRetrieved);
    }

    @Test
    void doUpdateGiocatoreEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doUpdateGiocatore(null);
        });
    }

    @Test
    void doUpdateAmministratore() throws SQLException {
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("admin");
        accountBean.setPassword("92d335368fdfc4153b791af1969fba0da4f95b04");
        accountBean.setEmail("siteadmin@gmail.com");
        accountBean.setEmailRecupero("adminrecupero@gmail.com");
        accountBean.setCellulare(null);
        accountBean.setData("2019-05-17");
        accountBean.setOra("22:54:57");
        accountBean.setIp("457.257.1.12");
        accountBean.setRuolo(0);
        accountBean.setImgProfilo(null);

        AmministratoreBean amministratoreBean = (AmministratoreBean) accountDAO.doRetrieveDetailed(accountBean);

        amministratoreBean.setNome("Prova");

        accountDAO.doUpdateAmministratore(amministratoreBean);

        amministratoreBean = (AmministratoreBean) accountDAO.doRetrieveDetailed(accountBean);

        assertEquals(amministratoreBean.getNome(), amministratoreBean.getNome());

        amministratoreBean.setNome("Veronica");

        accountDAO.doUpdateAmministratore(amministratoreBean);
    }

    @Test
    void doUpdateAmministratoreEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doUpdateAmministratore(null);
        });
    }

    @Test
    void doUpdateCasaEditrice() throws SQLException {
        CasaEditriceBean accountBean = new CasaEditriceBean();
        accountBean.setUsername("nintendo");

        CasaEditriceBean accountBeanRetrieved = accountDAO.doRetrieveCasaEditrice(accountBean);

        accountBeanRetrieved.setCEO("Ce o");

        accountDAO.doUpdateCasaEditrice(accountBeanRetrieved);

        accountBeanRetrieved = accountDAO.doRetrieveCasaEditrice(accountBean);

        assertEquals("Ce o", accountBeanRetrieved.getCEO());

        accountBeanRetrieved.setCEO("Shuntaro Furukawa");

        accountDAO.doUpdateCasaEditrice(accountBeanRetrieved);
    }

    @Test
    void doUpdateCasaEditriceEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doUpdateCasaEditrice(null);
        });
    }

    @Test
    void doDelete() throws SQLException {
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("username1");
        accountBean.setPassword("c1be695ccac688780d3d3d589fbfe681b2817f1f");
        accountBean.setEmail("user@name.it");
        accountBean.setEmailRecupero("name@user.it");
        accountBean.setCellulare(null);
        accountBean.setData("2011-02-16");
        accountBean.setOra("22:06:31");
        accountBean.setIp("454.657.12.46");
        accountBean.setRuolo(0);
        accountBean.setImgProfilo(null);

        accountDAO.doSaveAccount(accountBean);

        accountDAO.doDelete(accountBean);

        AccountBean accountBeanRetrieved = accountDAO.doRetrieveByUsername(accountBean.getUsername());

        assertEquals(accountBeanRetrieved.getUsername(), null);
    }

    @Test
    void doDeleteEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doDelete(null);
        });
    }

    @Test
    void doRetrieveAllFriendsByUsername() throws SQLException {
        List<String> list = new ArrayList<>();
        list.add("lucagialli01");
        list.add("luigiverdi94");
        list.add("mariablu98");

        String username = "mariorossi91";

        Collection<AccountBean> accountBeanList = accountDAO.doRetrieveAllFriendsByUsername(username);

        assertEquals(accountBeanList.size(), list.size());

        List<AccountBean> accountBeans = new ArrayList<>();

        for (AccountBean accountBean : accountBeanList) {
            accountBeans.add(accountBean);
        }

        for (int i = 0; i < accountBeans.size(); i++) {
            assertEquals(accountBeans.get(i).getUsername(), list.get(i));
        }
    }

    @Test
    void doRetrieveAllFriendsByVideogioco() throws SQLException {
        List<String> list = new ArrayList<>();
        list.add("lucagialli01");
        list.add("mariablu98");

        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        Collection<AccountBean> accountBeanList = accountDAO.doRetrieveAllFriendsByVideogioco(accountBean, 3);

        assertEquals(accountBeanList.size(), list.size());

        List<AccountBean> accountBeans = new ArrayList<>();

        for (AccountBean account : accountBeanList) {
            accountBeans.add(account);
        }

        for (int i = 0; i < accountBeans.size(); i++) {
            assertEquals(accountBeans.get(i).getUsername(), list.get(i));
        }
    }

    @Test
    void doAggiungiAmicoFriendlist() throws SQLException {
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        String username = "rosanera89";

        accountDAO.doAggiungiAmicoFriendlist(accountBean, username);

        Collection<AccountBean> accountBeanList = accountDAO.doRetrieveAllFriendsByUsername(accountBean.getUsername());

        List<String> list = new ArrayList<>();

        for (AccountBean account : accountBeanList) {
            list.add(account.getUsername());
        }

        assertTrue(list.contains(username));

        accountDAO.doRimuoviAmicoFriendlist(accountBean, username);
    }

    @Test
    void doRimuoviAmicoFriendlist() throws SQLException {
        AccountBean accountBean = new AccountBean();
        accountBean.setUsername("mariorossi91");

        String username = "rosanera89";

        accountDAO.doAggiungiAmicoFriendlist(accountBean, username);

        accountDAO.doRimuoviAmicoFriendlist(accountBean, username);

        Collection<AccountBean> accountBeanList = accountDAO.doRetrieveAllFriendsByUsername(accountBean.getUsername());

        List<String> list = new ArrayList<>();

        for (AccountBean account : accountBeanList) {
            list.add(account.getUsername());
        }

        assertTrue(!list.contains(username));
    }

    @Test
    void doRetrieveAllFriendsByVideogiocoEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRetrieveAllFriendsByVideogioco(null, -5);
        });
    }

    @Test
    void doAggiungiAmicoFriendlistEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doAggiungiAmicoFriendlist(null, null);
        });
    }

    @Test
    void doRimuoviAmicoFriendlistEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRimuoviAmicoFriendlist(null, null);
        });
    }

    @Test
    void doRetrieveDetailedAdmin() throws SQLException {
        AccountBean accountBean1 = new AccountBean();
        accountBean1.setUsername("admin");
        accountBean1.setRuolo(0);

        AmministratoreBean amministratoreBean = (AmministratoreBean) accountDAO.doRetrieveDetailed(accountBean1);

        assertEquals("Veronica", amministratoreBean.getNome());
        assertEquals("admin", amministratoreBean.getUsername());
        assertEquals("Volpicelli", amministratoreBean.getCognome());
    }

    @Test
    void doRetrieveDetailedGiocatore() throws SQLException {
        AccountBean accountBean1 = new AccountBean();
        accountBean1.setUsername("mariorossi91");
        accountBean1.setRuolo(1);

        GiocatoreBean giocatoreBean = (GiocatoreBean) accountDAO.doRetrieveDetailed(accountBean1);

        assertEquals("mariorossi91", giocatoreBean.getUsername());
        assertEquals("1991-05-12", giocatoreBean.getDataNascita());
        assertEquals("m", giocatoreBean.getGenere());
    }

    @Test
    void doRetrieveDetailedAzienda() throws SQLException {
        AccountBean accountBean1 = new AccountBean();
        accountBean1.setUsername("nintendo");
        accountBean1.setRuolo(2);

        CasaEditriceBean giocatoreBean = (CasaEditriceBean) accountDAO.doRetrieveDetailed(accountBean1);

        assertEquals("nintendo", giocatoreBean.getUsername());
        assertEquals("210987654321", giocatoreBean.getISIN());
        assertEquals("Nintendo", giocatoreBean.getNomeCasaEditrice());
        assertEquals("Shuntaro Furukawa", giocatoreBean.getCEO());
        assertEquals("www.nintendo.com", giocatoreBean.getSitoWeb());
    }

    @Test
    void doRetrieveDetailedEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doRetrieveDetailed(null);
        });
    }

    @Test
    void doUploadImage() throws SQLException {
        AccountBean account = new AccountBean();
        account.setUsername("mariorossi91");
        account.setRuolo(1);
        InputStream inputStream = new ByteArrayInputStream("prova".getBytes());
        account.setImgProfilo(inputStream);

        accountDAO.doUploadImage(account);

        account = accountDAO.doRetrieveDetailed(account);

        assertNotNull(account.getImgProfilo());

        accountDAO.doUploadImage(account);
    }

    @Test
    void doUploadImageNull() throws SQLException {
        AccountBean account = new AccountBean();
        account.setUsername("tobyfox");
        account.setRuolo(2);
        account.setImgProfilo(null);

        accountDAO.doUploadImage(account);

        account = accountDAO.doRetrieveDetailed(account);

        assertNull(account.getImgProfilo());
    }

    @Test
    void doUploadImageEx() {
        assertThrows(Exception.class, () -> {
            accountDAO.doUploadImage(null);
        });
    }

    @Test
    void doDisplayImage() throws SQLException {
        AccountBean account = new AccountBean();
        account.setUsername("freebirdgames");
        account.setRuolo(2);

        accountDAO.doDisplayImage(account.getUsername());

        account = accountDAO.doRetrieveDetailed(account);

        assertNull(account.getImgProfilo());

        accountDAO.doUploadImage(account);
    }

    @Test
    void doDisplayImageVoid() throws SQLException {
        AccountBean account = new AccountBean();
        account.setUsername("m");
        account.setRuolo(1);
        InputStream inputStream = new ByteArrayInputStream("prova".getBytes());
        account.setImgProfilo(inputStream);

        accountDAO.doDisplayImage(account.getUsername());

        account = accountDAO.doRetrieveDetailed(account);

        assertNull(account);
    }

    @Test
    void doRetrieveNumeroGiocatori() throws SQLException {
        assertEquals(1, accountDAO.doRetrieveNumeroGiocatori(0, 20));
    }


}