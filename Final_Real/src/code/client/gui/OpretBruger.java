package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.Client;

public class OpretBruger extends Composite {

	private static OpretBrugerUiBinder uiBinder = GWT.create(OpretBrugerUiBinder.class);

	interface OpretBrugerUiBinder extends UiBinder<Widget, OpretBruger> {
	}

	private Client client;

	public OpretBruger(Client client) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.client = client;
		boxGentag.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(boxGentag.getText().equals(boxPassword.getText())) {
					
				} else {
					labelError.setText("Passwordet er ikke ens i begge felter");
				}
			}
			
		});
	}
	
	@UiField TextBox boxID;
	@UiField TextBox boxNavn;
	@UiField TextBox boxIni;
	@UiField TextBox boxCPR;
	@UiField PasswordTextBox boxPassword;
	@UiField PasswordTextBox boxGentag;
	@UiField ListBox boxType;
	@UiField Label labelError;
	@UiField Button submit;
	
	
	
	@UiHandler("submit")
	void opretBruger(ClickEvent e) {
		if(boxID.getText() == "" || boxNavn.getText() == "" || boxCPR.getText() == "" || 
				boxPassword.getText() == "") {
			Window.alert("Alt undtagen initialer SKAL udfyldes!");
		} else {
			client.opretBruger(Integer.parseInt(boxID.getText()), boxNavn.getText(), boxIni.getText(), boxCPR.getText(), boxGentag.getText(), boxType.getSelectedItemText());
		}
	}

}
