package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.server.RaavareDAO;
import code.shared.RaavareDTO;

public class VisRaavarer extends Composite {

	private static VisRaavarerUiBinder uiBinder = GWT.create(VisRaavarerUiBinder.class);

	interface VisRaavarerUiBinder extends UiBinder<Widget, VisRaavarer> {
	}
	private IRaavareServiceAsync service;

	public VisRaavarer() {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IRaavareService.class);
		service.getRaavarer(new AsyncCallback<ArrayList<RaavareDTO>>() {

			
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				
			}

			@Override
			public void onSuccess(ArrayList<RaavareDTO> result) {
				Window.alert(""+result.size());
				
			}
			
		});
	}
}