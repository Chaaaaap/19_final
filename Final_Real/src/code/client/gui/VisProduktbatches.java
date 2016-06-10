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
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.ProduktBatchDTO;
import code.shared.RaavareDTO;

public class VisProduktbatches extends Composite {

	private static VisProduktbatchesUiBinder uiBinder = GWT.create(VisProduktbatchesUiBinder.class);

	interface VisProduktbatchesUiBinder extends UiBinder<Widget, VisProduktbatches> {
	}
	private IProduktBatchServiceAsync pbService;
	
	public VisProduktbatches() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		pbService = GWT.create(IProduktBatchService.class);
		visProduktBatch();
	}
	
	
	@UiField Label idLabel;
	@UiField Label statusLabel;
	@UiField Label receptIDLabel;
	@UiField Label rb_idLabel;
	@UiField Label taraLabel;
	@UiField Label nettoLabel;
	@UiField Label oprLabel;

	private void visProduktBatch() {
		Window.alert("visProduktBatch");
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		
		pbService.getProduktBatches(new AsyncCallback<ArrayList<ProduktBatchDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(ArrayList<ProduktBatchDTO> result) {
				Window.alert("onSuccess");
				VerticalPanel vPanel = new VerticalPanel();
				idLabel.setText("Produktbatch ID");
				statusLabel.setText("Status");
				receptIDLabel.setText("Recept ID");
				rb_idLabel.setText("Råvarebatch ID");
				taraLabel.setText("Tara");
				nettoLabel.setText("Netto");
				oprLabel.setText("Operatør ID");
				
				if(!result.isEmpty()) {
					for (ProduktBatchDTO produktbatch : result) {
						HorizontalPanel hPanel = new HorizontalPanel();
						Label pb_id = new Label(produktbatch.getPb_id()+"");
						Label status = new Label(produktbatch.getStatus());
						Label rcpt_id = new Label(produktbatch.getRecept_id()+"");
						Label rb_id = new Label(produktbatch.getRb_id()+"");
						Label tara = new Label(produktbatch.getTara()+"");
						Label netto = new Label(produktbatch.getNetto()+"");
						Label opr_id = new Label(produktbatch.getOprID()+"");
						
						
						hPanel.add(pb_id);
						hPanel.add(status);
						hPanel.add(rcpt_id);
						hPanel.add(rb_id);
						hPanel.add(tara);
						hPanel.add(netto);
						hPanel.add(opr_id);
						vPanel.add(hPanel);
					}
				}

				Final_Real.attachContent(vPanel);
				
			}
			
		});
	}

}
