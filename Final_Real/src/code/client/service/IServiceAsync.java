package code.client.service;

import com.google.gwt.user.client.rpc.AsyncCallback;

import code.shared.OperatoerDTO;

public interface IServiceAsync {

	void login(int oprID, String password, AsyncCallback<OperatoerDTO> callback);
	void skiftPassword(OperatoerDTO opr, String glPassword, String nyPassword, AsyncCallback<OperatoerDTO> callback);
	
}
