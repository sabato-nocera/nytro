package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class VideogiocoBeanTest {
    private final VideogiocoBean videogiocoBean = new VideogiocoBean();
    private final int codice = 1;
    private final String isin = "123456789012";
    private final String datarilascio="2014-10-10";
    private final String datarimozione="2019-10-10";
    private final String titolo="Final FantasyXIV";
    private final float votoMedio = 4.5F;
    private final int PEGI = 12;
    private final InputStream img = new ByteArrayInputStream("prova".getBytes());
    private final String trailer = "https://www.youtube.com/embed/w7eH0mqK3To";
    private final float prezzo = 59.99F;
    private final int copieVendute = 7;
    private final List<String> console = new ArrayList<>();
    private final List<String> generi = new ArrayList<>();

    @BeforeEach
    void beforeEach(){
        videogiocoBean.setCodice(codice);
        videogiocoBean.setISIN(isin);
        videogiocoBean.setDataRilascio(datarilascio);
        videogiocoBean.setDataRimozione(datarimozione);
        videogiocoBean.setTitolo(titolo);
        videogiocoBean.setVotoMedio(votoMedio);
        videogiocoBean.setPEGI(PEGI);
        videogiocoBean.setImg(img);
        videogiocoBean.setTrailer(trailer);
        videogiocoBean.setPrezzo(prezzo);
        videogiocoBean.setCopieVendute(copieVendute);
        console.add("PS4");
        videogiocoBean.setConsole(console);
        generi.add("picchiaduro");
        videogiocoBean.setGeneri(generi);
    }

    @Test
    void getCodice() { assertEquals(codice, videogiocoBean.getCodice()); }

    @Test
    void getISIN() { assertEquals(isin, videogiocoBean.getISIN()); }

    @Test
    void getDataRilascio() { assertEquals(datarilascio, videogiocoBean.getDataRilascio()); }

    @Test
    void getDataRimozione() { assertEquals(datarimozione, videogiocoBean.getDataRimozione()); }

    @Test
    void getTitolo() { assertEquals(titolo, videogiocoBean.getTitolo()); }

    @Test
    void getVotoMedio() { assertEquals(votoMedio, videogiocoBean.getVotoMedio()); }

    @Test
    void getPEGI() { assertEquals(PEGI, videogiocoBean.getPEGI()); }

    @Test
    void getImg() { assertEquals(img, videogiocoBean.getImg()); }

    @Test
    void getTrailer() { assertEquals(trailer, videogiocoBean.getTrailer()); }

    @Test
    void getPrezzo() { assertEquals(prezzo, videogiocoBean.getPrezzo()); }

    @Test
    void getCopieVendute() { assertEquals(copieVendute, videogiocoBean.getCopieVendute()); }

    @Test
    void getConsole() { assertEquals(console, videogiocoBean.getConsole()); }

    @Test
    void getGeneri() { assertEquals(generi, videogiocoBean.getGeneri()); }

    @Test
    void setCodice() {
        int codice2 = 2;
        videogiocoBean.setCodice(codice2);
        assertEquals(codice2, videogiocoBean.getCodice());
    }

    @Test
    void setISIN() {
        String isin2 ="210987654321";
        videogiocoBean.setISIN(isin2);
        assertEquals(isin2, videogiocoBean.getISIN());
    }

    @Test
    void setDataRilascio() {
        String datarilascio2 = "2015-10-7";
        videogiocoBean.setDataRilascio(datarilascio2);
        assertEquals(datarilascio2, videogiocoBean.getDataRilascio());
    }

    @Test
    void setDataRimozione() {
        String datarimozione2 = "2021-7-7";
        videogiocoBean.setDataRimozione(datarimozione2);
        assertEquals(datarimozione2, videogiocoBean.getDataRimozione());
    }

    @Test
    void setTitolo() {
        String titolo2 = "Super Smash Bros. Ultimate";
        videogiocoBean.setTitolo(titolo2);
        assertEquals(titolo2, videogiocoBean.getTitolo());
    }

    @Test
    void setVotoMedio() {
        float votoMedio2 = 3.7F;
        videogiocoBean.setVotoMedio(votoMedio2);
        assertEquals(votoMedio2, videogiocoBean.getVotoMedio());
    }

    @Test
    void setPEGI() {
        int pegi2 = 17;
        videogiocoBean.setPEGI(pegi2);
        assertEquals(pegi2, videogiocoBean.getPEGI());
    }

    @Test
    void setImg() {
        InputStream inputStream2 = new ByteArrayInputStream("prova2".getBytes());
        videogiocoBean.setImg(inputStream2);
        assertEquals(inputStream2, videogiocoBean.getImg());
    }

    @Test
    void setTrailer() {
        String trailer2 = "https://www.youtube.com/embed/TUzZKLirkrE";
        videogiocoBean.setTrailer(trailer2);
        assertEquals(trailer2,videogiocoBean.getTrailer());
    }

    @Test
    void setPrezzo() {
        float prezzo2 = 69.99F;
        videogiocoBean.setPrezzo(prezzo2);
        assertEquals(prezzo2, videogiocoBean.getPrezzo());
    }

    @Test
    void setCopieVendute() {
        int copieVendute2 = 4;
        videogiocoBean.setCopieVendute(copieVendute2);
        assertEquals(copieVendute2, videogiocoBean.getCopieVendute());
    }

    @Test
    void setConsole() {
        List<String> console2 = new ArrayList<>();
        console2.add("PS5");
        console2.add("nintendo");
        videogiocoBean.setConsole(console2);
        assertEquals(console2, videogiocoBean.getConsole());
    }

    @Test
    void setGeneri() {
        List<String> generi2 = new ArrayList<>();
        generi2.add("MMORPG");
        generi2.add("Picchiaduro");
        videogiocoBean.setGeneri(generi2);
        assertEquals(generi2, videogiocoBean.getGeneri());
    }


}