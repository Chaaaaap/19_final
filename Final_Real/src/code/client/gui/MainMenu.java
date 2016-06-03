package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.Client;
import code.shared.OperatoerDTO;

public class MainMenu extends Composite {

	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);

	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	public MainMenu(OperatoerDTO opr, Client client) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
