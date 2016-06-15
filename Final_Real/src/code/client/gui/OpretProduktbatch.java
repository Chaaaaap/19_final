package code.client.gui;

import java.util.Date;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IProduktBatchService;
import code.client.service.IProduktBatchServiceAsync;
import code.shared.validate.Validator;

public class OpretProduktbatch extends Composite {

	private static OpretProduktbatchUiBinder uiBinder = GWT.create(OpretProduktbatchUiBinder.class);

	interface OpretProduktbatchUiBinder extends UiBinder<Widget, OpretProduktbatch> {
	}

	private IProduktBatchServiceAsync pbService;
	private Validator validator = new Validator();

	
	
	@SuppressWarnings("deprecation")
	public OpretProduktbatch() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		pbService = GWT.create(IProduktBatchService.class);
		
		Date date = new Date();
		String test = DateTimeFormat.getShortDateFormat().format(date);
		datoBox.setText(test);
		
		initValidators();
	}

	

	private void initValidators() {
		boxID.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(validator.validateInt(boxID.getText())) {
					pbErrorLabel.setText("");
					submit.setEnabled(true);
				} else {
					pbErrorLabel.setText("Produktbatch ID skal være et heltal");
					submit.setEnabled(false);
				}
			}
		});
		
		receptBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(validator.validateInt(receptBox.getText())) {
					riErrorLabel.setText("");
					submit.setEnabled(true);
				} else {
					riErrorLabel.setText("Råvare ID skal være et heltal");
					submit.setEnabled(false);
				}
			}
		});
		
		datoBox.addKeyUpHandler(new KeyUpHandler() {
			
			@Override
			public void onKeyUp(KeyUpEvent event) {
				if(validator.validateDate(datoBox.getText())) {
					dateErrorLabel.setText("");
					submit.setEnabled(true);
				} else {
					dateErrorLabel.setText("Datoen findes ikke");
					submit.setEnabled(false);
				}
			}
		});
	}



	@UiField TextBox boxID;
	@UiField TextBox receptBox;
	@UiField TextBox datoBox;
	@UiField Label pbErrorLabel;
	@UiField Label riErrorLabel;
	@UiField Label dateErrorLabel;
	@UiField Button submit;
	
	@UiHandler("submit")
	void opretPbKnap(ClickEvent e) {
		if(boxID.getText() == "" || receptBox.getText() == "" || datoBox.getText() == "") {
			Window.alert("Alle felter skal udfyldes!");
		}else { 
			pbService.addProduktBatch(Integer.parseInt(boxID.getText()), 
				Integer.parseInt(receptBox.getText()), datoBox.getText(), new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Produktbatchen blev oprettet");
				boxID.setText("");
				datoBox.setText("");
				receptBox.setText("");
			}

		});
		}
	}
	

	
}
