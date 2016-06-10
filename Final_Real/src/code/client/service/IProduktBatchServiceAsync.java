package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.ProduktBatchDTO;

public interface IProduktBatchServiceAsync {

	void getProduktBatches(AsyncCallback<ArrayList<ProduktBatchDTO>> callback);

}
