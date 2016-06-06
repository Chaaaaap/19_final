package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.Client;
import code.shared.OperatoerDTO;

public class MainMenu extends Composite {

	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);

	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	private OperatoerDTO opr;
	private Client client;
	public MainMenu(OperatoerDTO opr, Client client) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.opr = opr;
		this.client = client;
		
//		brugerRedigering.setStyleName("navbar navbar-default");
		brugerRedigering.setAutoOpen(true);
		brugerRedigering.setAnimationEnabled(true);
		
		if(opr.getType().equals("operatør")) {
			brugerRedigering.setVisible(false);
			receptRedigering.setVisible(false);
			raavareRedigering.setVisible(false);
		} else if(opr.getType().equals("værkfører")) {
			brugerRedigering.setVisible(false);
			
		} else if(opr.getType().equals("farmaceut")) {
			brugerRedigering.setVisible(false);
		}
	}

	@UiField Button afvej;
	@UiField Button skiftPassword;
	@UiField MenuBar brugerRedigering;
	@UiField MenuItem opretBruger;
	@UiField Button receptRedigering;
	@UiField Button raavareRedigering;
	
	@UiHandler("skiftPassword")
	void onClick(ClickEvent e) {
		SkiftPassword skiftPassword = new SkiftPassword(opr, client);
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		Final_Real.attachContent(skiftPassword);
		
	}
}
