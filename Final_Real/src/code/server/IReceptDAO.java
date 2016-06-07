package code.server;

import java.util.ArrayList;

import code.shared.ReceptDTO;

public interface IReceptDAO {

	void addRecept();
	ArrayList<ReceptDTO> getRecepter();
}
