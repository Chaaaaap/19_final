package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VisRaavarebatches extends Composite {

	private static VisRaavarebatchesUiBinder uiBinder = GWT.create(VisRaavarebatchesUiBinder.class);

	interface VisRaavarebatchesUiBinder extends UiBinder<Widget, VisRaavarebatches> {
	}

	public VisRaavarebatches() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
