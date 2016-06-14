package code.server;

import java.sql.SQLException;
import java.util.ArrayList;

import code.shared.DALException;
import code.shared.ProduktBatchDTO;

public interface IProduktbatchDAO {

	void startProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id) throws SQLException;
	ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception;
	void redigerProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id, int oldPb_id) throws Exception;
	ProduktBatchDTO getProduktBatch(int pbID) throws Exception;
	void updateStatus(int pb_id, int status) throws Exception;
	void addProduktBatch(int pb_id, int recept_id, int status, String dato) throws DALException;
	
}
