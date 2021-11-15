package nytro.model.idao;

import nytro.model.bean.AccountBean;
import nytro.model.bean.AmministratoreBean;
import nytro.model.bean.CasaEditriceBean;
import nytro.model.bean.GiocatoreBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public interface IAccountDAO {
    Collection<AccountBean> doRetrieveAll(String order, int ruolo) throws SQLException;

    GiocatoreBean doRetrieveGiocatore(AccountBean bean) throws SQLException;

    CasaEditriceBean doRetrieveCasaEditrice(AccountBean bean) throws SQLException;

    AccountBean doRetrieveByUsernamePassword(String username, String password) throws SQLException;

    ArrayList<String> doRetrieveContributoAdmin(String dataInizio, String dataFine) throws SQLException;

    ArrayList<String> doRetrieveContributoCasaEditrice(String isin, String dataInizio, String dataFine) throws SQLException;

    String doRetrieveIsinByUsername(String username) throws SQLException;

    AccountBean doRetrieveByUsername(String username) throws SQLException;

    AccountBean doRetrieveByIsin(String isin) throws SQLException;

    void doSaveAccount(AccountBean account) throws SQLException;

    void doSaveGiocatore(GiocatoreBean account) throws SQLException;

    void doSaveCasaEditrice(CasaEditriceBean account) throws SQLException;

    void doUpdate(AccountBean account) throws SQLException;

    void doUpdateGiocatore(GiocatoreBean account) throws SQLException;

    void doUpdateAmministratore(AmministratoreBean account) throws SQLException;

    void doUpdateCasaEditrice(CasaEditriceBean account) throws SQLException;

    boolean doDelete(AccountBean account) throws SQLException;

    Collection<AccountBean> doRetrieveAllFriendsByUsername(String username) throws SQLException;

    Collection<AccountBean> doRetrieveAllFriendsByVideogioco(AccountBean account, int codiceVideogioco) throws SQLException;

    void doAggiungiAmicoFriendlist(AccountBean bean, String futuroAmico) throws SQLException;

    void doRimuoviAmicoFriendlist(AccountBean bean, String futuroAmico) throws SQLException;

    AccountBean doRetrieveDetailed(AccountBean account) throws SQLException;

    void doUploadImage(AccountBean account) throws SQLException;

    byte[] doDisplayImage(String username) throws SQLException;

    int doRetrieveNumeroGiocatori(int min, int max) throws SQLException;
}
