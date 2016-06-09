package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;

public class VisRecepter extends Composite {

	private static VisRecepterUiBinder uiBinder = GWT.create(VisRecepterUiBinder.class);

	interface VisRecepterUiBinder extends UiBinder<Widget, VisRecepter> {
	}

	public VisRecepter() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
	}

}
