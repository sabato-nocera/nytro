package nytro.model.idao;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.AccountBean;
import nytro.model.bean.VideogiocoBean;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface IVideogiocoDAO {
    static List<String> doGetGenere(VideogiocoBean videogiocoBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<String> generi = new ArrayList<String>();

        String selectSQL = "SELECT Nome FROM genere WHERE Videogioco = ? ";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, videogiocoBean.getCodice());

            System.out.println("doGetGenere: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
                generi.add(rs.getString("Nome"));
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return generi;
    }

    static List<String> doGetConsole(VideogiocoBean videogiocoBean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<String> console = new ArrayList<String>();

        String selectSQL = "SELECT Nome_Console FROM console WHERE Videogioco = ? ";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, videogiocoBean.getCodice());

            System.out.println("doGetConsole: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
                console.add(rs.getString("Nome_Console"));
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return console;
    }

    static List<String> doGetConsoleAcquistate(int videogioco, String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        List<String> console = new ArrayList<String>();

        String selectSQL = "select * from acquista where Videogioco = ? and username=?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, videogioco);
            preparedStatement.setString(2, username);

            System.out.println("doGetConsoleByAcquistati: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next())
                console.add(rs.getString("Nome_Console"));
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return console;
    }

    Collection<VideogiocoBean> doRetrieveAll(String order, String isin) throws SQLException;

    Collection<VideogiocoBean> doRetrieveAllLibreria(String username, String order) throws SQLException;

    VideogiocoBean doRetrieveByCodice(int codice, String isin) throws SQLException;

    void doSaveVideogiocoPagamento(VideogiocoBean bean, String genere, String console) throws SQLException;

    void doUpdate(VideogiocoBean bean) throws SQLException;

    void doDeleteVideogioco(VideogiocoBean bean) throws SQLException;

    boolean doDeleteFromLibreria(String username, int codiceVideogiocoDaCancellare) throws SQLException;

    boolean doSaveToLibreria(String username, int codiceVideogiocoDaInserire) throws SQLException;

    boolean doRetrieveAppartenenzaAllaLibreria(int codice, String username) throws SQLException;

    boolean doRetrieveAppartenenzaAgliAcquisti(int codice, String username) throws SQLException;

    void doAcquisto(List<VideogiocoBean> carrello, AccountBean account, String cartaDiPagamento) throws SQLException;

    void doUploadImage(VideogiocoBean bean) throws SQLException, IOException;

    void doUploadImageByCodice(VideogiocoBean bean) throws SQLException, IOException;

    byte[] doDisplayImage(int codice) throws SQLException, IOException;

    Collection<VideogiocoBean> doRetrievePiuAcquistati(int numeroVideogiochi) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuVotati(int numeroVideogiochi) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocati(int numeroVideogiochi) throws SQLException;

    List<VideogiocoBean> doRetrieveByTitolo(String against) throws SQLException;

    void doInsertGenere(String genere, VideogiocoBean tmp) throws SQLException;

    VideogiocoBean doRetrievePiuGiocatoFemmine() throws SQLException;

    VideogiocoBean doRetrievePiuGiocatoMaschi() throws SQLException;

    String doRetrieveCasaByCodice(int codice) throws SQLException;

    ArrayList<VideogiocoBean> doRetrievePerAnnoDiRimozione(String annoRimozione) throws SQLException;

    VideogiocoBean doRetrieveVideogiocoPiuGiocatoDa(int min, int max) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocatiFemmine(int limit) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocatiMaschi(int limit) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocatiGenderless(int limit) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocatiFemmineCasaEditrice(int limit, String isin) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocatiMaschiCasaEditrice(int limit, String isin) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuGiocatiGenderlessCasaEditrice(int limit, String isin) throws SQLException;

    Collection<VideogiocoBean> doRetrievePiuVotatiCasaEditrice(int numeroVideogiochi, String isin) throws SQLException;

    Collection<Map> doRetrieveAllAcqusiti(String username, String order) throws SQLException;

    void doSetDisponibleVideogioco(int codice) throws SQLException;
}
