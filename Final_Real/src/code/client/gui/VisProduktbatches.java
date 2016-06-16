package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IProduktBatchService;
import code.client.service.IProduktBatchServiceAsync;
import code.shared.ProduktBatchDTO;

public class VisProduktbatches extends Composite {

	private static VisProduktBatchesUiBinder uiBinder = GWT.create(VisProduktBatchesUiBinder.class);

	interface VisProduktBatchesUiBinder extends UiBinder<Widget, VisProduktbatches> {
	}
	private IProduktBatchServiceAsync pbService;

	public VisProduktbatches() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		pbService = GWT.create(IProduktBatchService.class);
		visProduktBatch();
	}
	

	@UiField Label pbIDLabel;
	@UiField Label receptIDLabel;
	@UiField Label statusLabel;

	private void visProduktBatch() {
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		pbService.getProduktBatches(new AsyncCallback<ArrayList<ProduktBatchDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(ArrayList<ProduktBatchDTO> result) {
				VerticalPanel vPanel = new VerticalPanel();
				
				pbIDLabel.setText("ProduktBatch ID");
				receptIDLabel.setText("Recept ID");
				statusLabel.setText("Status");
				
				
				if(!result.isEmpty()) {
					for (ProduktBatchDTO produkt : result) {
						HorizontalPanel hPanel = new HorizontalPanel();
						Label pb_id = new Label();
						Label status = new Label();
						Label receptID = new Label();
						
						if(produkt.getStatus() == 0) {
							pb_id.setText(produkt.getPb_id()+"");
							status.setText("Oprettet");
							receptID.setText(produkt.getRecept_id()+"");
						}else if(produkt.getStatus() == 1) {
							pb_id.setText(produkt.getPb_id()+"");
							status.setText("Under produktion");
							receptID.setText(produkt.getRecept_id()+"");
						}else if(produkt.getStatus() == 2) {
							pb_id.setText(produkt.getPb_id()+"");
							status.setText("Afsluttet");
							receptID.setText(produkt.getRecept_id()+"");
						}
						
						hPanel.add(pb_id);
						hPanel.add(receptID);
						hPanel.add(status);
						
						vPanel.add(hPanel);
					}
				}

				Final_Real.attachContent(vPanel);
			}
			
		});
	}
}
