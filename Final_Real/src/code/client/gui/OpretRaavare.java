package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class OpretRaavare extends Composite {

	private static OpretRaavareUiBinder uiBinder = GWT.create(OpretRaavareUiBinder.class);

	interface OpretRaavareUiBinder extends UiBinder<Widget, OpretRaavare> {
	}

	public OpretRaavare() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
