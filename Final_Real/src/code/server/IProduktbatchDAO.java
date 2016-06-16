package code.server;

import java.util.ArrayList;

import code.shared.DALException;
import code.shared.ProduktBatchDTO;

public interface IProduktbatchDAO {

	ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception;
	ProduktBatchDTO getProduktBatch(int pbID) throws Exception;
	void updateStatus(int pb_id, int status) throws Exception;
	void addProduktBatch(int pb_id, int recept_id, String dato) throws DALException;
	
}
