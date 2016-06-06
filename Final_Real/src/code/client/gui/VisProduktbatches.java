package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VisProduktbatches extends Composite {

	private static VisProduktbatchesUiBinder uiBinder = GWT.create(VisProduktbatchesUiBinder.class);

	interface VisProduktbatchesUiBinder extends UiBinder<Widget, VisProduktbatches> {
	}

	public VisProduktbatches() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
