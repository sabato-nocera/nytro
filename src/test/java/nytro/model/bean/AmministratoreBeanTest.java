package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AmministratoreBeanTest {
    private final AccountBean accountBean = new AccountBean();
    private final AmministratoreBean amministratoreBean = new AmministratoreBean(accountBean);
    private final String nome = "Nome";
    private final String cognome = "Cognome";

    @BeforeEach
    void beforeEach(){
        amministratoreBean.setNome(nome);
        amministratoreBean.setCognome(cognome);
    }

    @Test
    void AmministratoreBean() {
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

        AmministratoreBean amministratoreBean2 = new AmministratoreBean(accountBean2);

        assertEquals(amministratoreBean2.getUsername(), accountBean2.getUsername());
        assertEquals(amministratoreBean2.getPassword(), accountBean2.getPassword());
        assertEquals(amministratoreBean2.getEmail(), accountBean2.getEmail());
        assertEquals(amministratoreBean2.getEmailRecupero(), accountBean2.getEmailRecupero());
        assertEquals(amministratoreBean2.getCellulare(), accountBean2.getCellulare());
        assertEquals(amministratoreBean2.getData(), accountBean2.getData());
        assertEquals(amministratoreBean2.getOra(), accountBean2.getOra());
        assertEquals(amministratoreBean2.getIp(), accountBean2.getIp());
        assertEquals(amministratoreBean2.getImgProfilo(), accountBean2.getImgProfilo());
        assertEquals(amministratoreBean2.getRuolo(), accountBean2.getRuolo());
    }

    @Test
    void getNome() { assertEquals(nome, amministratoreBean.getNome());}

    @Test
    void getCognome() { assertEquals(cognome, amministratoreBean.getCognome());}

    @Test
    void setNome() {
        String nome2 = "Nome2";
        amministratoreBean.setNome(nome2);
        assertEquals(nome2, amministratoreBean.getNome());;
    }

    @Test
    void setCognome() {
        String cognome2 = "Cognome2";
        amministratoreBean.setCognome(cognome2);
        assertEquals(cognome2, amministratoreBean.getCognome());
    }
}