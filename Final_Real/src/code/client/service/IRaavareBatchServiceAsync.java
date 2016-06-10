package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.RaavareBatchDTO;

public interface IRaavareBatchServiceAsync {
	void getRaavarerBatch(AsyncCallback<ArrayList<RaavareBatchDTO>> callback);
	void addRaavareBatch(int raavareBatch_id, int raavare_id, int mængde, AsyncCallback<Void> asyncCallback);
	void redigerRaavareBatch(int raavareBatch_id, int raavare_id, int mængde, int glid, AsyncCallback<Void> asyncCallback);

}
