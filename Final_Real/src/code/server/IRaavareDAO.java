package code.server;

import java.sql.SQLException;
import java.util.ArrayList;

import code.shared.RaavareDTO;

public interface IRaavareDAO {

	void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws SQLException;
	ArrayList<RaavareDTO> getRaavarer() throws SQLException;
	
}
