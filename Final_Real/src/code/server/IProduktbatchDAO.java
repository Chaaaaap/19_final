package code.server;

import java.sql.SQLException;
import java.util.ArrayList;

import code.shared.ProduktBatchDTO;

public interface IProduktbatchDAO {

	void startProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id) throws SQLException;
	ArrayList<ProduktBatchDTO> getProduktBatches() throws SQLException;
	void redigerProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id, int oldPb_id) throws Exception;
}
