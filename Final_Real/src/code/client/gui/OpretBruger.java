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

import code.client.Final_Real;
import code.client.service.OperatoerClient;
import code.shared.validate.Validator;

public class OpretBruger extends Composite {

	private static OpretBrugerUiBinder uiBinder = GWT.create(OpretBrugerUiBinder.class);

	interface OpretBrugerUiBinder extends UiBinder<Widget, OpretBruger> {
	}

	private OperatoerClient client;
	private Validator validator = new Validator();

	public OpretBruger(OperatoerClient client) {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);

		this.client = client;
		boxGentag.addKeyUpHandler(new KeyUpHandler() {
			@Override
			public void onKeyUp(KeyUpEvent event) {
				passwordCheck();
			}
		});
		boxID.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				idCheck(boxID.getText());
			}
		});
		boxCPR.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				cprCheck(boxCPR.getText());
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
	@UiField Label labelPassError;
	@UiField Label labelIDError;
	@UiField Label labelCPRError;
	@UiField Button submit;



	@UiHandler("submit")
	void opretBruger(ClickEvent e) {
		if(boxID.getText() == "" || boxNavn.getText() == "" || boxCPR.getText() == "" || 
				boxPassword.getText() == "") {
			Window.alert("Alt undtagen initialer SKAL udfyldes!");
		} else if(boxPassword.getText().equals(boxGentag.getText())) {
			client.opretBruger(Integer.parseInt(boxID.getText()), boxNavn.getText(), boxIni.getText(), boxCPR.getText(), boxGentag.getText(), boxType.getSelectedItemText());
		}
	}
	
	
	private void idCheck(String value) {
		if(validator.validateInt(value)) {
			labelIDError.setText("");
			submit.setEnabled(true);
		} else {
			labelIDError.setText("Operatør ID skal være et heltal");
			submit.setEnabled(false);
		}
	}
	
	private void passwordCheck() {
		if(validator.validatePassword(boxGentag.getText())) {
			if(boxGentag.getText().equals(boxPassword.getText())) {
				labelPassError.setText("");
				submit.setEnabled(true);
			} else {
				labelPassError.setText("Passwordet er ikke ens i begge felter");
			}
		} else {
			submit.setEnabled(false);
			labelPassError.setText("Passwordet er ikke godt nok");
		}
	}
	
	private void cprCheck(String value) {
		if(validator.validateCPR(value)) {
			labelCPRError.setText("");
			submit.setEnabled(true);
		} else {
			labelCPRError.setText("CPR-nummeret skal stå ddmmååxxxx");
			submit.setEnabled(false);
		}
	}

}
