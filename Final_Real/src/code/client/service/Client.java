package code.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import code.shared.OperatoerDTO;

public class Client {

	private IServiceAsync service;
	private IClientCallback callback;
	
	public interface IClientCallback{ public void onLogin(OperatoerDTO opr); }
	
	public Client(String URL, IClientCallback callback) {
		this.callback = callback;
		service = GWT.create(IService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(URL);
	}

	
	public void login(int oprID, String password) {
		service.login(oprID, password, new AsyncCallback<OperatoerDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				GWT.log("lol");
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(OperatoerDTO result) {
				callback.onLogin(result);
			}
			
		});
	}


	public void skiftPassword(OperatoerDTO opr, String nyPassword) {
		service.skiftPassword(opr, nyPassword, new AsyncCallback<OperatoerDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(OperatoerDTO result) {
				Window.alert("Det burde virke");
			}
			
		});
		
	}
}
