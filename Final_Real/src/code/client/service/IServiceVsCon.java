package code.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.DALException;

@RemoteServiceRelativePath("19_Final_afvejning")
public interface IServiceVsCon extends RemoteService {
	
	void ConnectorVaegt() throws DALException;

	
}
