package code.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import code.client.gui.Login;
import code.client.gui.Login.LoginListener;
import code.client.gui.MainMenu;
import code.client.service.Client;
import code.client.service.Client.IClientCallback;
import code.shared.OperatoerDTO;


public class Final_Real implements EntryPoint, LoginListener {
	
	private OperatoerDTO oprLoggedIn;
	private VerticalPanel view = new VerticalPanel();
	private VerticalPanel content = new VerticalPanel();
	private Client client = new Client(GWT.getHostPageBaseURL()+"19_Final", 
			new IClientCallback() {
		
		@Override
		public void onLogin(OperatoerDTO opr) {
			oprLoggedIn = opr;
			content.clear();
			MainMenu menu = new MainMenu(oprLoggedIn, client);
			content.add(menu);
		}
	});
	

	public void onModuleLoad() {
		Image image = new Image();
		image.setUrl("images/piller.jpg");
		image.setWidth("800px");
		image.setHeight("200px");
		view.add(image);
		view.add(content);
		
		onLogin();
		
		view.getElement().setAttribute("align", "center");
		content.getElement().setAttribute("align", "center");
		
		RootPanel.get().add(view);
	}

	@Override
	public OperatoerDTO onLogin() {
		Login login = new Login(client);
		content.add(login);
		return login.oprLogin();
	}
}
