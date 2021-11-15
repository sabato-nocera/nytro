package nytro.model.dao;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.RecensioneBean;
import nytro.model.idao.IRecensioneDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;

public class RecensioneDAO implements IRecensioneDAO {

    public Collection<RecensioneBean> doRetrieveAllByCodice(String order, int codice) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<RecensioneBean> recensioni = new LinkedList<>();

        String selectSQL = "SELECT * FROM recensione WHERE Videogioco = ? ";

        if (order != null && !order.equals(""))
            selectSQL += " ORDER BY " + order;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, codice);

            System.out.println("doRetrieveAllByCodice: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                RecensioneBean bean = new RecensioneBean();
                bean.setNumRecensione(rs.getInt("Num_Recensione"));
                bean.setCodVideogioco(rs.getInt("Videogioco"));
                bean.setUsername(rs.getString("Username"));
                bean.setCommento(rs.getString("Commento"));
                bean.setVoto(rs.getDouble("Voto"));

                recensioni.add(bean);
            }
            connection.commit();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return recensioni;
    }

    public Collection<RecensioneBean> doRetrieveAllByRange(int codVideogioco, String order, int min, int max) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<RecensioneBean> recensioni = new LinkedList<>();

        String selectSQL = "SELECT * from videogioco, recensione where videogioco.Codice = recensione.videogioco && videogioco.Codice=? && recensione.voto>=? && recensione.voto<=? ";

        if (order != null && !order.equals(""))
            selectSQL += " ORDER BY " + order;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, codVideogioco);
            preparedStatement.setInt(2, min);
            preparedStatement.setInt(3, max);

            System.out.println("doRetrieveAllByRange: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                RecensioneBean bean = new RecensioneBean();
                bean.setNumRecensione(rs.getInt("Num_Recensione"));
                bean.setCodVideogioco(rs.getInt("Videogioco"));
                bean.setUsername(rs.getString("Username"));
                bean.setCommento(rs.getString("Commento"));
                bean.setVoto(rs.getDouble("Voto"));

                recensioni.add(bean);
            }
            connection.commit();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return recensioni;
    }

    public RecensioneBean doRetrieveByUsername(String username, int codiceVideogioco) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM recensione WHERE Username = ? AND Videogioco = ?";
        RecensioneBean recensione = new RecensioneBean();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setInt(2, codiceVideogioco);

            System.out.println("doRetrieveByUsername: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                recensione.setNumRecensione(rs.getInt("Num_Recensione"));
                recensione.setCodVideogioco(rs.getInt("Videogioco"));
                recensione.setUsername(rs.getString("Username"));
                recensione.setCommento(rs.getString("Commento"));
                recensione.setVoto(rs.getDouble("Voto"));

            }
            connection.commit();
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return recensione;
    }

    public boolean doCheck(RecensioneBean recensione) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String countSQL = "SELECT COUNT(*) FROM recensione WHERE recensione.videogioco = ? AND recensione.username = ?";
        int n;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(countSQL);

            preparedStatement.setInt(1, recensione.getCodVideogioco());
            preparedStatement.setString(2, recensione.getUsername());

            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
            n = rs.getInt(1);

            System.out.println("doCount: " + preparedStatement.toString() + n);

            if (n != 0) {
                System.out.println("Errore. Recensione gia' rilasciata per questo gioco.");
                return false;
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return true;
    }

    public boolean doSave(RecensioneBean recensione) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "CALL inserisci_recensione(?, ?, ?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, recensione.getCodVideogioco());
            preparedStatement.setString(2, recensione.getUsername());
            preparedStatement.setString(3, recensione.getCommento());
            preparedStatement.setDouble(4, recensione.getVoto());

            System.out.println("doSave: " + preparedStatement.toString());
            preparedStatement.executeUpdate();
            doRetrieveAllByCodice(null, recensione.getCodVideogioco());
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return true;
    }

    public void doUpdate(RecensioneBean recensione) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String updateSQL = "UPDATE recensione SET Commento = ? WHERE (Num_Recensione = ?) and (Videogioco = ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, recensione.getCommento());
            preparedStatement.setInt(2, recensione.getNumRecensione());
            preparedStatement.setInt(3, recensione.getCodVideogioco());

            System.out.println("doUpdate: " + preparedStatement.toString());
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

    public boolean doDelete(RecensioneBean recensione) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result;

        String deleteSQL = "CALL rimuovi_recensione(?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setInt(1, recensione.getNumRecensione());
            preparedStatement.setString(2, recensione.getUsername());

            System.out.println("doDelete: " + preparedStatement.toString());
            result = preparedStatement.executeUpdate();
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return (result != 0);
    }

    public boolean doDelete(String username, int codVideogioco) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result;

        String deleteSQL = "CALL rimuovi_recensione(?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setInt(1, codVideogioco);
            preparedStatement.setString(2, username);

            System.out.println("doDelete: " + preparedStatement.toString());
            result = preparedStatement.executeUpdate();
            doRetrieveAllByCodice(null, codVideogioco);
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return (result != 0);
    }
}
