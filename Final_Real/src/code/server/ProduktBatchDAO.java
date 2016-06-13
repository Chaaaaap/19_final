package code.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.ProduktBatchDTO;

public class ProduktBatchDAO implements IProduktbatchDAO {
	
	private Connector connector = new Connector();
//	ArrayList<ProduktBatchDTO> produktList;
	private Connection con;

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
	public ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception {
		ArrayList<ProduktBatchDTO> produktList = new ArrayList<ProduktBatchDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM produktbatch NATURAL JOIN produktbatchkomponent");
			if(!rs.next()) throw new Exception("Listen er tom");
			do {
				produktList.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"),
						rs.getInt("recept_id"), rs.getInt("rb_id"), rs.getInt("tara"), rs.getInt("netto"), rs.getInt("opr_id")));
			} while(rs.next());
		} catch(Exception e) {
			throw e;
		} 
		return produktList;
		
	}

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

}
