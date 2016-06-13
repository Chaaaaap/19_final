package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;

public interface IReceptServiceAsync {
	void getRecept(AsyncCallback<ArrayList<ReceptDTO>> callback);
	void addRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp, AsyncCallback<Void> asyncCallback);
	void redigerRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp, int glid, AsyncCallback<Void> asyncCallback);

}
