package code.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IProduktBatchService;
import code.client.service.IProduktBatchServiceAsync;

public class OpretProduktbatch extends Composite {

	private static OpretProduktbatchUiBinder uiBinder = GWT.create(OpretProduktbatchUiBinder.class);

	interface OpretProduktbatchUiBinder extends UiBinder<Widget, OpretProduktbatch> {
	}

	private IProduktBatchServiceAsync pbService;
	

	
	
	public OpretProduktbatch() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		pbService = GWT.create(IProduktBatchService.class);
		
		Date date = new Date();
		String test = DateTimeFormat.getShortDateFormat().format(date);
		datoBox.setText(test);
		
		
	}

	

	@UiField TextBox boxID;
	@UiField TextBox receptBox;
	@UiField TextBox statusBox;
	@UiField TextBox datoBox;
	@UiField Button submit;
	
	@UiHandler("submit")
	void opretPbKnap(ClickEvent e) {
		if(boxID.getText() == "" || receptBox.getText() == "" || statusBox.getText() == "" || datoBox.getText() == "") {
			Window.alert("Alle felter skal udfyldes!");
		}else { 
			pbService.addProduktBatch(Integer.parseInt(boxID.getText()), 
				Integer.parseInt(receptBox.getText()), Integer.parseInt(statusBox.getText()), datoBox.getText(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Produktbatchen blev oprettet");
				boxID.setText("");
				statusBox.setText("");
				datoBox.setText("");
				receptBox.setText("");
			}

		});
		}
	}
	

	
}
