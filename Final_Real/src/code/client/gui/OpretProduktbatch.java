package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;

public class OpretProduktbatch extends Composite {

	private static OpretProduktbatchUiBinder uiBinder = GWT.create(OpretProduktbatchUiBinder.class);

	interface OpretProduktbatchUiBinder extends UiBinder<Widget, OpretProduktbatch> {
	}

	public OpretProduktbatch() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
	}

	@UiField TextBox boxID;
	@UiField TextBox boxRecept;
	@UiField Button submit;
	
}
