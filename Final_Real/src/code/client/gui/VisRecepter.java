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
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IReceptService;
import code.client.service.IReceptServiceAsync;
import code.shared.ReceptDTO;

public class VisRecepter extends Composite 
{

	private static VisRecepterUiBinder uiBinder = GWT.create(VisRecepterUiBinder.class);

	interface VisRecepterUiBinder extends UiBinder<Widget, VisRecepter> 
	{

	}

	private IReceptServiceAsync service;

	public VisRecepter() 
	{
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IReceptService.class);
		visRaavarer();
	}

//	@UiField Button visListe;
	@UiField Label receptNavnLabel;
	@UiField Label receptIdLabel;
	@UiField Label raavareIdLabel;
	@UiField Label nomNettoLabel;
	@UiField Label toleranceLabel;
//	@UiHandler("visListe")


//	void visListe(ClickEvent e) 
//	{
//		visRaavarer();
//	}
	private void visRaavarer() 
	{
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service.getRecept(new AsyncCallback<ArrayList<ReceptDTO>>() 
		{

			@Override
			public void onFailure(Throwable caught) 
			{
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(ArrayList<ReceptDTO> result) 
			{
				VerticalPanel vPanel = new VerticalPanel();
				receptNavnLabel.setText("Recept Navn");
				receptIdLabel.setText("Recept ID");
				raavareIdLabel.setText("Råvare ID");
				nomNettoLabel.setText("nom-Netto");
				toleranceLabel.setText("Tolerance");

				if(!result.isEmpty()) 
				{
					for (ReceptDTO raavare : result) 
					{
						HorizontalPanel hPanel = new HorizontalPanel();
						
						Label receptNavn = new Label(raavare.getReceptNavn());
						Label receptId = new Label(raavare.getRecept_id()+"");
						Label raavareId = new Label(raavare.getRaavare_id()+"");
						Label nomNetto = new Label(raavare.getNom_netto()+"");
						Label tolerance = new Label(raavare.getTolerance()+"");
						
						hPanel.add(receptNavn);
						hPanel.add(receptId);
						hPanel.add(raavareId);
						hPanel.add(nomNetto);
						hPanel.add(tolerance);
						vPanel.add(hPanel);
					}
				}

				Final_Real.attachContent(vPanel);
			}

		});
	}
}