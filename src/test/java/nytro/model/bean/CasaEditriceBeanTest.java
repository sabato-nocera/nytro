package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class CasaEditriceBeanTest {
    private final CasaEditriceBean casaEditriceBean = new CasaEditriceBean();
    private final String isin = "1234567890170";
    private final String nomeCasaEditrice = "Casa Editrice";
    private final String ceo = "Ce O";
    private final String sitoWeb = "www.casaeditrice.it";

    @BeforeEach
    void beforeEach() {
        casaEditriceBean.setISIN(isin);
        casaEditriceBean.setNomeCasaEditrice(nomeCasaEditrice);
        casaEditriceBean.setCEO(ceo);
        casaEditriceBean.setSitoWeb(sitoWeb);
    }

    @Test
    void CasaEditriceBean() {
        AccountBean accountBean = new AccountBean();

        String username = "username";
        String password = "password";
        String email = "email@prova.it";
        String emailRecupero = "emailRecupero@prova.it";
        String cellulare = "3275766225";
        String data = "1995-05-25";
        String ora = "20:18:58";
        String ip = "123.456.23.12";
        InputStream inputStream = new ByteArrayInputStream("prova".getBytes());
        int ruolo = 1;

        accountBean.setUsername(username);
        accountBean.setPassword(password);
        accountBean.setEmail(email);
        accountBean.setEmailRecupero(emailRecupero);
        accountBean.setCellulare(cellulare);
        accountBean.setData(data);
        accountBean.setOra(ora);
        accountBean.setIp(ip);
        accountBean.setImgProfilo(inputStream);
        accountBean.setRuolo(ruolo);

        CasaEditriceBean casaEditriceBean2 = new CasaEditriceBean(accountBean);

        assertEquals(casaEditriceBean2.getUsername(), accountBean.getUsername());
        assertEquals(casaEditriceBean2.getPassword(), accountBean.getPassword());
        assertEquals(casaEditriceBean2.getEmail(), accountBean.getEmail());
        assertEquals(casaEditriceBean2.getEmailRecupero(), accountBean.getEmailRecupero());
        assertEquals(casaEditriceBean2.getCellulare(), accountBean.getCellulare());
        assertEquals(casaEditriceBean2.getData(), accountBean.getData());
        assertEquals(casaEditriceBean2.getOra(), accountBean.getOra());
        assertEquals(casaEditriceBean2.getIp(), accountBean.getIp());
        assertEquals(casaEditriceBean2.getImgProfilo(), accountBean.getImgProfilo());
        assertEquals(casaEditriceBean2.getRuolo(), accountBean.getRuolo());
    }

    @Test
    void getISIN() {
        assertEquals(isin, casaEditriceBean.getISIN());
    }

    @Test
    void getNomeCasaEditrice() {
        assertEquals(nomeCasaEditrice, casaEditriceBean.getNomeCasaEditrice());
    }

    @Test
    void getCEO() {
        assertEquals(ceo, casaEditriceBean.getCEO());
    }

    @Test
    void getSitoWeb() {
        assertEquals(sitoWeb, casaEditriceBean.getSitoWeb());
    }

    @Test
    void setISIN() {
        String isin2 = "1234567890172";
        casaEditriceBean.setISIN(isin2);
        assertEquals(isin2, casaEditriceBean.getISIN());
    }

    @Test
    void setNomeCasaEditrice() {
        String nomeCasaEditrice2 = "Casa Editrice 2";
        casaEditriceBean.setNomeCasaEditrice(nomeCasaEditrice2);
        assertEquals(nomeCasaEditrice2, casaEditriceBean.getNomeCasaEditrice());
    }

    @Test
    void setCEO() {
        String ceo2 = "Ce O2";
        casaEditriceBean.setCEO(ceo2);
        assertEquals(ceo2, casaEditriceBean.getCEO());
    }

    @Test
    void setSitoWeb() {
        String sitoWeb2 = "Ce O2";
        casaEditriceBean.setSitoWeb(sitoWeb2);
        assertEquals(sitoWeb2, casaEditriceBean.getSitoWeb());
    }
}