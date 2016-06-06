package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RedigerProduktbatch extends Composite {

	private static RedigerProduktbatchUiBinder uiBinder = GWT.create(RedigerProduktbatchUiBinder.class);

	interface RedigerProduktbatchUiBinder extends UiBinder<Widget, RedigerProduktbatch> {
	}

	public RedigerProduktbatch() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
