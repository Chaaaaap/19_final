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
import code.client.service.IRaavareBatchService;
import code.client.service.IRaavareBatchServiceAsync;
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.RaavareBatchDTO;
import code.shared.RaavareDTO;

public class VisRaavarebatches extends Composite {

	private static VisRaavarebatchesUiBinder uiBinder = GWT.create(VisRaavarebatchesUiBinder.class);

	interface VisRaavarebatchesUiBinder extends UiBinder<Widget, VisRaavarebatches> {
	}
	
	private IRaavareBatchServiceAsync RbService;

	

	public VisRaavarebatches() {
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		RbService = GWT.create(IRaavareBatchService.class);
		visRaavarerBatch();
	}
	
	@UiField Label raavareBatchIdLabel;
	@UiField Label raavareIdLabel;
	@UiField Label mængdeLabel;

	private void visRaavarerBatch() 
	{
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		RbService.getRaavareBatch(new AsyncCallback<ArrayList<RaavareBatchDTO>>() 
		{

			@Override
			public void onFailure(Throwable caught) 
			{
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(ArrayList<RaavareBatchDTO> result) 
			{
				VerticalPanel vPanel = new VerticalPanel();
				raavareBatchIdLabel.setText("RåvareBatch ID");
				raavareIdLabel.setText("Råvare ID");
				mængdeLabel.setText("Mængde");
				
				if(!result.isEmpty()) 
				{
					for (RaavareBatchDTO raavareBatch : result) {
						HorizontalPanel hPanel = new HorizontalPanel();
						Label raavareBatchId = new Label(raavareBatch.getRaavareBatch_id()+"");
						Label raavareId = new Label(raavareBatch.getRaavare_id()+"");
						Label Mængde = new Label(raavareBatch.getMængde()+"");
						
						hPanel.add(raavareBatchId);
						hPanel.add(raavareId);
						hPanel.add(Mængde);
						vPanel.add(hPanel);
					}
				}

				Final_Real.attachContent(vPanel);
			}
			
		});
	}
}