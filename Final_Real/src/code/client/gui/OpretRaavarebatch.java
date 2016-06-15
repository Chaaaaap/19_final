package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IRaavareBatchService;
import code.client.service.IRaavareBatchServiceAsync;
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.RaavareDTO;

public class OpretRaavarebatch extends Composite 
{

	private static OpretRaavarebatchUiBinder uiBinder = GWT.create(OpretRaavarebatchUiBinder.class);

	interface OpretRaavarebatchUiBinder extends UiBinder<Widget, OpretRaavarebatch> 
	{
		
	}

	private IRaavareBatchServiceAsync rbService;
	private IRaavareServiceAsync raavareService;

	public OpretRaavarebatch() 
	{
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		rbService = GWT.create(IRaavareBatchService.class);	
		raavareService = GWT.create(IRaavareService.class);
		addRaavareID();
	}


	@UiField TextBox boxRaavareBatchID;
	@UiField ListBox boxRaavareNr;
	@UiField TextBox boxMaengde;
	@UiField Button submit;

	@UiHandler("submit")
	void opretRaavareBatch(ClickEvent e) 
	{
		if(boxRaavareBatchID.getText() == "" || boxRaavareNr.getSelectedItemText() == "" || boxMaengde.getText() == "") 
		{
			Window.alert("Alle felter skal udfyldes!");
		}
		else 
		{ 
			rbService.addRaavareBatch(Integer.parseInt(boxRaavareBatchID.getText()), 
					Integer.parseInt(boxRaavareNr.getSelectedItemText()), Integer.parseInt(boxMaengde.getText()), new AsyncCallback<Void>() 
			{

				@Override
				public void onFailure(Throwable caught) 
				{
					Window.alert(caught.getMessage());
				}

				@Override
				public void onSuccess(Void result) 
				{
					Window.alert("RaavarenBatch er oprettet");
					boxRaavareBatchID.setText("");
					boxMaengde.setText("");
				}
			});
		}
	}

	public void addRaavareID()
	{
		raavareService.getRaavarer(new AsyncCallback<ArrayList<RaavareDTO>>() 
		{
			@Override
			public void onSuccess(ArrayList<RaavareDTO> result) 
			{
				boxRaavareNr.addItem("", "blank");
				for (RaavareDTO raavareDTO : result) 
				{
					boxRaavareNr.addItem(raavareDTO.getRaavare_id()+"","");
				}
			}

			@Override
			public void onFailure(Throwable caught) 
			{
				Window.alert(caught.getMessage());
			}

		});	
	}
}
