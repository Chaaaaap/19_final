package code.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

public interface IServiceVsConAsync {

	void ConnectorVaegt(String ip, AsyncCallback<Void> callback);

	void Simulator(AsyncCallback<Void> callback);
	
}
