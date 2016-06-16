package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.DALException;
import code.shared.RaavareDTO;

@RemoteServiceRelativePath("19_Final_Raavare")
public interface IRaavareService extends RemoteService {

	ArrayList<RaavareDTO> getRaavarer() throws Exception;
	void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws DALException;
	void redigerRaavare(int raavare_id, String raavare_navn, String leverandør, int glid) throws DALException;
}
