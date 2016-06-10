package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.RaavareDTO;
import code.shared.ReceptDTO;

@RemoteServiceRelativePath("19_Final_Recept")
public interface IReceptService extends RemoteService {

	ArrayList<ReceptDTO> getRecept() throws Exception;
	void addRecept(String receptNavn, int recept_id, int[] raavare_id, int[] nom_netto, int[] tolerance) throws Exception;
	void redigerRecept(String receptNavn, int recept_id, int[] raavare_id, int[] nom_netto, int[] tolerance, int glid) throws Exception;
}
