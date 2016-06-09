package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;

public class VisProduktbatches extends Composite {

	private static VisProduktbatchesUiBinder uiBinder = GWT.create(VisProduktbatchesUiBinder.class);

	interface VisProduktbatchesUiBinder extends UiBinder<Widget, VisProduktbatches> {
	}

	public VisProduktbatches() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
	}

}
