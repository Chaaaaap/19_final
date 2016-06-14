package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.OperatoerClient;
import code.shared.OperatoerDTO;
import code.shared.validate.Validator;

public class SkiftPassword extends Composite {

	private static SkiftPasswordUiBinder uiBinder = GWT.create(SkiftPasswordUiBinder.class);

	interface SkiftPasswordUiBinder extends UiBinder<Widget, SkiftPassword> {
	}

	private OperatoerDTO opr;
	private OperatoerClient client;
	private Validator validator = new Validator();

	public SkiftPassword(OperatoerDTO opr, OperatoerClient client) {
		initWidget(uiBinder.createAndBindUi(this));
		this.opr = opr;
		this.client = client;

		gentagPassword.addKeyUpHandler(new KeyUpHandler() {

			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(nytPassword.getText().equals(gentagPassword.getText())) {
					if(validator.validatePassword(gentagPassword.getText())) {
						submit.setEnabled(true);
						errorLabel.setText("");
					} else {
						submit.setEnabled(false);
						errorLabel.setText("Det nye password er ikke godt nok");
					}
				} else {
					submit.setEnabled(false);
					errorLabel.setText("Det nye password er ikke ens i begge felter");
				}
			}
		});
	}

	@UiField PasswordTextBox gammeltPassword;
	@UiField PasswordTextBox nytPassword;
	@UiField PasswordTextBox gentagPassword;
	@UiField Label errorLabel;
	@UiField Button submit;

	@UiHandler("submit")
	void onClick(ClickEvent e) {
		String glPassword = gammeltPassword.getText();
		String nytPass = nytPassword.getText();
		String gentagPass = gentagPassword.getText();
		if(nytPass.equals(gentagPass) && glPassword.equals(opr.getPassword())) {
			client.skiftPassword(opr, nytPass);
			opr.skiftPassword(nytPass);
		}

	}



}
