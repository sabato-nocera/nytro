package nytro.model.idao;

import nytro.model.bean.RecensioneBean;

import java.sql.SQLException;
import java.util.Collection;

public interface IRecensioneDAO {
    Collection<RecensioneBean> doRetrieveAllByCodice(String order, int codice) throws SQLException;

    Collection<RecensioneBean> doRetrieveAllByRange(int codVideogioco, String order, int min, int max) throws SQLException;

    RecensioneBean doRetrieveByUsername(String username, int codiceVideogioco) throws SQLException;

    boolean doCheck(RecensioneBean recensione) throws SQLException;

    boolean doSave(RecensioneBean recensione) throws SQLException;

    void doUpdate(RecensioneBean recensione) throws SQLException;

    boolean doDelete(RecensioneBean recensione) throws SQLException;

    boolean doDelete(String username, int codVideogioco) throws SQLException;
}
