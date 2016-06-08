package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.RaavareDTO;

public interface IRaavareServiceAsync {

	void getRaavarer(AsyncCallback<ArrayList<RaavareDTO>> callback);
	void addRaavare(int raavare_id, String raavare_navn, String leverandør, AsyncCallback<Void> callback);
}
