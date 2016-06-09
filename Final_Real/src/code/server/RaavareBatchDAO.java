package code.server;

import java.sql.ResultSet;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.RaavareBatchDTO;

public class RaavareBatchDAO implements IRaavareBatchDAO {
	
	Connector connector = new Connector();

	@Override
	public void addRaavareBatch(int rb_id, int raavare_id, int mængde) throws Exception {
		try {
			connector.doUpdate("INSERT INTO raavarebatch VALUES("+rb_id+", "+raavare_id+", '"+mængde+"');");
		} catch(Exception e) {
			throw e;
		}
		
	}

	@Override
	public ArrayList<RaavareBatchDTO> getRaavareBatches() throws Exception {
		ArrayList<RaavareBatchDTO> rbList = new ArrayList<RaavareBatchDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavarebatch;");
			if(!rs.first()) throw new Exception("Listen er tom");
			do {
				rbList.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getInt("maengde")));
			} while(rs.next());
		} catch(Exception e) {
			throw e;
		}
		return null;
	}

	@Override
	public void redigerRaavareBatch(int rb_id, int raavare_id, int mængde, int glid) throws Exception {
		try {
			connector.doUpdate("UPDATE raavarebatch SET rb_id = "+rb_id+", raavare_id = "+raavare_id+
					", maengde = "+mængde+" WHERE rb_id = "+glid+";");
		} catch(Exception e) {
			throw e;
		}
	}

}
