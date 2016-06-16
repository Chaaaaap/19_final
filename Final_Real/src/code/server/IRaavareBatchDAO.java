package code.server;

import java.util.ArrayList;

import code.shared.DALException;
import code.shared.RaavareBatchDTO;

public interface IRaavareBatchDAO 
{
	void addRaavareBatch(int raavareBatch_id, int raavare_id, double mængde) throws DALException;
	ArrayList<RaavareBatchDTO> getRaavareBatch() throws DALException;
	void redigerMaengde(int rb_id, double vaegt);
}
