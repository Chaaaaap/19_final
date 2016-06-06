package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RedigerRaavarebatch extends Composite {

	private static RedigerRaavarebatchUiBinder uiBinder = GWT.create(RedigerRaavarebatchUiBinder.class);

	interface RedigerRaavarebatchUiBinder extends UiBinder<Widget, RedigerRaavarebatch> {
	}

	public RedigerRaavarebatch() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
