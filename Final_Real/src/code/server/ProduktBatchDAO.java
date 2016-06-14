package code.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.DALException;
import code.shared.ProduktBatchDTO;
import code.shared.ProduktBatchKomponentDTO;

public class ProduktBatchDAO implements IProduktbatchDAO {

	private Connector connector = new Connector();
	//	ArrayList<ProduktBatchDTO> produktList;
	private Connection con;
	private int x = 0;
	private int l;

	@Override
	public void startProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id) throws SQLException {

		try {

			con = connector.getConnection();
			con.setAutoCommit(false);

			connector.doUpdate("INSERT INTO produktbatch VALUES("+pb_id+", "+1+", "+recept_id+")");
			connector.doUpdate("INSERT INTO produktbatchkomponent VALUES("+pb_id+", "+rb_id
					+", "+tara+", "+netto+", "+oprID+")");
		} catch(SQLException e) {
			con.rollback();
			throw e;
		} finally {
			con.commit();
			con.setAutoCommit(true);
		}
	}

	@Override
	public ArrayList<ProduktBatchDTO> getProduktBatches() throws DALException {
		int i = 0;
		ArrayList<ProduktBatchDTO> pbList = new ArrayList<ProduktBatchDTO>();
		ResultSet resultSet;
		try {
			ResultSet length = connector.doQuery("SELECT COUNT(pb_id) FROM produktbatch;");
			if(!length.first()) throw new DALException("Listen er tom");
			l = length.getInt("COUNT(pb_id)");
			resultSet = connector.doQuery("SELECT * FROM produktbatch NATURAL JOIN produktbatchkomponent");
			if(!resultSet.first()) throw new DALException("Listen er tom");
			while(i < l-1) {
				ArrayList<ProduktBatchKomponentDTO> komp = new ArrayList<ProduktBatchKomponentDTO>();
				do {
					x = resultSet.getInt("pb_id");
					komp.add(new ProduktBatchKomponentDTO(resultSet.getInt("pb_id"), resultSet.getInt("rb_id"),
							resultSet.getInt("tara"), resultSet.getInt("netto"), resultSet.getInt("opr_id")));

					System.out.println("\nx = "+x);
				} while(resultSet.next() && resultSet.getInt("pb_id") == x);
				resultSet.previous();
				pbList.add(new ProduktBatchDTO(resultSet.getInt("pb_id"),resultSet.getInt("status"), resultSet.getInt("recept_id"),komp));
				if(!resultSet.next())
					return pbList;
			}
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		} 
		return pbList;

	}

	//	@Override
	//	public ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception {
	//		ArrayList<ProduktBatchDTO> produktList = new ArrayList<ProduktBatchDTO>();
	//		ResultSet rs;
	//		try {
	//			rs = connector.doQuery("SELECT * FROM produktbatch");
	//			if(!rs.next()) throw new Exception("Listen er tom");
	//			do {
	//				produktList.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"),
	//						rs.getInt("recept_id")  /*, rs.getInt("rb_id"), rs.getInt("tara"), rs.getInt("netto"), rs.getInt("opr_id")*/));
	//			} while(rs.next());
	//		} catch(Exception e) {
	//			throw e;
	//		} 
	//		return produktList;
	//		
	//	}

	@Override
	public void redigerProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id, int oldPb_id) throws Exception {
		Connection con = connector.getConnection();

		try {
			con.setAutoCommit(false);
			connector.doUpdate("UPDATE produktbatch SET pb_id = "+pb_id+", recept_id ="+recept_id+" WHERE pb_id = "+oldPb_id+";");
			connector.doUpdate("UPDATE produktbatchkomponent SET pb_id = "+pb_id+", rb_id = "+pb_id+", tara = "+tara+
					", netto = "+netto+", opr_id = "+oprID+" WHERE pb_id = "+oldPb_id+";");

		} catch(Exception e) {
			con.rollback();
			throw e;
		} finally {
			con.commit();
			con.setAutoCommit(true);
		}
	}

	@Override
	public ProduktBatchDTO getProduktBatch(int pbID) throws Exception{

		try {
			ResultSet rs = connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = '" + pbID+"'");
			if (!rs.first()) throw new Exception("Operatoeren '" + pbID + "' findes ikke");
			return new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"),
					rs.getInt("recept_id"), null);	    }
		catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void updateStatus(int pb_id, int status) throws Exception{
		try {
			connector.doUpdate("UPDATE produktbatch SET status="+status+" WHERE pb_id=" +pb_id);
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public void addProduktBatch(int pb_id, int recept_id, int status, String dato) throws DALException {
		String query = "INSERT INTO produktbatch VALUES("+pb_id+", "+status+", "+recept_id+", '"+dato+"');";
		try {
			connector.doUpdate(query);
		} catch(Exception e) {
			throw new DALException(e.getMessage());
		}
	}

	public int countPBK(int pb_id)throws DALException {
		ResultSet rs;
		String countString;
		int countInt;
		try {
			rs = connector.doQuery("Select count(raavare_id) from produktbatch natural join recept natural join receptkomponent where pb_id ="+pb_id);
			countString = rs.getString("count(raavare_id)");
			countInt = Integer.parseInt(countString);

			return countInt;
		} catch(Exception e) {
			throw new DALException(e.getMessage());
		}

	}


}
