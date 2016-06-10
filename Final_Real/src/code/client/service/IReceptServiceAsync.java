package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.ReceptDTO;

public interface IReceptServiceAsync {
	void getRecept(AsyncCallback<ArrayList<ReceptDTO>> callback);
	void addRecept(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance, AsyncCallback<Void> asyncCallback);
	void redigerRecept(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance, int glid, AsyncCallback<Void> asyncCallback);

}
