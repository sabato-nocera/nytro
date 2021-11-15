package nytro.model.dao;

import nytro.model.DriverManagerConnectionPool;
import nytro.model.bean.AccountBean;
import nytro.model.bean.VideogiocoBean;
import nytro.model.idao.IVideogiocoDAO;
import nytro.other.Utils;
import org.bson.Document;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class VideogiocoDAO implements IVideogiocoDAO {

	@Override
	public Collection<VideogiocoBean> doRetrieveAll(String order, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco ";
		
		if(isin!=null && !isin.equals("")){
			selectSQL += " WHERE ISIN = ?";
		}
		
		//Nel caso avessi voluto imporre un ordine per l'estrazione degli utenti
		if(order!=null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			if(isin!=null && !isin.equals("")) {
				preparedStatement.setString(1, isin);
			}

			System.out.println("doRetrieveAll: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = new VideogiocoBean();

				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
				bean.setPrezzo(Float.parseFloat(rs.getString("Prezzo")));
				bean.setCopieVendute(Integer.parseInt(rs.getString("Copie_Vendute")));
				bean.setGeneri(IVideogiocoDAO.doGetGenere(bean));
				bean.setConsole(IVideogiocoDAO.doGetConsole(bean));

				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}

	@Override
	public Collection<VideogiocoBean> doRetrieveAllLibreria(String username, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT DISTINCT * FROM videogioco, ha_nella_libreria WHERE Username = ? AND Codice=Videogioco";
		
		if(order!=null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}
		
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);			
			
			System.out.println("doRetrieveAllLibreria: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				/*	N.B. La query produce una tabella con piu' colonne di quelle che effettivamente andiamo ad utilizzare, siamo interessati
				 * 			solo a quelle che riguardano i videogiochi!*/
				VideogiocoBean bean = new VideogiocoBean();
				
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
				bean.setPrezzo(Float.parseFloat(rs.getString("Prezzo")));
				bean.setCopieVendute(Integer.parseInt(rs.getString("Copie_Vendute")));
				bean.setGeneri(IVideogiocoDAO.doGetGenere(bean));
				bean.setConsole(IVideogiocoDAO.doGetConsole(bean));
				
				videogiochi.add(bean);				
			}
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}

	@Override
	public VideogiocoBean doRetrieveByCodice(int codice, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = new VideogiocoBean();
		
		String selectSQL = "SELECT * FROM videogioco WHERE Codice = ? ";
		
		if(isin!=null && !isin.equals(""))
			selectSQL +=" AND ISIN='"+isin+"'";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			
			System.out.println("doRetrieveByCodice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setDataRimozione(rs.getString("Data_Rimozione"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setTrailer(rs.getString("Trailer"));
				bean.setPrezzo(Float.parseFloat(rs.getString("Prezzo")));
				bean.setCopieVendute(Integer.parseInt(rs.getString("Copie_Vendute")));
				bean.setGeneri(IVideogiocoDAO.doGetGenere(bean));
				bean.setConsole(IVideogiocoDAO.doGetConsole(bean));
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}

	@Override
	public void doSaveVideogiocoPagamento(VideogiocoBean bean, String genere, String console) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "CALL inserisci_a_pagamento(?, ?, ?, ?, ?, ?, ?, ?, ?)";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getTitolo());
			preparedStatement.setInt(4, bean.getPEGI());
			preparedStatement.setNull(5, java.sql.Types.BLOB); 
			preparedStatement.setString(6, genere); 
			preparedStatement.setFloat(7, bean.getPrezzo());
			preparedStatement.setString(8, console);
			preparedStatement.setString(9, bean.getTrailer());

			System.out.println("doSaveVideogiocoPagamento: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doUpdate(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String updateSQL = "UPDATE videogioco SET  ISIN = ?, Data_Rilascio = ?, Data_Rimozione = ?, Titolo = ?, Voto_Medio = ?, PEGI = ?, Img = ?, Trailer = ?, Prezzo = ? WHERE Codice = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
						
			preparedStatement.setString(1, bean.getISIN());
			preparedStatement.setString(2, bean.getDataRilascio());
			preparedStatement.setString(3, bean.getDataRimozione());
			preparedStatement.setString(4, bean.getTitolo());
			preparedStatement.setFloat(5, bean.getVotoMedio());
			preparedStatement.setInt(6, bean.getPEGI());
			preparedStatement.setBlob(7, bean.getImg());
			preparedStatement.setString(8, bean.getTrailer());
			preparedStatement.setFloat(9, bean.getPrezzo());
			preparedStatement.setInt(10, bean.getCodice());

			System.out.println("doUpdate: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}

	@Override
	public void doDeleteVideogioco(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "UPDATE videogioco SET Data_Rimozione = current_date() WHERE codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			preparedStatement.setInt(1, bean.getCodice());
			
			System.out.println("doDeleteVideogioco: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public boolean doDeleteFromLibreria(String username, int codiceVideogiocoDaCancellare) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result;
		
		String deleteSQL="DELETE FROM ha_nella_libreria WHERE Username = ? AND Videogioco = ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, codiceVideogiocoDaCancellare);
			
			System.out.println("doDeleteFromLibreria: " + preparedStatement.toString());
			
			result=preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result != 0);
	}
	
	@Override
	public boolean doSaveToLibreria(String username, int codiceVideogiocoDaInserire) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int result;
		
		String selectSQL = "CALL inserisci_libreria(?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, codiceVideogiocoDaInserire);
			
			System.out.println("doSaveToLibreria: " + preparedStatement.toString());
			result=preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return (result != 0);
	}

	@Override
	public boolean doRetrieveAppartenenzaAllaLibreria(int codice, String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int n=0;
		
		String selectSQL = "SELECT COUNT(*) FROM ha_nella_libreria WHERE Videogioco = ? AND Username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setString(2, username);
			
			System.out.println("doRetrieveAppartenenzaAllaLibreria: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
				n=rs.getInt(1);
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println(n);
		return true;
	}

	@Override
	public boolean doRetrieveAppartenenzaAgliAcquisti(int codice, String username) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		int n=0;
		
		String selectSQL = "SELECT COUNT(*) FROM acquista WHERE Videogioco = ? AND Username=?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			preparedStatement.setString(2, username);
			
			System.out.println("doRetrieveAppartenenzaAgliAcquisti: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) 
				n=rs.getInt(1);
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println(n);
		return true;
	}
	
	@Override
	public void doAcquisto(List<VideogiocoBean> carrello, AccountBean account, String cartaDiPagamento) throws SQLException {
		
		for(VideogiocoBean x : carrello) {
			Connection connection = null;
			PreparedStatement preparedStatement = null;
	
			String selectSQL = "CALL acquista_videogioco(?, ?, ?, ?, ?, ?, ?)";
			
			try {
				connection = DriverManagerConnectionPool.getConnection();
				preparedStatement = connection.prepareStatement(selectSQL);
				
				preparedStatement.setString(1, account.getUsername());
				preparedStatement.setInt(2, x.getCodice());
				preparedStatement.setString(3, cartaDiPagamento);
				preparedStatement.setFloat(4, x.getPrezzo());
				preparedStatement.setString(5, account.getIp());
				// Sara' acquistata il videogioco per la prima console presente
				preparedStatement.setString(6, x.getConsole().get(0));
				preparedStatement.setString(7, Utils.getAlphaNumericString(16));
				
				System.out.println("doAcquisto: " + preparedStatement.toString());
				
				preparedStatement.executeQuery();

			} finally {
				try {
					if(preparedStatement!=null)
						preparedStatement.close();
				} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
					DriverManagerConnectionPool.releaseConnection(connection);
				}
			}
			
		}
	}

	@Override
	public void doUploadImage(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String insertSQL = "UPDATE videogioco SET Img = ? WHERE ISIN = ? AND data_rilascio = ? AND titolo = ? AND PEGI = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			 if (bean.getImg() != null) {
	                // fetches input stream of the upload file for the blob column
	                preparedStatement.setBlob(1, bean.getImg());
	                preparedStatement.setString(2, bean.getISIN());
	                preparedStatement.setString(3, bean.getDataRilascio());
	                preparedStatement.setString(4, bean.getTitolo());
	                preparedStatement.setInt(5, bean.getPEGI());
	            }		
			
			System.out.println("doUploadImage: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public void doUploadImageByCodice(VideogiocoBean bean) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String insertSQL = "UPDATE videogioco SET Img = ? WHERE Codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			
			 if (bean.getImg() != null) {
	                // fetches input stream of the upload file for the blob column
	                preparedStatement.setBlob(1, bean.getImg());
	                preparedStatement.setInt(2, bean.getCodice());
	            }		
			
			System.out.println("doUploadImageByCodice: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}

	@Override
	public byte[] doDisplayImage(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		byte[] image = null;
				
		String insertSQL = "SELECT Img FROM Videogioco WHERE Codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
            preparedStatement.setInt(1, codice);
			
			System.out.println("doDisplayImage: " + preparedStatement.toString());
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				image = rs.getBytes("Img");
			}							
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		return image;	
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuAcquistati(int numeroVideogiochi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT * FROM videogioco ORDER BY videogioco.copie_vendute DESC LIMIT ?";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, numeroVideogiochi);
			
			System.out.println("doRetrievePiuAcquistati: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("codice"), null);
				if(bean.getDataRimozione() == null)
					videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuVotati(int numeroVideogiochi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT * FROM piattaforma_videogiochi_tsw.videogioco ORDER BY Voto_Medio DESC LIMIT ?";
			
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, numeroVideogiochi);
			
			System.out.println("doRetrievePiuVotati: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("codice"), null);
				if(bean.getDataRimozione() == null)
					videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocati(int numeroVideogiochi) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT videogioco, SUM(ha_nella_libreria.Ore_Di_Gioco) AS Ore_Di_Gioco from ha_nella_libreria GROUP BY ha_nella_libreria.Videogioco ORDER BY Ore_Di_Gioco DESC LIMIT ?";
			
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setInt(1, numeroVideogiochi);
			
			System.out.println("doRetrievePiuGiocati: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("videogioco"), null);
				
				if(bean.getDataRimozione() == null)
					videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public List<VideogiocoBean> doRetrieveByTitolo(String against) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		List<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT * FROM videogioco";	
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrieveByTitolo: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("Titolo").toLowerCase().contains(against.toLowerCase())) {
					VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"),"");
					videogiochi.add(bean);	
				}			
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		System.out.println("\tTerminato: doRetrieveByTitolo");
		return videogiochi;
	
	}

	@Override
	public void doInsertGenere(String genere, VideogiocoBean tmp) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
				
		String insertSQL = "INSERT INTO genere VALUES (?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
						
			preparedStatement.setString(1, genere);
			preparedStatement.setInt(2, tmp.getCodice());
			
			System.out.println("doInsertGenere: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool
			
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
			
	}
	
	@Override
	public VideogiocoBean doRetrievePiuGiocatoFemmine() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "call piattaforma_videogiochi_tsw.videogioco_fem()";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrievePiuGiocatoFemmine: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}
	
	@Override
	public VideogiocoBean doRetrievePiuGiocatoMaschi() throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "call piattaforma_videogiochi_tsw.videogioco_mas()";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			System.out.println("doRetrievePiuGiocatoMaschi: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}

	@Override
	public String doRetrieveCasaByCodice(int codice) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String nome = null;
		
		String selectSQL = "SELECT Nome_Casa_Editrice FROM azienda, videogioco WHERE codice = ? AND videogioco.ISIN = azienda.ISIN ";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, codice);
			
			System.out.println("doRetrieveCasaByCodice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				nome = rs.getString("Nome_Casa_Editrice");
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return nome;
	}

	@Override
	public ArrayList<VideogiocoBean> doRetrievePerAnnoDiRimozione(String annoRimozione) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		ArrayList<VideogiocoBean> videogiochiRimossiInAnno = new ArrayList<>();
		
		String selectSQL = "SELECT * FROM videogioco WHERE Data_Rimozione LIKE ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, annoRimozione);
			
			System.out.println("doRetrievePerAnnoDiRimozione: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochiRimossiInAnno.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochiRimossiInAnno;
	}
	
	@Override
	public VideogiocoBean doRetrieveVideogiocoPiuGiocatoDa(int min, int max) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		VideogiocoBean bean = null;
		
		String selectSQL = "call piattaforma_videogiochi_tsw.videogioco_eta(?, ?)";
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, min);
			preparedStatement.setInt(2, max);
			
			System.out.println("doRetrieveVideogiocoPiuGiocatoDa: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				bean = this.doRetrieveByCodice(rs.getInt("codice"), "");
			}
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return bean;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocatiFemmine(int limit) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'f' GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, limit);
			
			
			System.out.println("doRetrievePiuGiocatiFemmineCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocatiMaschi(int limit) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'm' GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, limit);
			
			System.out.println("doRetrievePiuGiocatiMaschi: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocatiGenderless(int limit) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere is null GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, limit);
			
			System.out.println("doRetrievePiuGiocatiGenderless: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocatiFemmineCasaEditrice(int limit, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'f' and videogioco.ISIN= ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, limit);
			
			
			System.out.println("doRetrievePiuGiocatiFemmineCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocatiMaschiCasaEditrice(int limit, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere = 'm' and videogioco.ISIN = ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, limit);
			
			System.out.println("doRetrievePiuGiocatiMaschiCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuGiocatiGenderlessCasaEditrice(int limit, String isin) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		
		String selectSQL = "SELECT Codice, COUNT(*) as Numero_Giocatrici, SUM(Ore_Di_Gioco) as Ore_Totali FROM videogioco JOIN ha_nella_libreria ON videogioco.codice = ha_nella_libreria.videogioco JOIN giocatore ON ha_nella_libreria.username = giocatore.username WHERE giocatore.genere is null and videogioco.ISIN = ? GROUP BY codice ORDER BY SUM(ore_di_gioco) DESC LIMIT ?";
				
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, limit);
			
			System.out.println("doRetrievePiuGiocatiGenderlessCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("Codice"), "");
				videogiochi.add(bean);
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}
	
	@Override
	public Collection<VideogiocoBean> doRetrievePiuVotatiCasaEditrice(int numeroVideogiochi, String isin) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;
		
		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		
		String selectSQL = "SELECT * FROM piattaforma_videogiochi_tsw.videogioco where isin=? ORDER BY Voto_Medio DESC LIMIT ?";
			
		
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setString(1, isin);
			preparedStatement.setInt(2, numeroVideogiochi);
			
			System.out.println("doRetrievePiuVotatiCasaEditrice: " + preparedStatement.toString());
			
			ResultSet rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				VideogiocoBean bean = this.doRetrieveByCodice(rs.getInt("codice"), null);
				if(bean.getDataRimozione() == null)
					videogiochi.add(bean);				
				
			}
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
		
		return videogiochi;
	}

	@Override
	public Collection<Map> doRetrieveAllAcqusiti(String username, String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement=null;

		Collection<VideogiocoBean> videogiochi = new LinkedList<>();
		Collection<Map> acquisti = new LinkedList<>();

		String selectSQL = "SELECT DISTINCT * FROM videogioco, ACQUISTA WHERE Username = ? AND Codice=Videogioco";

		if(order!=null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}


		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, username);

			System.out.println("doRetrieveAllAcqusiti: " + preparedStatement.toString());

			ResultSet rs = preparedStatement.executeQuery();

			while(rs.next()) {
				/*	N.B. La query produce una tabella con piu' colonne di quelle che effettivamente andiamo ad utilizzare, siamo interessati
				 * 			solo a quelle che riguardano i videogiochi!*/
				VideogiocoBean bean = new VideogiocoBean();
				Map map = new Document();

				bean.setCodice(rs.getInt("Codice"));
				bean.setISIN(rs.getString("ISIN"));
				bean.setTitolo(rs.getString("Titolo"));
				bean.setVotoMedio(rs.getFloat("Voto_medio"));
				bean.setDataRilascio(rs.getString("Data_Rilascio"));
				bean.setPEGI(rs.getInt("PEGI"));
				bean.setImg(rs.getBinaryStream("Img"));
				bean.setPrezzo(Float.parseFloat(rs.getString("Prezzo_Di_Acquisto")));
				bean.setGeneri(IVideogiocoDAO.doGetGenere(bean));
				String data = String.valueOf(rs.getDate("Data"));
				String ora = String.valueOf(rs.getTime("Ora"));
				String console = rs.getString("Nome_Console");
				String codiceDownload = rs.getString("Codice_Download");


				videogiochi.add(bean);

				map.put("videogioco", bean);
				map.put("data", data);
				map.put("ora", ora);
				map.put("console", console);
				map.put("codiceDownload", codiceDownload);

				acquisti.add(map);
			}
			connection.commit();
		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}

		return acquisti;
	}

	@Override
	public void doSetDisponibleVideogioco(int codice) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		String insertSQL = "UPDATE Videogioco SET Data_Rimozione = NULL WHERE codice = ?";
		try {
			connection = DriverManagerConnectionPool.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);

			preparedStatement.setInt(1, codice);

			System.out.println("doSetDisponibleVideogioco: " + preparedStatement.toString());
			preparedStatement.executeUpdate();
			connection.commit();													//Perche' auto-commit e' false in DriverManagerConnectionPool

		} finally {
			try {
				if(preparedStatement!=null)
					preparedStatement.close();
			} finally {																//Mi serve un ulteriore livello di try{} finally{ } in quanto se preparedStament.close() genera un'execption, non chiudo la connessione
				DriverManagerConnectionPool.releaseConnection(connection);
			}
		}
	}
	
}
