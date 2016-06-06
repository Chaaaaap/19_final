package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VisRaavarer extends Composite {

	private static VisRaavarerUiBinder uiBinder = GWT.create(VisRaavarerUiBinder.class);

	interface VisRaavarerUiBinder extends UiBinder<Widget, VisRaavarer> {
	}

	public VisRaavarer() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
