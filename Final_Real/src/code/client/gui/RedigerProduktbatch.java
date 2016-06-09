package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;

public class RedigerProduktbatch extends Composite {

	private static RedigerProduktbatchUiBinder uiBinder = GWT.create(RedigerProduktbatchUiBinder.class);

	interface RedigerProduktbatchUiBinder extends UiBinder<Widget, RedigerProduktbatch> {
	}

	public RedigerProduktbatch() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
	}

}
