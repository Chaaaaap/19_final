package code.server;

import java.util.ArrayList;

import code.shared.ReceptDTO;

public interface IReceptDAO {

	void addRecept(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance) throws Exception;
	ArrayList<ReceptDTO> getRecept() throws Exception;
	void redigerRecept(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance, int glid) throws Exception;
}
