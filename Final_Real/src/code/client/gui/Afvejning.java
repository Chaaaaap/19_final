package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.IServiceVsCon;
import code.client.service.IServiceVsConAsync;
import code.shared.OperatoerDTO;

public class Afvejning extends Composite {

	private static AfvejningUiBinder uiBinder = GWT.create(AfvejningUiBinder.class);

	private IServiceVsConAsync service;

	interface AfvejningUiBinder extends UiBinder<Widget, Afvejning> {
	}

	public Afvejning(OperatoerDTO oprDTO) {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IServiceVsCon.class);
	}

	@UiField Label label;
	@UiField Button OK;




	@UiHandler("OK")
	void onClickIP(ClickEvent e) {

		VaegtConnect();
	
	}


	
	public void VaegtConnect() {

		service.ConnectorVaegt(new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				
			}
		});
}
}