package code.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.gui.Login;
import code.client.gui.Login.LoginListener;
import code.client.gui.MainMenu;
import code.client.service.OperatoerClient;
import code.client.service.OperatoerClient.IClientCallback;
import code.shared.OperatoerDTO;


public class Final_Real implements EntryPoint, LoginListener {
	
	private OperatoerDTO oprLoggedIn;
	private static MainMenu menu;
	private VerticalPanel view = new VerticalPanel();
	private VerticalPanel header = new VerticalPanel();
	private VerticalPanel menuView = new VerticalPanel();
	private static VerticalPanel content = new VerticalPanel();
	private OperatoerClient client = new OperatoerClient(GWT.getHostPageBaseURL()+"19_Final", 
			new IClientCallback() {
		
		@Override
		public void onLogin(OperatoerDTO opr) {
			oprLoggedIn = opr;
			content.clear();
			menu = new MainMenu(oprLoggedIn, client);
			content.add(menu);
		}
	});
	

	public void onModuleLoad() {
		
		Image image = new Image();
		image.setUrl("images/Pharma12.png");
		image.setWidth("100%");
		image.setHeight("200px");
		header.add(image);
		view.add(header);
		view.add(menuView);
		view.add(content);
		
		onLogin();
		header.getElement().setAttribute("align", "center");
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
	
	public static void  clearContent() {
		content.clear();
		content.add(menu);
	}
	
	public static void attachContent(Widget w) {
		content.add(w);
	}
}
