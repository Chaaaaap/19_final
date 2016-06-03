package code.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import code.client.gui.Login;
import code.client.gui.Login.LoginListener;
import code.client.service.Client;
import code.shared.OperatoerDTO;


public class Final_Real implements EntryPoint, LoginListener {
	
	private OperatoerDTO oprLoggedIn;
	private VerticalPanel content = new VerticalPanel();
	private Client client = new Client(GWT.getHostPageBaseURL()+"19_Final");
	

	public void onModuleLoad() {
		Label lbl = new Label("FUCK GWT!");
		oprLoggedIn = onLogin();
		
		content.getElement().setAttribute("align", "center");
		
		RootPanel.get().add(content);
	}

	@Override
	public OperatoerDTO onLogin() {
		Login login = new Login(client);
		content.add(login);
		return login.oprLogin();
	}
}
