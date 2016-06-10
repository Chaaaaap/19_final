package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IReceptService;
import code.client.service.IReceptServiceAsync;
import code.shared.ReceptDTO;


public class RedigerRecept extends Composite 
{

	private static RedigerReceptUiBinder uiBinder = GWT.create(RedigerReceptUiBinder.class);

	interface RedigerReceptUiBinder extends UiBinder<Widget, RedigerRecept> 
	{

	}

	private IReceptServiceAsync service;

	public RedigerRecept() 
	{
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IReceptService.class);
		redigerRecept();
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
//		redigerRecept();
//	}

	private void redigerRecept() 
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
					for (ReceptDTO recept : result) 
					{
						final ReceptDTO rv = recept;
						final HorizontalPanel hPanel = new HorizontalPanel();

						final TextBox receptNavn = new TextBox();
						final TextBox receptID = new TextBox();
						final TextBox raavareID = new TextBox();
						final TextBox nomNetto = new TextBox();
						final TextBox tolerance = new TextBox();

						final Button rediger = new Button("Rediger");
						final Button gem = new Button("Gem");
						final Button annuller = new Button("Annuller");

						receptNavn.setText(recept.getReceptNavn());
						receptID.setText(recept.getRecept_id()+"");
						raavareID.setText(recept.getRaavare_id()+"");
						nomNetto.setText(recept.getNom_netto()+"");
						tolerance.setText(recept.getTolerance()+"");

						rediger.setStyleName("style.Rediger");
						gem.setStyleName("style.Rediger");
						annuller.setStyleName("style.Rediger");

						gem.setVisible(false);
						annuller.setVisible(false);

						receptNavn.setEnabled(false);
						receptID.setEnabled(false);
						raavareID.setEnabled(false);
						nomNetto.setEnabled(false);
						tolerance.setEnabled(false);

						hPanel.add(receptNavn);
						hPanel.add(receptID);
						hPanel.add(raavareID);
						hPanel.add(nomNetto);
						hPanel.add(tolerance);
						hPanel.add(rediger);
						hPanel.add(gem);
						hPanel.add(annuller);
						vPanel.add(hPanel);

						rediger.addClickHandler(new ClickHandler() 
						{

							@Override
							public void onClick(ClickEvent event) 
							{
								receptNavn.setEnabled(true);
								receptID.setEnabled(true);
								raavareID.setEnabled(true);
								nomNetto.setEnabled(true);
								tolerance.setEnabled(true);

								rediger.setVisible(false);
								gem.setVisible(true);
								annuller.setVisible(true);								
							}

						});

						gem.addClickHandler(new ClickHandler() 
						{

							@Override
							public void onClick(ClickEvent event) 
							{
								service.redigerRecept(receptNavn.getText(), 
										Integer.parseInt(receptID.getText()), 
										Integer.parseInt(raavareID.getText()), 
										Integer.parseInt(nomNetto.getText()), 
										Integer.parseInt(tolerance.getText()),
										rv.getRecept_id(),
										new AsyncCallback<Void>() 
								{

									@Override
									public void onFailure(Throwable caught) 
									{
										Window.alert(caught.getMessage());
									}

									@Override
									public void onSuccess(Void result) 
									{
										// TODO noget meningsfuldt.
									}

								});

								annuller.addClickHandler(new ClickHandler() 
								{

									@Override
									public void onClick(ClickEvent event) 
									{
										receptNavn.setText(rv.getReceptNavn());
										receptID.setText(rv.getRecept_id()+"");
										raavareID.setText(rv.getRaavare_id()+"");
										nomNetto.setText(rv.getNom_netto()+"");
										tolerance.setText(rv.getTolerance()+"");

										receptNavn.setEnabled(false);
										receptID.setEnabled(false);
										raavareID.setEnabled(false);
										nomNetto.setEnabled(false);
										tolerance.setEnabled(false);

										gem.setVisible(false);
										annuller.setVisible(false);
										rediger.setVisible(true);
									}

								});
							}
						});

						Final_Real.attachContent(vPanel);

					}
				}
			}
		});
	}
}

