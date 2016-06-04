package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.Client;
import code.shared.OperatoerDTO;

public class MainMenu extends Composite {

	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);

	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	public MainMenu(OperatoerDTO opr, Client client) {
		initWidget(uiBinder.createAndBindUi(this));
		
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
	@UiField Button brugerRedigering;
//	@UiField MenuItem opretBruger;
	@UiField Button receptRedigering;
	@UiField Button raavareRedigering;
}
