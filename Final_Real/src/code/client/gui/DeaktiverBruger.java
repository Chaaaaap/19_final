package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class DeaktiverBruger extends Composite {

	private static DeaktiverBrugerUiBinder uiBinder = GWT.create(DeaktiverBrugerUiBinder.class);

	interface DeaktiverBrugerUiBinder extends UiBinder<Widget, DeaktiverBruger> {
	}

	public DeaktiverBruger() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	

}
