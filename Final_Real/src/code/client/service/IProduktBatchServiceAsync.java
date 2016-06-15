package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.ProduktBatchDTO;

public interface IProduktBatchServiceAsync {

	void getProduktBatches(AsyncCallback<ArrayList<ProduktBatchDTO>> callback);

	void addProduktBatch(int pb_id, int recept_id, String dato, AsyncCallback<Void> asyncCallback);
	
}

