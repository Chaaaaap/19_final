package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class OpretBruger extends Composite {

	private static OpretBrugerUiBinder uiBinder = GWT.create(OpretBrugerUiBinder.class);

	interface OpretBrugerUiBinder extends UiBinder<Widget, OpretBruger> {
	}

	public OpretBruger() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
