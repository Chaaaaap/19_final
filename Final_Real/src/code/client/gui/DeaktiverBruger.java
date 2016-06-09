package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.OperatoerClient;

public class DeaktiverBruger extends Composite {

	private static DeaktiverBrugerUiBinder uiBinder = GWT.create(DeaktiverBrugerUiBinder.class);

	interface DeaktiverBrugerUiBinder extends UiBinder<Widget, DeaktiverBruger> {
	}

	public DeaktiverBruger(OperatoerClient client) {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
