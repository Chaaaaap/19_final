package code.server;

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
	public ArrayList<RaavareBatchDTO> getRaavareBatches() {
		// TODO SKAL LAVES
		return null;
	}

	@Override
	public void redigerRaavareBatch(int rb_id, int raavare_id, int mængde, int glid) {
		// TODO SKAL LAVES
		
	}

}
