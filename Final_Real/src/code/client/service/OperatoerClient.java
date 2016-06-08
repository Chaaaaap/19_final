package code.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import code.shared.OperatoerDTO;

public class OperatoerClient {

	private IOperatoerServiceAsync service;
	private IClientCallback callback;
	
	public interface IClientCallback{ public void onLogin(OperatoerDTO opr); }
	
	public OperatoerClient(String URL, IClientCallback callback) {
		this.callback = callback;
		service = GWT.create(IOperatoerService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(URL);
	}

	
	public void login(int oprID, String password) {
		service.login(oprID, password, new AsyncCallback<OperatoerDTO>() {

			@Override
			public void onFailure(Throwable caught) {
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
				Window.alert("Dit password er nu ændret.");
			}
			
		});
		
	}


	public void opretBruger(int oprID, String navn, String ini, String cpr, 
			String password, String type) {
		service.opretBruger(oprID, navn, ini, cpr, password, type, new AsyncCallback<OperatoerDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(OperatoerDTO result) {
				Window.alert("Brugeren er nu oprettet");
			}
			
		});
	}
}
