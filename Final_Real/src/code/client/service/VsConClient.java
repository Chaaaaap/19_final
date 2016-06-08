package code.client.service;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

public class VsConClient {
	private IServiceVsConAsync service;
	
	
	public VsConClient(String URL) {
		
		service = GWT.create(IServiceVsCon.class);
		ServiceDefTarget endpoint = (ServiceDefTarget) service;
		endpoint.setServiceEntryPoint(URL);
	}
	
	public void Simulator() {
		service.Simulator(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert("Virker ikke");
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Virker!!");
			}
		});
	}
}
