package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecensioneBeanTest {
    private final RecensioneBean recensioneBean = new RecensioneBean();
    private final int numRecensione = 1;
    private final int codVideogioco = 1;
    private final String username = "mariorossi91";
    private final String commento = "Videogioco fantastico";
    private final double voto = 5;

    @BeforeEach
    void beforeEach(){
        recensioneBean.setNumRecensione(numRecensione);
        recensioneBean.setCodVideogioco(codVideogioco);
        recensioneBean.setUsername(username);
        recensioneBean.setCommento(commento);
        recensioneBean.setVoto(voto);
    }

    @Test
    void getNumRecensione() { assertEquals(numRecensione, recensioneBean.getNumRecensione()); }

    @Test
    void getCodVideogioco() { assertEquals(codVideogioco, recensioneBean.getCodVideogioco()); }

    @Test
    void getUsername() { assertEquals(username, recensioneBean.getUsername()); }

    @Test
    void getCommento() { assertEquals(commento, recensioneBean.getCommento()); }

    @Test
    void getVoto() { assertEquals(voto, recensioneBean.getVoto()); }

    @Test
    void setNumRecensione() {
        int numRecensione2 = 2;
        recensioneBean.setNumRecensione(numRecensione2);
        assertEquals(numRecensione2, recensioneBean.getNumRecensione());
    }

    @Test
    void setCodVideogioco() {
        int codVideogioco2 = 2;
        recensioneBean.setCodVideogioco(codVideogioco2);
        assertEquals(codVideogioco2, recensioneBean.getCodVideogioco());
    }

    @Test
    void setUsername() {
        String username2 = "rosanera89";
        recensioneBean.setUsername(username2);
        assertEquals(username2, recensioneBean.getUsername());
    }

    @Test
    void setCommento() {
        String commento2 = "Videogioco molto carino";
        recensioneBean.setCommento(commento2);
        assertEquals(commento2, recensioneBean.getCommento());
    }

    @Test
    void setVoto() {
        double voto2 = 4.7D;
        recensioneBean.setVoto(voto2);
        assertEquals(voto2, recensioneBean.getVoto());
    }
}