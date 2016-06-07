package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpretRaavare extends Composite {

	private static OpretRaavareUiBinder uiBinder = GWT.create(OpretRaavareUiBinder.class);

	interface OpretRaavareUiBinder extends UiBinder<Widget, OpretRaavare> {
	}

	public OpretRaavare() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField TextBox boxID;
	@UiField TextBox boxNavn;
	@UiField TextBox boxSupplier;
	@UiField Button submit;
}
