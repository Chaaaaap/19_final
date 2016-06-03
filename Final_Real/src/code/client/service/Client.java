package code.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

import code.shared.OperatoerDTO;

public class Client {

	private IServiceAsync service;
	private OperatoerDTO opr = null;
	
	public Client(String URL) {
		service = GWT.create(IService.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(URL);
	}

	
	public OperatoerDTO login(int oprID, String password) {
		
		service.login(oprID, password, new AsyncCallback<OperatoerDTO>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage()+"");
			}

			@Override
			public void onSuccess(OperatoerDTO result) {
				opr = result;
				Window.alert(opr+"");
			}
			
		});
		return opr;
	}
}
