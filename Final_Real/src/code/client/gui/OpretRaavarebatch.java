package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class OpretRaavarebatch extends Composite {

	private static OpretRaavarebatchUiBinder uiBinder = GWT.create(OpretRaavarebatchUiBinder.class);

	interface OpretRaavarebatchUiBinder extends UiBinder<Widget, OpretRaavarebatch> {
	}

	public OpretRaavarebatch() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
