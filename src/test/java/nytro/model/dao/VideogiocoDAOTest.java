package nytro.model.dao;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.*;
import nytro.model.idao.IAccountDAO;
import nytro.model.idao.IVideogiocoDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class VideogiocoDAOTest {
    private final List<VideogiocoBean> giochi = new ArrayList<>();
    private final VideogiocoBean videogioco1 = new VideogiocoBean();
    private final VideogiocoBean videogioco2 = new VideogiocoBean();
    private final VideogiocoBean videogioco3 = new VideogiocoBean();
    private final VideogiocoBean videogioco4 = new VideogiocoBean();
    private final VideogiocoBean videogioco5 = new VideogiocoBean();
    private final IVideogiocoDAO videogiocoDAO = new VideogiocoDAO();
    private final int codice1 = 1;
    private final int codice2 = 2;
    private final int codice3 = 3;
    private final int codice4 = 4;
    private final int codice5 = 5;
    private final String titolo1 = "Assassin's Creed: Origins";
    private final String titolo2 = "Super Smash Bros. Ultimate";
    private final String titolo3 = "Final Fantasy XIV";
    private final String tiolo4 = "To The Moon";
    private final String titolo5 = "Finding Paradise";

    @BeforeEach
    void beforeEach() {
        videogioco1.setCodice(codice1);
        videogioco2.setCodice(codice2);
        videogioco3.setCodice(codice3);
        videogioco4.setCodice(codice4);
        videogioco5.setCodice(codice5);
        videogioco1.setTitolo(titolo1);
        videogioco2.setTitolo(titolo2);
        videogioco3.setTitolo(titolo3);
        videogioco4.setTitolo(tiolo4);
        videogioco5.setTitolo(titolo5);
        giochi.add(videogioco1);
        giochi.add(videogioco2);
        giochi.add(videogioco3);
        giochi.add(videogioco4);
        giochi.add(videogioco5);
    }

    @Test
    void doRetrieveAll() throws SQLException {
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAll("", "");
        assertEquals(giochi.get(0).getCodice(), giochiBD.get(0).getCodice());
        assertEquals(giochi.get(0).getTitolo(), giochiBD.get(0).getTitolo());
        assertEquals(giochi.get(1).getCodice(), giochiBD.get(1).getCodice());
        assertEquals(giochi.get(1).getTitolo(), giochiBD.get(1).getTitolo());
        assertEquals(giochi.get(2).getCodice(), giochiBD.get(2).getCodice());
        assertEquals(giochi.get(2).getTitolo(), giochiBD.get(2).getTitolo());
        assertEquals(giochi.get(3).getCodice(), giochiBD.get(3).getCodice());
        assertEquals(giochi.get(3).getTitolo(), giochiBD.get(3).getTitolo());
        assertEquals(giochi.get(4).getCodice(), giochiBD.get(4).getCodice());
        assertEquals(giochi.get(4).getTitolo(), giochiBD.get(4).getTitolo());
    }

    @Test
    void doRetrieveAllOrdering() throws SQLException {
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAll("ISIN", "123456789012");
        assertEquals(giochi.get(0).getCodice(), giochiBD.get(0).getCodice());
        assertEquals(giochi.get(0).getTitolo(), giochiBD.get(0).getTitolo());
    }

    @Test
    void doRetrieveAllLibreria() throws SQLException {
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAllLibreria("mariorossi91", "ISIN");
        assertEquals(giochi.get(0).getCodice(), giochiBD.get(0).getCodice());
        assertEquals(giochi.get(0).getTitolo(), giochiBD.get(0).getTitolo());
        assertEquals(giochi.get(1).getCodice(), giochiBD.get(1).getCodice());
        assertEquals(giochi.get(1).getTitolo(), giochiBD.get(1).getTitolo());
    }

    @Test
    void doRetrieveByCodice() throws SQLException {
        VideogiocoBean giocoBD = videogiocoDAO.doRetrieveByCodice(1, "123456789012");
        assertEquals(giochi.get(0).getCodice(), giocoBD.getCodice());
        assertEquals(giochi.get(0).getTitolo(), giocoBD.getTitolo());
    }

    @Test
    void doSaveVideogiocoPagamento() throws SQLException {
        int code = 6;
        String isin = "123456789012";
        String datarilascio = "2014-10-10";
        String datarimozione = null;
        String titolo = "Pokemon perla";
        float votoMedio = 4.5F;
        int PEGI = 12;
        InputStream img = new ByteArrayInputStream("prova".getBytes());
        String trailer = "https://www.youtube.com/embed/w7eH0mqK3To";
        float prezzo = 59.99F;
        int copieVendute = 7;
        List<String> console = new ArrayList<>();
        console.add("PS4");
        List<String> generi = new ArrayList<>();
        generi.add("Picchiaduro");

        VideogiocoBean videogioco2 = new VideogiocoBean();
        videogioco2.setCodice(code);
        videogioco2.setISIN(isin);
        videogioco2.setDataRilascio(datarilascio);
        videogioco2.setDataRimozione(datarimozione);
        videogioco2.setTitolo(titolo);
        videogioco2.setVotoMedio(votoMedio);
        videogioco2.setPEGI(PEGI);
        videogioco2.setImg(img);
        videogioco2.setTrailer(trailer);
        videogioco2.setPrezzo(prezzo);
        videogioco2.setCopieVendute(copieVendute);

        videogiocoDAO.doSaveVideogiocoPagamento(videogioco2, generi.get(0), console.get(0));

        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAll("ISIN", "123456789012");
        for (VideogiocoBean x : giochiBD) {
            if (videogioco2.getTitolo().equals(x.getTitolo())) {
                assertEquals(videogioco2.getTitolo(), x.getTitolo());
                deleteVideogioco(x);
            }
        }

    }

    @Test
    void doUpdate() throws SQLException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        gioco.setPrezzo(30.95F);
        gioco.setTitolo("Nuovo");
        videogiocoDAO.doUpdate(gioco);
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAll("ISIN", "");
        for (VideogiocoBean x : giochiBD) {
            if (gioco.getTitolo().equals(x.getTitolo())) {
                assertEquals(gioco.getTitolo(), x.getTitolo());
                assertEquals(gioco.getPrezzo(), x.getPrezzo());
                gioco.setPrezzo(69.99F);
                gioco.setTitolo("Assassin's Creed: Origins");
                videogiocoDAO.doUpdate(gioco);
            }
        }
    }

    @Test
    void doDeleteVideogioco() throws SQLException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        videogiocoDAO.doDeleteVideogioco(gioco);
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAll("ISIN", "");
        for (VideogiocoBean x : giochiBD) {
            if (gioco.getTitolo().equals(x.getTitolo())) {
                if (x.getDataRimozione() != null || !x.getDataRimozione().equals("")) {
                    assertTrue(true);
                    gioco.setDataRimozione(null);
                    videogiocoDAO.doUpdate(gioco);
                } else {
                    fail();
                }
            }
        }
    }

    @Test
    void doDeleteFromLibreria() throws SQLException {
        videogiocoDAO.doDeleteFromLibreria("mariorossi91", 1);
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAllLibreria("mariorossi91", "ISIN");
        for (VideogiocoBean x : giochiBD) {
            if (x.getTitolo().equals(videogioco1.getTitolo())) {
                fail();
                return;
            }
        }
        assertTrue(true);
        videogiocoDAO.doSaveToLibreria("mariorossi91", 1);
    }

    @Test
    void doSaveToLibreria() throws SQLException {
        videogiocoDAO.doSaveToLibreria("mariorossi91", 3);
        List<VideogiocoBean> giochiBD = (List<VideogiocoBean>) videogiocoDAO.doRetrieveAllLibreria("mariorossi91", "ISIN");
        for (VideogiocoBean x : giochiBD) {
            if (x.getCodice() == 3) {
                assertTrue(true);
                videogiocoDAO.doDeleteFromLibreria("mariorossi91", 3);
                return;
            }
        }
        fail();
    }

    @Test
    void doRetrieveAppartenenzaAllaLibreria() throws SQLException {
        if (videogiocoDAO.doRetrieveAppartenenzaAllaLibreria(1, "mariorossi91")) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void doRetrieveAppartenenzaAgliAcquisti() throws SQLException {
        if (videogiocoDAO.doRetrieveAppartenenzaAgliAcquisti(1, "mariorossi91")) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void doAcquisto() throws SQLException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        Cart cart = new Cart();
        cart.addItem(gioco);
        AccountBean account = new AccountBean();
        account.setUsername("luigiverdi94");
        account.setIp("132.645.32.21");
        String carta = "5888736213861112";
        videogiocoDAO.doAcquisto(cart.getItems(), account, carta);
        Collection<Map> giochiDB = videogiocoDAO.doRetrieveAllAcqusiti("luigiverdi94", "Videogioco");
        for (Map x : giochiDB) {
            VideogiocoBean y = (VideogiocoBean) x.get("videogioco");
            if (y.getTitolo().equals(gioco.getTitolo())) {
                assertTrue(true);
                deleteAcquisto(1, account.getUsername());
                return;
            }
        }
        fail();
    }

    @Test
    void doUploadImage() throws SQLException, IOException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        InputStream img = new ByteArrayInputStream("PROVA".getBytes());
        gioco.setImg(img);
        videogiocoDAO.doUploadImage(gioco);
        assertEquals(img, gioco.getImg());
    }

    @Test
    void doUploadImageByCodice() throws SQLException, IOException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        InputStream img = new ByteArrayInputStream("Prova".getBytes());
        gioco.setImg(img);
        videogiocoDAO.doUploadImageByCodice(gioco);
        assertEquals(img, gioco.getImg());
    }

    @Test
    void doDisplayImage() throws SQLException, IOException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        InputStream img = new ByteArrayInputStream("PROVA".getBytes());
        gioco.setImg(img);
        videogiocoDAO.doUpdate(gioco);
        videogiocoDAO.doUploadImageByCodice(gioco);
        byte[] image = videogiocoDAO.doDisplayImage(1);
        if (image != null) {
            assertTrue(true);
        } else {
            fail();
        }
    }

    @Test
    void doRetrievePiuAcquistati() throws SQLException {
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco1);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuAcquistati(1);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
    }

    @Test
    void doRetrievePiuVotati() throws SQLException {
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuVotati(1);
        assertEquals(1, videogiochiDB.size());
    }

    @Test
    void doRetrievePiuGiocati() throws SQLException {
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco3);
        videogiochi.add(videogioco4);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocati(2);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
        assertEquals(videogiochi.get(1).getCodice(), videogiochiDB.get(1).getCodice());
        assertEquals(videogiochi.get(1).getTitolo(), videogiochiDB.get(1).getTitolo());
    }

    @Test
    void doRetrieveByTitolo() throws SQLException {
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrieveByTitolo("To The Moon");
        for (VideogiocoBean x : videogiochiDB) {
            if (x.getTitolo().equals(videogioco4.getTitolo())) {
                assertTrue(true);
                return;
            }
        }
        fail();
    }

    @Test
    void doInsertGenere() throws SQLException {
        String gen = "MMORPG";
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(2, "");
        videogiocoDAO.doInsertGenere(gen, gioco);
        VideogiocoBean giococonfronto = videogiocoDAO.doRetrieveByCodice(2, "");
        List<String> generi = giococonfronto.getGeneri();
        for (String x : generi) {
            if (x.equals(gen)) {
                assertTrue(true);
                deleteGenere(gioco.getCodice(), gen);
                return;
            }
        }
    }

    @Test
    void doRetrievePiuGiocatoFemmine() throws SQLException {
        VideogiocoBean famale = videogiocoDAO.doRetrievePiuGiocatoFemmine();
        assertEquals(famale.getCodice(), videogioco3.getCodice());
        assertEquals(famale.getTitolo(), videogioco3.getTitolo());
    }

    @Test
    void doRetrievePiuGiocatoMaschi() throws SQLException {
        VideogiocoBean male = videogiocoDAO.doRetrievePiuGiocatoMaschi();
        assertEquals(male.getCodice(), videogioco3.getCodice());
        assertEquals(male.getTitolo(), videogioco3.getTitolo());
    }

    @Test
    void doRetrieveCasaByCodice() throws SQLException {
        CasaEditriceBean casa = new CasaEditriceBean();
        casa.setNomeCasaEditrice("Nintendo");
        String nome = videogiocoDAO.doRetrieveCasaByCodice(videogioco2.getCodice());
        assertEquals(nome, casa.getNomeCasaEditrice());
    }

    @Test
    void doRetrieveVideogiocoPiuGiocatoDa() throws SQLException {
        VideogiocoBean male = videogiocoDAO.doRetrieveVideogiocoPiuGiocatoDa(10, 18);
        assertEquals(male.getCodice(), videogioco3.getCodice());
        assertEquals(male.getTitolo(), videogioco3.getTitolo());
    }

    @Test
    void doRetrievePiuGiocatiFemmine() throws SQLException {
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco3);
        videogiochi.add(videogioco4);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocatiFemmine(2);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
        assertEquals(videogiochi.get(1).getCodice(), videogiochiDB.get(1).getCodice());
        assertEquals(videogiochi.get(1).getTitolo(), videogiochiDB.get(1).getTitolo());
    }

    @Test
    void doRetrievePiuGiocatiMaschi() throws SQLException {
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco3);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocatiMaschi(1);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
    }

    @Test
    void doRetrievePiuGiocatiGenderless() throws SQLException {
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("lucagialli01");
        GiocatoreBean giocatore = accountDAO.doRetrieveGiocatore(accountBean);
        giocatore.setGenere(null);
        accountDAO.doUpdateGiocatore(giocatore);
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco3);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocatiGenderless(1);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
        giocatore.setGenere("m");
        accountDAO.doUpdateGiocatore(giocatore);
    }

    @Test
    void doRetrievePiuGiocatiFemmineCasaEditrice() throws SQLException {
        String casaISIN = "675648205634";
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco4);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocatiFemmineCasaEditrice(1, casaISIN);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
    }

    @Test
    void doRetrievePiuGiocatiMaschiCasaEditrice() throws SQLException {
        String casaISIN = "675648205634";
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco4);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocatiMaschiCasaEditrice(1, casaISIN);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
    }

    @Test
    void doRetrievePiuGiocatiGenderlessCasaEditrice() throws SQLException {
        String casaISIN = "237648450745";
        IAccountDAO accountDAO = new AccountDAO();
        AccountBean accountBean = accountDAO.doRetrieveByUsername("lucagialli01");
        GiocatoreBean giocatore = accountDAO.doRetrieveGiocatore(accountBean);
        giocatore.setGenere(null);
        accountDAO.doUpdateGiocatore(giocatore);
        List<VideogiocoBean> videogiochi = new ArrayList<>();
        videogiochi.add(videogioco3);
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuGiocatiGenderlessCasaEditrice(1, casaISIN);
        assertEquals(videogiochi.get(0).getCodice(), videogiochiDB.get(0).getCodice());
        assertEquals(videogiochi.get(0).getTitolo(), videogiochiDB.get(0).getTitolo());
        giocatore.setGenere("m");
        accountDAO.doUpdateGiocatore(giocatore);
    }

    @Test
    void doRetrievePiuVotatiCasaEditrice() throws SQLException {
        String casaISIN = "123456789012";
        List<VideogiocoBean> videogiochiDB = (List<VideogiocoBean>) videogiocoDAO.doRetrievePiuVotatiCasaEditrice(1, casaISIN);
        assertEquals(1, videogiochiDB.size());
    }

    @Test
    void doRetrieveAllAcqusiti() throws SQLException {
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        Cart cart = new Cart();
        cart.addItem(gioco);
        AccountBean account = new AccountBean();
        account.setUsername("luigiverdi94");
        account.setIp("132.645.32.21");
        String carta = "5888736213861112";
        videogiocoDAO.doAcquisto(cart.getItems(), account, carta);
        Collection<Map> giochiDB = videogiocoDAO.doRetrieveAllAcqusiti("luigiverdi94", "Videogioco");
        for (Map x : giochiDB) {
            VideogiocoBean y = (VideogiocoBean) x.get("videogioco");
            if (y.getTitolo().equals(gioco.getTitolo())) {
                assertTrue(true);
                deleteAcquisto(1, account.getUsername());
                return;
            }
        }
        fail();
    }

    @Test
    void doSetDisponibleVideogioco() throws SQLException {
        videogiocoDAO.doSetDisponibleVideogioco(1);
        VideogiocoBean gioco = videogiocoDAO.doRetrieveByCodice(1, "");
        assertEquals(gioco.getDataRimozione(), null);
    }


    public void deleteVideogioco(VideogiocoBean bean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "DELETE FROM videogioco WHERE Titolo = ?;";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, bean.getTitolo());

            System.out.println("Delete: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

    }

    public void deleteAcquisto(int code, String user) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "DELETE FROM ACQUISTA WHERE Videogioco = ? AND Username = ?;";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(2, user);
            preparedStatement.setInt(1, code);

            System.out.println("Delete: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

    }

    public void deleteGenere(int code, String gen) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "DELETE FROM Genere WHERE Videogioco = ? AND Nome = ?;";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(2, gen);
            preparedStatement.setInt(1, code);

            System.out.println("Delete: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

    }
}