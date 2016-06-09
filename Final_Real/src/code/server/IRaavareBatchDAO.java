package code.server;

import java.util.ArrayList;

import code.shared.RaavareBatchDTO;

public interface IRaavareBatchDAO {

	void addRaavareBatch(int rb_id, int raavare_id, int mængde) throws Exception;
	ArrayList<RaavareBatchDTO> getRaavareBatches() throws Exception;
	void redigerRaavareBatch(int rb_id, int raavare_id, int mængde, int glid) throws Exception;
}
