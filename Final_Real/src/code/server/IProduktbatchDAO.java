package code.server;

import java.util.ArrayList;

import code.shared.DALException;
import code.shared.ProduktBatchDTO;

public interface IProduktbatchDAO {

	ArrayList<ProduktBatchDTO> getProduktBatches() throws DALException;
	ProduktBatchDTO getProduktBatch(int pbID) throws DALException;
	void updateStatus(int pb_id, int status) throws DALException;
	void addProduktBatch(int pb_id, int recept_id, String dato) throws DALException;
	
}
