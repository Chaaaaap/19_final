package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class RedigerBruger extends Composite {

	private static RedigerBrugerUiBinder uiBinder = GWT.create(RedigerBrugerUiBinder.class);

	interface RedigerBrugerUiBinder extends UiBinder<Widget, RedigerBruger> {
	}

	public RedigerBruger() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
