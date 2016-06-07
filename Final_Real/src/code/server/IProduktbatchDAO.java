package code.server;

import java.sql.SQLException;
import java.util.ArrayList;

import code.shared.ProduktBatchDTO;

public interface IProduktbatchDAO {

	void startProduktBatch(int oprID, int pb_id, int rb_id, int tara, int netto, int recept_id);
	ArrayList<ProduktBatchDTO> getProduktBatches() throws SQLException;
}
