package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.validate.Validator;

public class OpretRaavare extends Composite {

	private static OpretRaavareUiBinder uiBinder = GWT.create(OpretRaavareUiBinder.class);

	interface OpretRaavareUiBinder extends UiBinder<Widget, OpretRaavare> {
	}

	private IRaavareServiceAsync service;
	private Validator validator = new Validator();

	public OpretRaavare() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IRaavareService.class);

		boxID.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(validator.validateInt(boxID.getText())) {
					idErrorLabel.setText("");
					submit.setEnabled(true);
				} else {
					idErrorLabel.setText("Råvare ID skal være et heltal");
					submit.setEnabled(false);
				}
			}
		});
	}

	@UiField TextBox boxID;
	@UiField TextBox boxNavn;
	@UiField TextBox boxSupplier;
	@UiField Label idErrorLabel;
	@UiField Button submit;

	@UiHandler("submit")
	void opretRaavare(ClickEvent e) {
		if(boxID.getText() == "" || boxNavn.getText() == "" || boxSupplier.getText() == "") {
			Window.alert("Alle felter skal udfyldes!");
		}else { service.addRaavare(Integer.parseInt(boxID.getText()), 
				boxNavn.getText(), boxSupplier.getText(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Raavaren er oprettet");
				boxID.setText("");
				boxNavn.setText("");
				boxSupplier.setText("");
			}

		});
		}
	}
}
