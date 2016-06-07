package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpretBruger extends Composite {

	private static OpretBrugerUiBinder uiBinder = GWT.create(OpretBrugerUiBinder.class);

	interface OpretBrugerUiBinder extends UiBinder<Widget, OpretBruger> {
	}

	public OpretBruger() {
		initWidget(uiBinder.createAndBindUi(this));
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
	
//	@UiHandler("boxGentag")
//	void
	
	@UiHandler("submit")
	void opretBruger(ClickEvent e) {
		if(boxID.getText() == "" || boxNavn.getText() == "" || boxCPR.getText() == "" || 
				boxPassword.getText() == "" || boxType.getSelectedItemText() == "") {
			
		}
	}

}
