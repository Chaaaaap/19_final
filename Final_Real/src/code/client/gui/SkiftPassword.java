package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.Client;
import code.shared.OperatoerDTO;

public class SkiftPassword extends Composite {

	private static SkiftPasswordUiBinder uiBinder = GWT.create(SkiftPasswordUiBinder.class);

	interface SkiftPasswordUiBinder extends UiBinder<Widget, SkiftPassword> {
	}
	
	private OperatoerDTO opr;
	private Client client;

	public SkiftPassword(OperatoerDTO opr, Client client) {
		initWidget(uiBinder.createAndBindUi(this));
		this.opr = opr;
		this.client = client;
	}

}
