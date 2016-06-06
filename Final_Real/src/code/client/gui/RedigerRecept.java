package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RedigerRecept extends Composite {

	private static RedigerReceptUiBinder uiBinder = GWT.create(RedigerReceptUiBinder.class);

	interface RedigerReceptUiBinder extends UiBinder<Widget, RedigerRecept> {
	}

	public RedigerRecept() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
