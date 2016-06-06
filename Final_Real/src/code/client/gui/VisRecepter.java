package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class VisRecepter extends Composite {

	private static VisRecepterUiBinder uiBinder = GWT.create(VisRecepterUiBinder.class);

	interface VisRecepterUiBinder extends UiBinder<Widget, VisRecepter> {
	}

	public VisRecepter() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
