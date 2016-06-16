package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.DALException;
import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;

@RemoteServiceRelativePath("19_Final_Recept")
public interface IReceptService extends RemoteService {

	ArrayList<ReceptDTO> getRecept() throws Exception;
	void addRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp) throws DALException;
}
