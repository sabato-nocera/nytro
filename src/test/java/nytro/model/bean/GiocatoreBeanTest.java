package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class GiocatoreBeanTest {
    private final AccountBean accountBean = new AccountBean();
    private final GiocatoreBean giocatoreBean = new GiocatoreBean(accountBean);
    private final String dataNascita = "1998-16-10";
    private final String dataIscrizione = "2020-10-12";
    private final String genere = "f";

    @BeforeEach
    void beforeEach(){
        giocatoreBean.setDataNascita(dataNascita);
        giocatoreBean.setDataIscrizione(dataIscrizione);
        giocatoreBean.setGenere(genere);
    }

    @Test
    void GiocatoreBeanAccount() {
        AccountBean accountBean2 = new AccountBean();

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

        accountBean2.setUsername(username);
        accountBean2.setPassword(password);
        accountBean2.setEmail(email);
        accountBean2.setEmailRecupero(emailRecupero);
        accountBean2.setCellulare(cellulare);
        accountBean2.setData(data);
        accountBean2.setOra(ora);
        accountBean2.setIp(ip);
        accountBean2.setImgProfilo(inputStream);
        accountBean2.setRuolo(ruolo);

        GiocatoreBean giocatoreBean2 = new GiocatoreBean(accountBean2);

        assertEquals(giocatoreBean2.getUsername(), accountBean2.getUsername());
        assertEquals(giocatoreBean2.getPassword(), accountBean2.getPassword());
        assertEquals(giocatoreBean2.getEmail(), accountBean2.getEmail());
        assertEquals(giocatoreBean2.getEmailRecupero(), accountBean2.getEmailRecupero());
        assertEquals(giocatoreBean2.getCellulare(), accountBean2.getCellulare());
        assertEquals(giocatoreBean2.getData(), accountBean2.getData());
        assertEquals(giocatoreBean2.getOra(), accountBean2.getOra());
        assertEquals(giocatoreBean2.getIp(), accountBean2.getIp());
        assertEquals(giocatoreBean2.getImgProfilo(), accountBean2.getImgProfilo());
        assertEquals(giocatoreBean2.getRuolo(), accountBean2.getRuolo());
    }

   @Test
    public void GiocatoreBeanVoid(){
        GiocatoreBean giocatoreBean1 = new GiocatoreBean();
       assertEquals(giocatoreBean1.getUsername(), null);
       assertEquals(giocatoreBean1.getPassword(), null);
       assertEquals(giocatoreBean1.getEmail(), null);
       assertEquals(giocatoreBean1.getEmailRecupero(), null);
       assertEquals(giocatoreBean1.getCellulare(), null);
       assertEquals(giocatoreBean1.getData(), null);
       assertEquals(giocatoreBean1.getOra(), null);
       assertEquals(giocatoreBean1.getIp(), null);
       assertEquals(giocatoreBean1.getImgProfilo(), null);
       assertEquals(giocatoreBean1.getRuolo(), 0.0);
       assertEquals(giocatoreBean1.getDataNascita(), null);
       assertEquals(giocatoreBean1.getDataIscrizione(),null);
       assertEquals(giocatoreBean1.getGenere(), null);
   }

    @Test
    void getDataNascita() {assertEquals(dataNascita, giocatoreBean.getDataNascita());}

    @Test
    void getDataIscrizione() {assertEquals(dataIscrizione,giocatoreBean.getDataIscrizione());}

    @Test
    void getGenere() {assertEquals(genere, giocatoreBean.getGenere());}

    @Test
    void setDataNascita() {
        String dataNascita2 = "1997-12-17";
        giocatoreBean.setDataNascita(dataNascita2);
        assertEquals(dataNascita2, giocatoreBean.getDataNascita());
    }

    @Test
    void setDataIscrizione() {
        String dataIscrizione2 = "2020-11-07";
        giocatoreBean.setDataIscrizione(dataIscrizione2);
        assertEquals(dataIscrizione2, giocatoreBean.getDataIscrizione());
    }

    @Test
    void setGenere() {
        String genere2 = "m";
        giocatoreBean.setGenere(genere2);
        assertEquals(genere2, giocatoreBean.getGenere());
    }
}