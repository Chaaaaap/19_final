package code.server;

import java.util.ArrayList;

import code.shared.RaavareDTO;

public interface IRaavareDAO {

	void addRaavare();
	ArrayList<RaavareDTO> getRaavarer();
	
}
