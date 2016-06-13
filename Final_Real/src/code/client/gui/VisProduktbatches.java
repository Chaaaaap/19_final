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
	
//	@UiField Button visListe;
	@UiField Label idLabel;
	@UiField Label statusLabel;
	@UiField Label receptIDLabel;
//	@UiHandler("visListe")
//	void visListe(ClickEvent e) {
//		visRaavarer();
//	}
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
				idLabel.setText("ProduktBatch ID");
				statusLabel.setText("Status nr.");
				receptIDLabel.setText("Recept ID");
				
				if(!result.isEmpty()) {
					for (ProduktBatchDTO produkt : result) {
						HorizontalPanel hPanel = new HorizontalPanel();
						Label id = new Label(produkt.getPb_id()+"");
						Label navn = new Label(produkt.getStatus()+"");
						Label lev = new Label(produkt.getRecept_id()+"");
						hPanel.add(id);
						hPanel.add(navn);
						hPanel.add(lev);
						vPanel.add(hPanel);
					}
				}

				Final_Real.attachContent(vPanel);
			}
			
		});
	}
}
