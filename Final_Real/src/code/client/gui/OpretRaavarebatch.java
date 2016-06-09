package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IRaavareBatchServiceAsync;
import code.client.service.IRaavareService;

public class OpretRaavarebatch extends Composite {

	private static OpretRaavarebatchUiBinder uiBinder = GWT.create(OpretRaavarebatchUiBinder.class);

	interface OpretRaavarebatchUiBinder extends UiBinder<Widget, OpretRaavarebatch> {
	}
	
	private IRaavareBatchServiceAsync service;

	public OpretRaavarebatch() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IRaavareService.class);
		
	}

	@UiField TextBox boxID;
	@UiField ListBox boxRaavareNr;
	@UiField TextBox boxMaengde;
	@UiField Button submit;
	
	@UiHandler("submit")
	void opretRaavareBatch(ClickEvent e) {
		
	}

}
