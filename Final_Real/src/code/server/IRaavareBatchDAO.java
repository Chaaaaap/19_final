package code.server;

import java.util.ArrayList;

import code.shared.RaavareBatchDTO;

public interface IRaavareBatchDAO {

	void addRaavareBatch(int raavareBatch_id, int raavare_id, int mængde) throws Exception;
	ArrayList<RaavareBatchDTO> getRaavareBatch() throws Exception;
	void redigerRaavareBatch(int raavareBatch_id, int raavare_id, int mængde, int glid) throws Exception;
}
