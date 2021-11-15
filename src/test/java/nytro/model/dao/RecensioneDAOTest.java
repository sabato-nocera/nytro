package nytro.model.dao;

import nytro.model.bean.RecensioneBean;
import nytro.model.idao.IRecensioneDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RecensioneDAOTest {
    private final RecensioneBean recensioneBean1 = new RecensioneBean();
    private final RecensioneBean recensioneBean2 = new RecensioneBean();
    private final IRecensioneDAO iRecensioneDAO = new RecensioneDAO();
    private final List<RecensioneBean> recensioni = new ArrayList<>();
    private final int numRecensione = 1;
    private final int codVideogioco = 5;
    private final String username = "luigiverdi94";
    private final String commento = "Molto godibile anche senza aver giocato al primo.";
    private final double voto = 4;
    private final int numRecensione2 = 2;
    private final int codVideogioco2 = 5;
    private final String username2 = "rosanera89";
    private final String commento2 = "Stessa casa di To the moon, una garanzia.";
    private final double voto2 = 4.5;

    @BeforeEach
    void beforeEach(){
       recensioneBean1.setNumRecensione(numRecensione);
       recensioneBean1.setCodVideogioco(codVideogioco);
       recensioneBean1.setUsername(username);
       recensioneBean1.setCommento(commento);
       recensioneBean1.setVoto(voto);
       recensioni.add(recensioneBean1);
       recensioneBean2.setNumRecensione(numRecensione2);
       recensioneBean2.setCodVideogioco(codVideogioco2);
       recensioneBean2.setUsername(username2);
       recensioneBean2.setCommento(commento2);
       recensioneBean2.setVoto(voto2);
       recensioni.add(recensioneBean2);
    }

    @Test
    void doRetrieveAllByCodice() throws SQLException{
        List<RecensioneBean> recensioni2 = new ArrayList<>();
        recensioni2 = (List<RecensioneBean>) iRecensioneDAO.doRetrieveAllByCodice("Username", 5);
        assertEquals(recensioni.get(0).getNumRecensione(), recensioni2.get(0).getNumRecensione());
        assertEquals(recensioni.get(0).getCodVideogioco(), recensioni2.get(0).getCodVideogioco());
        assertEquals(recensioni.get(0).getUsername(), recensioni2.get(0).getUsername());
        assertEquals(recensioni.get(0).getCommento(), recensioni2.get(0).getCommento());
        assertEquals(recensioni.get(0).getVoto(), recensioni2.get(0).getVoto());
        assertEquals(recensioni.get(1).getNumRecensione(), recensioni2.get(1).getNumRecensione());
        assertEquals(recensioni.get(1).getCodVideogioco(), recensioni2.get(1).getCodVideogioco());
        assertEquals(recensioni.get(1).getUsername(), recensioni2.get(1).getUsername());
        assertEquals(recensioni.get(1).getCommento(), recensioni2.get(1).getCommento());
        assertEquals(recensioni.get(1).getVoto(), recensioni2.get(1).getVoto());
    }

    @Test
    void doRetrieveAllByRange() throws SQLException{
        List<RecensioneBean> recensioni2 = new ArrayList<>();
        recensioni2 = (List<RecensioneBean>) iRecensioneDAO.doRetrieveAllByRange( 5, "Voto",3,5);
        assertEquals(recensioni.get(0).getNumRecensione(), recensioni2.get(0).getNumRecensione());
        assertEquals(recensioni.get(0).getCodVideogioco(), recensioni2.get(0).getCodVideogioco());
        assertEquals(recensioni.get(0).getUsername(), recensioni2.get(0).getUsername());
        assertEquals(recensioni.get(0).getCommento(), recensioni2.get(0).getCommento());
        assertEquals(recensioni.get(0).getVoto(), recensioni2.get(0).getVoto());
        assertEquals(recensioni.get(1).getNumRecensione(), recensioni2.get(1).getNumRecensione());
        assertEquals(recensioni.get(1).getCodVideogioco(), recensioni2.get(1).getCodVideogioco());
        assertEquals(recensioni.get(1).getUsername(), recensioni2.get(1).getUsername());
        assertEquals(recensioni.get(1).getCommento(), recensioni2.get(1).getCommento());
        assertEquals(recensioni.get(1).getVoto(), recensioni2.get(1).getVoto());
    }

    @Test
    void doRetrieveAllByRangeNoOrdering() throws SQLException{
        List<RecensioneBean> recensioni2 = new ArrayList<>();
        recensioni2 = (List<RecensioneBean>) iRecensioneDAO.doRetrieveAllByRange( 5, "",3,5);
        assertEquals(recensioni.get(0).getNumRecensione(), recensioni2.get(0).getNumRecensione());
        assertEquals(recensioni.get(0).getCodVideogioco(), recensioni2.get(0).getCodVideogioco());
        assertEquals(recensioni.get(0).getUsername(), recensioni2.get(0).getUsername());
        assertEquals(recensioni.get(0).getCommento(), recensioni2.get(0).getCommento());
        assertEquals(recensioni.get(0).getVoto(), recensioni2.get(0).getVoto());
        assertEquals(recensioni.get(1).getNumRecensione(), recensioni2.get(1).getNumRecensione());
        assertEquals(recensioni.get(1).getCodVideogioco(), recensioni2.get(1).getCodVideogioco());
        assertEquals(recensioni.get(1).getUsername(), recensioni2.get(1).getUsername());
        assertEquals(recensioni.get(1).getCommento(), recensioni2.get(1).getCommento());
        assertEquals(recensioni.get(1).getVoto(), recensioni2.get(1).getVoto());
    }

    @Test
    void doRetrieveByUsername() throws SQLException{
        RecensioneBean recensioneDB = iRecensioneDAO.doRetrieveByUsername("rosanera89",5 );
        assertEquals(recensioneBean2.getNumRecensione(),recensioneDB.getNumRecensione());
        assertEquals(recensioneBean2.getCodVideogioco(),recensioneDB.getCodVideogioco());
        assertEquals(recensioneBean2.getUsername(),recensioneDB.getUsername());
        assertEquals(recensioneBean2.getCommento(),recensioneDB.getCommento());
        assertEquals(recensioneBean2.getVoto(),recensioneDB.getVoto());
    }

    @Test
    void doCheckFalse() throws SQLException{
        if(iRecensioneDAO.doCheck(recensioneBean1)){
            assertTrue(false);
        }else{
            assertTrue(true);
        }
    }

    @Test
    void doCheckTrue() throws SQLException{
        recensioneBean1.setUsername("mariorossi91");
        if(iRecensioneDAO.doCheck(recensioneBean1)){
            assertTrue(true);
        }else{
            assertTrue(false);
        }
    }

    @Test
    void doSave() throws SQLException{
        RecensioneBean newRecensione = new RecensioneBean();
        newRecensione.setNumRecensione(3);
        newRecensione.setCodVideogioco(5);
        newRecensione.setUsername("mariorossi91");
        newRecensione.setCommento("Molto godibile anche senza aver giocato al primo.");
        newRecensione.setVoto(4);
        iRecensioneDAO.doSave(newRecensione);
        List<RecensioneBean> recensioni2 = new ArrayList<>();
        recensioni2 = (List<RecensioneBean>) iRecensioneDAO.doRetrieveAllByCodice("Username", 5);
        for(RecensioneBean x : recensioni2){
            if(x.getUsername().equals("mariorossi91")){
                assertEquals(newRecensione.getCodVideogioco(),x.getCodVideogioco());
                assertEquals(newRecensione.getUsername(),x.getUsername());
                assertEquals(newRecensione.getCommento(),x.getCommento());
                assertEquals(newRecensione.getVoto(),x.getVoto());
            }
        }
        iRecensioneDAO.doDelete("mariorossi91",5);
    }

    @Test
    void doUpdate() throws  SQLException{
        recensioneBean2.setCommento("Molto godibile anche senza aver giocato al primo.");
        iRecensioneDAO.doUpdate(recensioneBean2);
        RecensioneBean recensioneDB = iRecensioneDAO.doRetrieveByUsername("rosanera89", 5);
        assertEquals(recensioneBean2.getNumRecensione(),recensioneDB.getNumRecensione());
        assertEquals(recensioneBean2.getCodVideogioco(),recensioneDB.getCodVideogioco());
        assertEquals(recensioneBean2.getUsername(),recensioneDB.getUsername());
        assertEquals(recensioneBean2.getCommento(),recensioneDB.getCommento());
        assertEquals(recensioneBean2.getVoto(),recensioneDB.getVoto());
        recensioneBean2.setCommento("Stessa casa di To the moon, una garanzia.");
        iRecensioneDAO.doUpdate(recensioneBean2);
    }

    @Test
    void doDelete() throws SQLException{
        RecensioneBean newRecensione = new RecensioneBean();
        newRecensione.setNumRecensione(5);
        newRecensione.setCodVideogioco(5);
        newRecensione.setUsername("mariablu98");
        newRecensione.setCommento("Molto godibile anche senza aver giocato al primo.");
        newRecensione.setVoto(5);
        iRecensioneDAO.doSave(newRecensione);
        RecensioneBean recensioneBeanDB = iRecensioneDAO.doRetrieveByUsername("mariablu98",5);
        recensioneBeanDB.setNumRecensione(recensioneBeanDB.getCodVideogioco());
        iRecensioneDAO.doDelete(recensioneBeanDB);
        List<RecensioneBean> recensioni2 = (List<RecensioneBean>) iRecensioneDAO.doRetrieveAllByCodice("", 5);
        for(RecensioneBean x : recensioni2){
            if(x.getUsername().equals("mariablu98")){
                fail();
                return;
            }
        }
        assertTrue(true);
    }

    @Test
    void testDoDelete() throws SQLException{
        RecensioneBean newRecensione = new RecensioneBean();
        newRecensione.setNumRecensione(3);
        newRecensione.setCodVideogioco(5);
        newRecensione.setUsername("mariorossi91");
        newRecensione.setCommento("Molto godibile anche senza aver giocato al primo.");
        newRecensione.setVoto(4);
        iRecensioneDAO.doSave(newRecensione);
        iRecensioneDAO.doDelete("mariorossi91",5);
        List<RecensioneBean> recensioni2 = (List<RecensioneBean>) iRecensioneDAO.doRetrieveAllByCodice("Username", 5);
        for(RecensioneBean x : recensioni2){
            if(x.getUsername().equals("mariorossi91")){
                fail();
                return;
            }
        }
        assertTrue(true);
    }
}