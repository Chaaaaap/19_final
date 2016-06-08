package code.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.ProduktBatchDTO;

public class ProduktBatchDAO implements IProduktbatchDAO {
	
	Connector connector = new Connector();
	ArrayList<ProduktBatchDTO> produktList;
	Connection con;

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
			con.setAutoCommit(true);
		}
		
	}

	@Override
	public ArrayList<ProduktBatchDTO> getProduktBatches() throws SQLException {
		
		try {
			ResultSet rs = connector.doQuery("SELECT * FROM produktbatch NATURAL JOIN produktbatchkomponent");
			if(!rs.first()) throw new SQLException("Der findes ingen produktbatches.");
			do {
				produktList.add(new ProduktBatchDTO(rs.getInt("pb_id"), rs.getString("status"),
						rs.getInt("recept_id"), rs.getInt("tara"), rs.getInt("netto"), 
						rs.getInt("oprID"), rs.getInt("rb_id")));
			} while(rs.next());
		} catch(SQLException e) {
			throw e;
		}
		return null;
	}

}
