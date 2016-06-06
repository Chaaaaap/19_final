package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RedigerRaavare extends Composite {

	private static RedigerRaavareUiBinder uiBinder = GWT.create(RedigerRaavareUiBinder.class);

	interface RedigerRaavareUiBinder extends UiBinder<Widget, RedigerRaavare> {
	}

	public RedigerRaavare() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
