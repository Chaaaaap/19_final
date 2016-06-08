package code.server;

import java.util.ArrayList;

import code.shared.RaavareDTO;

public interface IRaavareDAO {

	void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws Exception;
	ArrayList<RaavareDTO> getRaavarer() throws Exception;
	void redigerRaavare(int raavare_id, String raavare_navn, String leverandør) throws Exception;
	
}
