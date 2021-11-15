package nytro.model.bean;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartTest {
    private final List<VideogiocoBean> items = new ArrayList<VideogiocoBean>();
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
    private final Cart cart = new Cart();

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
        items.add(videogiocoBean);
        cart.addItem(videogiocoBean);
    }

    @Test
    void addItem() {
        VideogiocoBean videogioco3 = new VideogiocoBean();

        int codice = 7;
        String ISIN = "210987655671";
        String dataRilascio = "2020-05-12";
        String dataRimozione = null;
        String titolo = "Kingdom Hearts";
        float votoMedio = 5;
        int PEGI = 12;
        InputStream img = new ByteArrayInputStream("prova".getBytes());
        String trailer = "https://www.youtube.com/embed/w7eH0mqK3To";
        float prezzo = 25.99F;
        int copieVendute = 12;
        List<String> console = new ArrayList<>();
        console.add("PS4");
        List<String> generi = new ArrayList<>();
        generi.add("MMORPG");

        videogioco3.setCodice(codice);
        videogioco3.setISIN(ISIN);
        videogioco3.setDataRilascio(dataRilascio);
        videogioco3.setDataRimozione(dataRimozione);
        videogioco3.setTrailer(trailer);
        videogioco3.setTitolo(titolo);
        videogioco3.setVotoMedio(votoMedio);
        videogioco3.setPrezzo(prezzo);
        videogioco3.setPEGI(PEGI);
        videogioco3.setImg(img);
        videogioco3.setCopieVendute(copieVendute);
        videogioco3.setConsole(console);
        videogioco3.setGeneri(generi);

        Cart cart3 = new Cart();
        cart3.addItem(videogioco3);

        assertEquals(cart3.getItems().get(0).getCodice(), videogioco3.getCodice());
        assertEquals(cart3.getItems().get(0).getISIN(), videogioco3.getISIN());
        assertEquals(cart3.getItems().get(0).getDataRilascio(), videogioco3.getDataRilascio());
        assertEquals(cart3.getItems().get(0).getDataRimozione(), videogioco3.getDataRimozione());
        assertEquals(cart3.getItems().get(0).getTitolo(), videogioco3.getTitolo());
        assertEquals(cart3.getItems().get(0).getTrailer(), videogioco3.getTrailer());
        assertEquals(cart3.getItems().get(0).getVotoMedio(), videogioco3.getVotoMedio());
        assertEquals(cart3.getItems().get(0).getPEGI(), videogioco3.getPEGI());
        assertEquals(cart3.getItems().get(0).getPrezzo(), videogioco3.getPrezzo());
        assertEquals(cart3.getItems().get(0).getImg(), videogioco3.getImg());
        assertEquals(cart3.getItems().get(0).getCopieVendute(), videogioco3.getCopieVendute());
        assertEquals(cart3.getItems().get(0).getConsole(), videogioco3.getConsole());
        assertEquals(cart3.getItems().get(0).getGeneri(), videogioco3.getGeneri());
    }

    @Test
    void deleteItem() {
        cart.deleteItem(videogiocoBean);
        for (VideogiocoBean x : cart.getItems()){
            if (x.getCodice() == videogiocoBean.getCodice()) {
                fail();
            }
        }
        assertTrue(true);
    }

    @Test
    void deleteAll() {
        cart.deleteAll();
        Cart cartvoid = new Cart();
        assertEquals(cart,cartvoid );
    }

    @Test
    void contains() {
        for(VideogiocoBean x : cart.getItems()){
            if(x.getCodice() == videogiocoBean.getCodice())
                assertTrue(true);
            return;
        }
        fail();
    }

    @Test
    void getItems() {
        assertEquals(cart.getItems().get(0).getCodice(), videogiocoBean.getCodice());
        assertEquals(cart.getItems().get(0).getISIN(), videogiocoBean.getISIN());
        assertEquals(cart.getItems().get(0).getDataRilascio(), videogiocoBean.getDataRilascio());
        assertEquals(cart.getItems().get(0).getDataRimozione(), videogiocoBean.getDataRimozione());
        assertEquals(cart.getItems().get(0).getTitolo(), videogiocoBean.getTitolo());
        assertEquals(cart.getItems().get(0).getTrailer(), videogiocoBean.getTrailer());
        assertEquals(cart.getItems().get(0).getVotoMedio(), videogiocoBean.getVotoMedio());
        assertEquals(cart.getItems().get(0).getPEGI(), videogiocoBean.getPEGI());
        assertEquals(cart.getItems().get(0).getPrezzo(), videogiocoBean.getPrezzo());
        assertEquals(cart.getItems().get(0).getImg(), videogiocoBean.getImg());
        assertEquals(cart.getItems().get(0).getCopieVendute(), videogiocoBean.getCopieVendute());
        assertEquals(cart.getItems().get(0).getConsole(), videogiocoBean.getConsole());
        assertEquals(cart.getItems().get(0).getGeneri(), videogiocoBean.getGeneri());
    }

    @Test
    void setItems() {
        VideogiocoBean videogioco2 = new VideogiocoBean();

        int codice = 7;
        String ISIN = "210987654321";
        String dataRilascio = "2020-05-12";
        String dataRimozione = null;
        String titolo = "Kingdom Hearts";
        float votoMedio = 5;
        int PEGI = 12;
        InputStream img = new ByteArrayInputStream("prova".getBytes());
        String trailer = "https://www.youtube.com/embed/w7eH0mqK3To";
        float prezzo = 25.99F;
        int copieVendute = 12;
        List<String> console = new ArrayList<>();
        console.add("PS4");
        List<String> generi = new ArrayList<>();
        generi.add("MMORPG");

        videogioco2.setCodice(codice);
        videogioco2.setISIN(ISIN);
        videogioco2.setDataRilascio(dataRilascio);
        videogioco2.setDataRimozione(dataRimozione);
        videogioco2.setTrailer(trailer);
        videogioco2.setTitolo(titolo);
        videogioco2.setVotoMedio(votoMedio);
        videogioco2.setPrezzo(prezzo);
        videogioco2.setPEGI(PEGI);
        videogioco2.setImg(img);
        videogioco2.setCopieVendute(copieVendute);
        videogioco2.setConsole(console);
        videogioco2.setGeneri(generi);

        List<VideogiocoBean> items2 = new ArrayList<VideogiocoBean>();
        items2.add(videogioco2);
        cart.setItems(items2);

        assertEquals(cart.getItems().get(0).getCodice(), videogioco2.getCodice());
        assertEquals(cart.getItems().get(0).getISIN(), videogioco2.getISIN());
        assertEquals(cart.getItems().get(0).getDataRilascio(), videogioco2.getDataRilascio());
        assertEquals(cart.getItems().get(0).getDataRimozione(), videogioco2.getDataRimozione());
        assertEquals(cart.getItems().get(0).getTitolo(), videogioco2.getTitolo());
        assertEquals(cart.getItems().get(0).getTrailer(), videogioco2.getTrailer());
        assertEquals(cart.getItems().get(0).getVotoMedio(), videogioco2.getVotoMedio());
        assertEquals(cart.getItems().get(0).getPEGI(), videogioco2.getPEGI());
        assertEquals(cart.getItems().get(0).getPrezzo(), videogioco2.getPrezzo());
        assertEquals(cart.getItems().get(0).getImg(), videogioco2.getImg());
        assertEquals(cart.getItems().get(0).getCopieVendute(), videogioco2.getCopieVendute());
        assertEquals(cart.getItems().get(0).getConsole(), videogioco2.getConsole());
        assertEquals(cart.getItems().get(0).getGeneri(), videogioco2.getGeneri());
    }
}