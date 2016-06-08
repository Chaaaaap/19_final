package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.OperatoerClient;
import code.shared.OperatoerDTO;

public class Login extends Composite {

	private static LoginUiBinder uiBinder = GWT.create(LoginUiBinder.class);

	interface LoginUiBinder extends UiBinder<Widget, Login> {
	}
	
	 public interface LoginListener { public OperatoerDTO onLogin(); }
	
	private OperatoerClient client;

	public Login(OperatoerClient client) {
		initWidget(uiBinder.createAndBindUi(this));
		this.client = client;
	}

	public OperatoerDTO oprLogin() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@UiField TextBox nameBox;
	@UiField PasswordTextBox passwordBox;	
	@UiField Button submit;
	
	
	
	@UiHandler("submit") 
	void onClick(ClickEvent e) {
		client.login(Integer.parseInt(nameBox.getText()), passwordBox.getText());
	}

}
