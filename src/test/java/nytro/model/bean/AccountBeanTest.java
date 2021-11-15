package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AccountBeanTest {
    private final AccountBean accountBean = new AccountBean();
    private final String username = "username";
    private final String password = "password";
    private final String email = "email@prova.it";
    private final String emailRecupero = "emailRecupero@prova.it";
    private final String cellulare = "3275766225";
    private final String data = "1995-05-25";
    private final String ora = "20:18:58";
    private final String ip = "123.456.23.12";
    private final InputStream inputStream = new ByteArrayInputStream("prova".getBytes());
    private final int ruolo = 1;

    @BeforeEach
    void beforeEach() {
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
    }

    @Test
    void getUsername() {
        assertEquals(username, accountBean.getUsername());
    }

    @Test
    void getPassword() {
        assertEquals(password, accountBean.getPassword());
    }

    @Test
    void getEmail() {
        assertEquals(email, accountBean.getEmail());
    }

    @Test
    void getEmailRecupero() {
        assertEquals(emailRecupero, accountBean.getEmailRecupero());
    }

    @Test
    void getCellulare() {
        assertEquals(cellulare, accountBean.getCellulare());
    }

    @Test
    void getData() {
        assertEquals(data, accountBean.getData());
    }

    @Test
    void getOra() {
        assertEquals(ora, accountBean.getOra());
    }

    @Test
    void getIp() {
        assertEquals(ip, accountBean.getIp());
    }

    @Test
    void getRuolo() {
        assertEquals(ruolo, accountBean.getRuolo());
    }

    @Test
    void getImgProfilo() {
        assertEquals(inputStream, accountBean.getImgProfilo());
    }

    @Test
    void setUsername() {
        String username2 = "username2";
        accountBean.setUsername(username2);
        assertEquals(username2, accountBean.getUsername());
    }

    @Test
    void setPassword() {
        String password2 = "password2";
        accountBean.setPassword(password2);
        assertEquals(password2, accountBean.getPassword());
    }

    @Test
    void setEmail() {
        String email2 = "email2@prova.it";
        accountBean.setEmail(email2);
        assertEquals(email2, accountBean.getEmail());
    }

    @Test
    void setEmailRecupero() {
        String emailRecupero2 = "emailRecupero2@prova.it";
        accountBean.setEmailRecupero(emailRecupero2);
        assertEquals(emailRecupero2, accountBean.getEmailRecupero());
    }

    @Test
    void setCellulare() {
        String cellulare2 = "3275766222";
        accountBean.setCellulare(cellulare2);
        assertEquals(cellulare2, accountBean.getCellulare());
    }

    @Test
    void setData() {
        String data2 = "1995-05-22";
        accountBean.setData(data2);
        assertEquals(data2, accountBean.getData());
    }

    @Test
    void setOra() {
        String ora2 = "22:22:22";
        accountBean.setOra(ora2);
        assertEquals(ora2, accountBean.getOra());
    }

    @Test
    void setIp() {
        String ip2 = "122.222.22.22";
        accountBean.setIp(ip2);
        assertEquals(ip2, accountBean.getIp());
    }

    @Test
    void setRuolo() {
        int ruolo2 = 2;
        accountBean.setRuolo(ruolo2);
        assertEquals(ruolo2, accountBean.getRuolo());
    }

    @Test
    void setImgProfilo() {
        InputStream inputStream2 = new ByteArrayInputStream("prova2".getBytes());
        accountBean.setImgProfilo(inputStream2);
        assertEquals(inputStream2, accountBean.getImgProfilo());
    }
}