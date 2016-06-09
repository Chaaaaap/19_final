package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;

public class RedigerRaavarebatch extends Composite {

	private static RedigerRaavarebatchUiBinder uiBinder = GWT.create(RedigerRaavarebatchUiBinder.class);

	interface RedigerRaavarebatchUiBinder extends UiBinder<Widget, RedigerRaavarebatch> {
	}

	public RedigerRaavarebatch() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
	}

}
