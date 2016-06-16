package code.server;

import java.util.ArrayList;

import code.shared.DALException;
import code.shared.RaavareDTO;

public interface IRaavareDAO {

	void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws DALException;
	ArrayList<RaavareDTO> getRaavarer() throws Exception;
	void redigerRaavare(int raavare_id, String raavare_navn, String leverandør, int glid) throws DALException;
	String getRaavare(int pb_id) throws Exception;
	
}
