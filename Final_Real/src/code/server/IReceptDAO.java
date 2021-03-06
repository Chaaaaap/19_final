package code.server;

import java.util.ArrayList;

import code.shared.DALException;
import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;

public interface IReceptDAO {

	void addRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp) throws DALException;
	ArrayList<ReceptDTO> getRecepter() throws DALException;
	ReceptDTO getRecept(int recept_id) throws DALException;
	int countPBK(int recept_id) throws DALException;
}
