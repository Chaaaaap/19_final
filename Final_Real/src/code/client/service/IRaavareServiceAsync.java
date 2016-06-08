package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.server.RaavareDAO;
import code.shared.RaavareDTO;

public interface IRaavareServiceAsync {

	void getRaavarer(AsyncCallback<ArrayList<RaavareDTO>> callback);
}
