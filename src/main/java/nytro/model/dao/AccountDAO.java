package nytro.model.dao;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.GiocatoreBean;
import nytro.model.bean.AccountBean;
import nytro.model.bean.AmministratoreBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.idao.IAccountDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class AccountDAO implements IAccountDAO {

    public Collection<AccountBean> doRetrieveAll(String order, int ruolo) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<AccountBean> users = new LinkedList<>();

        String selectSQL = "";

        if (ruolo == 0)
            selectSQL += "SELECT DISTINCT * FROM account, amministratore WHERE Ruolo = " + ruolo + " AND account.Username = amministratore.Username ";
        else if (ruolo == 1)
            selectSQL += "SELECT DISTINCT * FROM account, giocatore WHERE Ruolo = " + ruolo + " AND account.Username = giocatore.Username ";
        else if (ruolo == 2)
            selectSQL += "SELECT DISTINCT * FROM account, azienda, casa_editrice WHERE Ruolo = " + ruolo + " AND account.Username = azienda.Username AND casa_editrice.ISIN=azienda.ISIN ";
        else    //seleziona tutti
            selectSQL = "SELECT DISTINCT * FROM account ";

        //Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
        if (order != null && !order.equals(""))
            selectSQL += " ORDER BY " + order;


        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            System.out.println("doRetrieveAll: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                AccountBean bean = new AccountBean();

                bean.setUsername(rs.getString("Username"));
                bean.setPassword(rs.getString("Password"));
                bean.setEmail(rs.getString("Email"));
                bean.setEmailRecupero(rs.getString("Email_Recupero"));
                bean.setCellulare(rs.getString("Cellulare"));
                bean.setData(rs.getString("Data"));
                bean.setOra(rs.getString("Ora"));
                bean.setIp(rs.getString("IP"));
                bean.setRuolo(rs.getInt("Ruolo"));
                bean.setImgProfilo(rs.getBinaryStream("Img_Profilo"));

                if (ruolo == 0) {
                    users.add(bean);            //Da sostituire con AmministratoreBean nel caso in cui volessi implementare un'estrazione per tutti gli amministratori
                } else if (ruolo == 1) {
                    GiocatoreBean giocatoreBean = new GiocatoreBean(bean);
                    giocatoreBean.setDataIscrizione(rs.getString("Data_Iscrizione"));
                    giocatoreBean.setDataNascita(rs.getString("Data_Nascita"));
                    giocatoreBean.setGenere(rs.getString("Genere"));
                    users.add(giocatoreBean);
                } else if (ruolo == 2) {
                    CasaEditriceBean casaEditriceBean = new CasaEditriceBean(bean);
                    casaEditriceBean.setISIN(rs.getString("ISIN"));
                    casaEditriceBean.setNomeCasaEditrice(rs.getString("Nome_Casa_Editrice"));
                    casaEditriceBean.setCEO(rs.getString("CEO"));
                    casaEditriceBean.setSitoWeb(rs.getString("Sito_Web"));
                    users.add(casaEditriceBean);
                } else {
                    users.add(bean);
                }
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return users;
    }

    public GiocatoreBean doRetrieveGiocatore(AccountBean bean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        GiocatoreBean giocatore = new GiocatoreBean(bean);

        String selectSQL = "SELECT * FROM giocatore WHERE Username = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, bean.getUsername());

            System.out.println("doRetrieveGiocatore: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                giocatore.setDataIscrizione(rs.getString("Data_Iscrizione"));
                giocatore.setDataNascita(rs.getString("Data_Nascita"));
                giocatore.setGenere(rs.getString("Genere"));
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

        return giocatore;
    }

    public CasaEditriceBean doRetrieveCasaEditrice(AccountBean bean) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        CasaEditriceBean casa = new CasaEditriceBean(bean);

        String selectSQL = "SELECT * FROM azienda, casa_editrice WHERE Username = ? AND azienda.ISIN = casa_editrice.ISIN";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, bean.getUsername());

            System.out.println("doRetrieveGiocatore: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                casa.setISIN(rs.getString("ISIN"));
                casa.setNomeCasaEditrice(rs.getString("Nome_Casa_Editrice"));
                casa.setCEO(rs.getString("CEO"));
                casa.setSitoWeb(rs.getString("Sito_Web"));
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

        return casa;
    }

    public AccountBean doRetrieveByUsernamePassword(String username, String password) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        AccountBean bean = new AccountBean();

        String selectSQL = "SELECT * FROM account WHERE Username = ? AND Password = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            System.out.println("doRetrieveByKey: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setUsername(rs.getString("Username"));
                bean.setPassword(rs.getString("Password"));
                bean.setEmail(rs.getString("Email"));
                bean.setEmailRecupero(rs.getString("Email_Recupero"));
                bean.setCellulare(rs.getString("Cellulare"));
                bean.setData(rs.getString("Data"));
                bean.setOra(rs.getString("Ora"));
                bean.setIp(rs.getString("IP"));
                bean.setRuolo(rs.getInt("Ruolo"));
                bean.setImgProfilo(rs.getBinaryStream("Img_Profilo"));
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

        return bean;
    }

    public ArrayList<String> doRetrieveContributoAdmin(String dataInizio, String dataFine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "call piattaforma_videogiochi_tsw.calcolo_contributo_admin(?, ?)";
        ArrayList<String> contributo = new ArrayList<>();

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, dataInizio);
            preparedStatement.setString(2, dataFine);

            System.out.println("doRetrieveContributo: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                contributo.add(rs.getString("ISIN"));
                contributo.add(rs.getString("incassi"));
                contributo.add(rs.getString("contributi_annuali"));
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return contributo;
    }

    public ArrayList<String> doRetrieveContributoCasaEditrice(String isin, String dataInizio, String dataFine) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "call piattaforma_videogiochi_tsw.calcolo_contributo(?, ?)";
        ArrayList<String> contributo = new ArrayList<>();
        contributo.add("Incassi");
        contributo.add("Contributi_Annuali");

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, dataInizio);
            preparedStatement.setString(2, dataFine);

            System.out.println("doRetrieveContributo: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                if (rs.getString("ISIN").equalsIgnoreCase(isin)) {
                    contributo.add(rs.getString("incassi"));
                    contributo.add(rs.getString("contributi_annuali"));
                }
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return contributo;
    }

    public String doRetrieveIsinByUsername(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT * FROM account, azienda WHERE account.Username=azienda.Username AND account.Username = ?";

        String isin = null;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);

            System.out.println("doRetrieveByKey: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                isin = rs.getString("ISIN");
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return isin;
    }

    public AccountBean doRetrieveByUsername(String username) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        AccountBean bean = new AccountBean();

        String selectSQL = "SELECT * FROM account WHERE Username = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);

            System.out.println("doRetrieveByUsername: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean.setUsername(rs.getString("Username"));
                bean.setPassword(rs.getString("Password"));
                bean.setEmail(rs.getString("Email"));
                bean.setEmailRecupero(rs.getString("Email_Recupero"));
                bean.setCellulare(rs.getString("Cellulare"));
                bean.setData(rs.getString("Data"));
                bean.setOra(rs.getString("Ora"));
                bean.setIp(rs.getString("IP"));
                bean.setRuolo(rs.getInt("Ruolo"));
                bean.setImgProfilo(rs.getBinaryStream("Img_Profilo"));
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

        return bean;
    }

    public AccountBean doRetrieveByIsin(String isin) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        AccountBean bean = new AccountBean();

        String selectSQL = "SELECT * FROM account, azienda where account.username=azienda.username and azienda.isin=?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, isin);

            System.out.println("doRetrieveByIsin: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                bean = this.doRetrieveByUsername(rs.getString("Username"));
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

        return bean;
    }

    public void doSaveAccount(AccountBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String insertSQL = "INSERT INTO account(Username, Password, Email, Email_Recupero, Cellulare, Data, Ora, IP, Ruolo, Img_Profilo) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getEmailRecupero());
            preparedStatement.setString(5, account.getCellulare());
            preparedStatement.setString(6, account.getData());
            preparedStatement.setString(7, account.getOra());
            preparedStatement.setString(8, account.getIp());
            preparedStatement.setInt(9, account.getRuolo());
            preparedStatement.setNull(10, java.sql.Types.BLOB);                    //blob settata null temporaneamente

            System.out.println("doSaveAccount: " + preparedStatement.toString());
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

    public void doSaveGiocatore(GiocatoreBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String insertSQL = "CALL inserisci_giocatore(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getEmailRecupero());
            preparedStatement.setString(5, account.getCellulare());
            preparedStatement.setString(6, account.getIp());
            preparedStatement.setNull(7, java.sql.Types.BLOB);                        //blob settata null temporaneamente
            preparedStatement.setString(8, account.getDataNascita());
            preparedStatement.setString(9, account.getGenere());

            System.out.println("doSaveGiocatore: " + preparedStatement.toString());
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

    public void doSaveCasaEditrice(CasaEditriceBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;


        String insertSQL = "CALL inserisci_azienda(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);

            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.setString(3, account.getEmail());
            preparedStatement.setString(4, account.getEmailRecupero());
            preparedStatement.setString(5, account.getCellulare());
            preparedStatement.setString(6, account.getIp());
            preparedStatement.setNull(7, java.sql.Types.BLOB);                    //blob settata null temporaneamente
            preparedStatement.setString(8, account.getISIN());
            preparedStatement.setString(9, account.getNomeCasaEditrice());
            preparedStatement.setString(10, account.getCEO());
            preparedStatement.setString(11, account.getSitoWeb());

            System.out.println("doSaveCasaEditrice: " + preparedStatement.toString());
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

    public void doUpdate(AccountBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE account SET  Password = ?, Email = ?, Email_Recupero=?, Cellulare=?, Data=current_date(), Ora=current_time(), IP=?, Ruolo=?, Img_Profilo=? WHERE Username = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, account.getPassword());
            preparedStatement.setString(2, account.getEmail());
            preparedStatement.setString(3, account.getEmailRecupero());
            preparedStatement.setString(4, account.getCellulare());
            preparedStatement.setString(5, account.getIp());
            preparedStatement.setInt(6, account.getRuolo());
            preparedStatement.setBlob(7, account.getImgProfilo());
            preparedStatement.setString(8, account.getUsername());

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

    public void doUpdateGiocatore(GiocatoreBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE giocatore SET  Data_Nascita = ?, Data_Iscrizione = ?, Genere=? WHERE Username = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, account.getDataNascita());
            preparedStatement.setString(2, account.getDataIscrizione());
            preparedStatement.setString(3, account.getGenere());
            preparedStatement.setString(4, account.getUsername());

            System.out.println("doUpdateGiocatore: " + preparedStatement.toString());
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

    public void doUpdateAmministratore(AmministratoreBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String updateSQL = "UPDATE amministratore SET  Nome = ?, Cognome = ? WHERE Username = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(updateSQL);

            preparedStatement.setString(1, account.getNome());
            preparedStatement.setString(2, account.getCognome());
            preparedStatement.setString(3, account.getUsername());

            System.out.println("doUpdateAmministratore: " + preparedStatement.toString());
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

    public void doUpdateCasaEditrice(CasaEditriceBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement1 = null;
        PreparedStatement preparedStatement2 = null;

        String updateSQL1 = "UPDATE casa_editrice SET  CEO = ?, Sito_Web = ? WHERE ISIN = ?";
        String updateSQL2 = "UPDATE azienda SET  Nome_Casa_Editrice = ? WHERE Username = ?";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement1 = connection.prepareStatement(updateSQL1);
            preparedStatement2 = connection.prepareStatement(updateSQL2);

            preparedStatement1.setString(1, account.getCEO());
            preparedStatement1.setString(2, account.getSitoWeb());
            preparedStatement1.setString(3, account.getISIN());

            preparedStatement2.setString(1, account.getNomeCasaEditrice());
            preparedStatement2.setString(2, account.getUsername());

            System.out.println("doUpdateCasaEditrice: " + preparedStatement1.toString() + " " + preparedStatement2.toString());
            preparedStatement1.executeUpdate();
            preparedStatement2.executeUpdate();
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

        } finally {
            try {
                if (preparedStatement1 != null)
                    preparedStatement1.close();
                if (preparedStatement2 != null)
                    preparedStatement2.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }


    }

    public boolean doDelete(AccountBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int result;

        String deleteSQL = "DELETE FROM account WHERE Username = ?";
        String isin = null;

        if (account.getRuolo() == 2) {
            isin = this.doRetrieveIsinByUsername(account.getUsername());
        }

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(deleteSQL);

            preparedStatement.setString(1, account.getUsername());

            System.out.println("doDelete: " + preparedStatement.toString());
            result = preparedStatement.executeUpdate();
            connection.commit();                                                    //Perche' auto-commit e' false in DriverManagerConnectionPool

            if (account.getRuolo() == 2) {
                String deleteSQL2 = "DELETE FROM casa_editrice WHERE isin = ?";
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(deleteSQL2);

                preparedStatement.setString(1, isin);

                System.out.println("doDeleteAzienda: " + preparedStatement.toString());
                result = preparedStatement.executeUpdate();
                connection.commit();
            }
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

    public Collection<AccountBean> doRetrieveAllFriendsByUsername(String username) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<AccountBean> amici = new LinkedList<>();

        String selectSQL = "SELECT DISTINCT * FROM e_nella_friendlist, account WHERE e_nella_friendlist.Possessore = ? AND e_nella_friendlist.Amico=account.Username";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, username);

            System.out.println("doRetrieveAllFriendsByUsername: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                AccountBean bean = new AccountBean();

                bean.setUsername(rs.getString("Username"));
                bean.setPassword(rs.getString("Password"));
                bean.setEmail(rs.getString("Email"));
                bean.setEmailRecupero(rs.getString("Email_Recupero"));
                bean.setCellulare(rs.getString("Cellulare"));
                bean.setData(rs.getString("Data"));
                bean.setOra(rs.getString("Ora"));
                bean.setIp(rs.getString("IP"));
                bean.setRuolo(rs.getInt("Ruolo"));
                bean.setImgProfilo(rs.getBinaryStream("Img_Profilo"));
                amici.add(bean);
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

        return amici;
    }

    public Collection<AccountBean> doRetrieveAllFriendsByVideogioco(AccountBean account, int codiceVideogioco) throws SQLException {

        Connection connection = null;
        PreparedStatement preparedStatement = null;

        Collection<AccountBean> amici = new LinkedList<>();

        String selectSQL = "SELECT giocatore.username FROM giocatore, ha_nella_libreria where giocatore.Username=ha_nella_libreria.Username && ha_nella_libreria.videogioco=? && giocatore.username IN (SELECT e_nella_friendlist.Amico from e_nella_friendlist where e_nella_friendlist.Possessore=?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, codiceVideogioco);
            preparedStatement.setString(2, account.getUsername());

            System.out.println("doRetrieveAllFriendsByVideogioco: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String tmp = rs.getString("Username");
                AccountBean bean = this.doRetrieveByUsername(tmp);
                amici.add(bean);
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

        return amici;
    }

    public void doAggiungiAmicoFriendlist(AccountBean bean, String futuroAmico) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "CALL inserisci_amicizia(?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, bean.getUsername());
            preparedStatement.setString(2, futuroAmico);

            System.out.println("doAggiungiAmicoFriendlist: " + preparedStatement.toString());

            preparedStatement.executeUpdate();
            connection.commit();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    public void doRimuoviAmicoFriendlist(AccountBean bean, String futuroAmico) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "CALL cancella_amicizia(?, ?)";

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setString(1, bean.getUsername());
            preparedStatement.setString(2, futuroAmico);

            System.out.println("doRimuoviAmicoFriendlist: " + preparedStatement.toString());

            preparedStatement.executeUpdate();
            connection.commit();

        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
    }

    public AccountBean doRetrieveDetailed(AccountBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        AccountBean theUser = null;

        String username = account.getUsername();
        int ruolo = account.getRuolo();

        String selectSQL = "";

        if (ruolo == 0)
            selectSQL += "SELECT DISTINCT * FROM account, amministratore WHERE Ruolo = " + ruolo + " AND account.Username = amministratore.Username AND account.Username = '" + username + "'";
        else if (ruolo == 1)
            selectSQL += "SELECT DISTINCT * FROM account, giocatore WHERE Ruolo = " + ruolo + " AND account.Username = giocatore.Username AND account.Username = '" + username + "'";
        else if (ruolo == 2)
            selectSQL += "SELECT DISTINCT * FROM account, azienda, casa_editrice WHERE Ruolo = " + ruolo + " AND account.Username = azienda.Username AND casa_editrice.ISIN=azienda.ISIN AND account.Username='" + username + "'";


        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);

            System.out.println("doRetrieveDetailed: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {

                AccountBean bean = new AccountBean();

                bean.setUsername(rs.getString("Username"));
                bean.setPassword(rs.getString("Password"));
                bean.setEmail(rs.getString("Email"));
                bean.setEmailRecupero(rs.getString("Email_Recupero"));
                bean.setCellulare(rs.getString("Cellulare"));
                bean.setData(rs.getString("Data"));
                bean.setOra(rs.getString("Ora"));
                bean.setIp(rs.getString("IP"));
                bean.setRuolo(rs.getInt("Ruolo"));
                bean.setImgProfilo(rs.getBinaryStream("Img_Profilo"));

                if (ruolo == 0) {
                    AmministratoreBean tmp = new AmministratoreBean(bean);
                    tmp.setNome(rs.getString("Nome"));
                    tmp.setCognome(rs.getString("Cognome"));
                    theUser = tmp;
                } else if (ruolo == 1) {
                    GiocatoreBean giocatoreBean = new GiocatoreBean(bean);
                    giocatoreBean.setDataIscrizione(rs.getString("Data_Iscrizione"));
                    giocatoreBean.setDataNascita(rs.getString("Data_Nascita"));
                    giocatoreBean.setGenere(rs.getString("Genere"));
                    theUser = giocatoreBean;
                } else if (ruolo == 2) {
                    CasaEditriceBean casaEditriceBean = new CasaEditriceBean(bean);
                    casaEditriceBean.setISIN(rs.getString("ISIN"));
                    casaEditriceBean.setNomeCasaEditrice(rs.getString("Nome_Casa_Editrice"));
                    casaEditriceBean.setCEO(rs.getString("CEO"));
                    casaEditriceBean.setSitoWeb(rs.getString("Sito_Web"));
                    theUser = casaEditriceBean;
                }
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }

        return theUser;
    }

    public void doUploadImage(AccountBean account) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        if (account.getImgProfilo() != null) {
            String insertSQL = "UPDATE account SET Img_Profilo = ? WHERE Username = ?";
            try {
                connection = DriverManagerConnectionPool.getConnection();
                preparedStatement = connection.prepareStatement(insertSQL);


                // fetches input stream of the upload file for the blob column
                preparedStatement.setBlob(1, account.getImgProfilo());
                preparedStatement.setString(2, account.getUsername());


                System.out.println("doUploadImage: " + preparedStatement.toString());
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

    public byte[] doDisplayImage(String username) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        byte[] image = null;

        String insertSQL = "SELECT Img_Profilo FROM Account WHERE Username = ?";
        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setString(1, username);

            System.out.println("doDisplayImage: " + preparedStatement.toString());
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                image = rs.getBytes("Img_Profilo");
            }
        } finally {
            try {
                if (preparedStatement != null)
                    preparedStatement.close();
            } finally {                                                                //Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
                DriverManagerConnectionPool.releaseConnection(connection);
            }
        }
        return image;
    }

    public int doRetrieveNumeroGiocatori(int min, int max) throws SQLException {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String selectSQL = "SELECT count(*) as Numero_Giocatori FROM giocatore WHERE TIMESTAMPDIFF(year, Data_Nascita, current_date())>=? && TIMESTAMPDIFF(year, Data_Nascita, current_date())<=?";
        int n = 0;

        try {
            connection = DriverManagerConnectionPool.getConnection();
            preparedStatement = connection.prepareStatement(selectSQL);
            preparedStatement.setInt(1, min);
            preparedStatement.setInt(2, max);

            System.out.println("doRetrieveNumeroGiocatori: " + preparedStatement.toString());

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                n = rs.getInt("Numero_Giocatori");
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

        return n;
    }
}
