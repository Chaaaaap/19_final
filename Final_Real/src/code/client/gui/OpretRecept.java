package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class OpretRecept extends Composite {

	private static OpretReceptUiBinder uiBinder = GWT.create(OpretReceptUiBinder.class);

	interface OpretReceptUiBinder extends UiBinder<Widget, OpretRecept> {
	}

	public OpretRecept() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
