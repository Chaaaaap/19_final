package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IReceptService;
import code.client.service.IReceptServiceAsync;
import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;


public class RedigerRecept extends Composite 
{

	private static RedigerReceptUiBinder uiBinder = GWT.create(RedigerReceptUiBinder.class);

	interface RedigerReceptUiBinder extends UiBinder<Widget, RedigerRecept> 
	{

	}

	private IReceptServiceAsync service;
	private TextBox nameBox = new TextBox();
	private TextBox receptIDBox = new TextBox();
	private TextBox[] type;
	private TextBox[] maengde;
	private TextBox[] tol;
	private ListBox receptList;

	public RedigerRecept() 
	{
		initWidget(uiBinder.createAndBindUi(this));
		type = new TextBox[5];
		maengde = new TextBox[5];
		tol = new TextBox[5];
		receptList = new ListBox();
		receptList.addItem("", "blank");
		vPanel.insert(receptList, 1);
		
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IReceptService.class);
		
		redigerRecept();
	}

	@UiField Label receptNavnLabel;
	@UiField Label receptIdLabel;
	@UiField Label raavare1Label;
	@UiField Label raavare2Label;
	@UiField Label raavare3Label;
	@UiField Label raavare4Label;
	@UiField Label raavare5Label;
	@UiField VerticalPanel vPanel;
	@UiField HorizontalPanel hPanel;



	private void redigerRecept() {
		Final_Real.clearContent();
		Final_Real.attachContent(this);

		service.getRecept(new AsyncCallback<ArrayList<ReceptDTO>>() {
//
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<ReceptDTO> result) {
				final ArrayList<ReceptDTO> dtoList = result;
				
				
				
				
				for (ReceptDTO rDTO : result) {
					receptList.addItem(rDTO.getReceptNavn(), rDTO.getRecept_id()+"");
				}
				
				receptList.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						
						int i = receptList.getSelectedIndex();
						String receptValgt = receptList.getSelectedValue();
						ArrayList<ReceptKomponentDTO> list = dtoList.get(i-1).getKomp();
						
						//TODO Index fucker mega...
						nameBox.setEnabled(false);
						nameBox.setStyleName("style.Btn");
						nameBox.setText(dtoList.get(i-1).getReceptNavn());
						receptIDBox.setEnabled(false);
						receptIDBox.setText(dtoList.get(i-1).getRecept_id()+"");
						hPanel.add(nameBox);
						hPanel.add(receptIDBox);
						
						if(!receptValgt.equals("blank")) {
						
						for (int j = 0; j < list.size(); j++) {
							Window.alert(list.get(j).getRaavare_id()+"");
							VerticalPanel redigerPanel = new VerticalPanel();
							HorizontalPanel knapPanel = new HorizontalPanel();
							Label idLabel = new Label("Raavare ID");
							type[j] = new TextBox();
							type[j].setText(list.get(j).getRaavare_id()+"");
							type[j].setEnabled(false);
							
							Label maengdeLabel = new Label("Maengde");
							maengde[j] = new TextBox();
							maengde[j].setText(list.get(j).getMængde()+"");
							maengde[j].setEnabled(false);
							
							Label tolLabel = new Label("Tolerance");
							tol[j] = new TextBox();
							tol[j].setText(list.get(j).getTolerance()+"");
							tol[j].setEnabled(false);
							
							final Button rediger = new Button("Rediger");
							final Button ok = new Button("Gem");
							ok.setVisible(false);
							ok.setWidth("75px");
							final Button annuller = new Button("Annuller");
							annuller.setVisible(false);
							annuller.setWidth("75px");
							
							redigerPanel.add(idLabel);
							redigerPanel.add(type[j]);
							redigerPanel.add(maengdeLabel);
							redigerPanel.add(maengde[j]);
							redigerPanel.add(tolLabel);
							redigerPanel.add(tol[j]);
							knapPanel.add(rediger);
							knapPanel.add(ok);
							knapPanel.add(annuller);
							redigerPanel.add(knapPanel);
							
							hPanel.add(redigerPanel);
							
							rediger.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									rediger.setVisible(false);
									ok.setVisible(true);
									annuller.setVisible(true);
									
								}
								
							});
						}
						
						}
					}
					
				});
//				VerticalPanel vPanel = new VerticalPanel();
//				receptNavnLabel.setText("Recept Navn");
//				receptIdLabel.setText("Recept ID");
//				raavareIdLabel.setText("Råvare ID");
//				nomNettoLabel.setText("nom-Netto");
//				toleranceLabel.setText("Tolerance");
//
//				if(!result.isEmpty()) {
//					for (ReceptDTO recept : result) {
//						final ReceptDTO rv = recept;
//						final HorizontalPanel hPanel = new HorizontalPanel();
//
//						final TextBox receptNavn = new TextBox();
//						final TextBox receptID = new TextBox();
//						final TextBox raavareID = new TextBox();
//						final TextBox nomNetto = new TextBox();
//						final TextBox tolerance = new TextBox();
//
//						final Button rediger = new Button("Rediger");
//						final Button gem = new Button("Gem");
//						final Button annuller = new Button("Annuller");
//
//						receptNavn.setText(recept.getReceptNavn());
//						receptID.setText(recept.getRecept_id()+"");
//						//TODO Skal laves
//						raavareID.setText(recept.getRaavare_id()+"");
//						nomNetto.setText(recept.getNom_netto()+"");
//						tolerance.setText(recept.getTolerance()+"");
//
//						rediger.setStyleName("style.Rediger");
//						gem.setStyleName("style.Rediger");
//						annuller.setStyleName("style.Rediger");
//
//						gem.setVisible(false);
//						annuller.setVisible(false);
//
//						receptNavn.setEnabled(false);
//						receptID.setEnabled(false);
//						raavareID.setEnabled(false);
//						nomNetto.setEnabled(false);
//						tolerance.setEnabled(false);
//
//						hPanel.add(receptNavn);
//						hPanel.add(receptID);
//						hPanel.add(raavareID);
//						hPanel.add(nomNetto);
//						hPanel.add(tolerance);
//						hPanel.add(rediger);
//						hPanel.add(gem);
//						hPanel.add(annuller);
//						vPanel.add(hPanel);
//
//						rediger.addClickHandler(new ClickHandler() {
//
//							@Override
//							public void onClick(ClickEvent event) {
//								receptNavn.setEnabled(true);
//								receptID.setEnabled(true);
//								raavareID.setEnabled(true);
//								nomNetto.setEnabled(true);
//								tolerance.setEnabled(true);
//
//								rediger.setVisible(false);
//								gem.setVisible(true);
//								annuller.setVisible(true);								
//							}
//
//						});
//
//						gem.addClickHandler(new ClickHandler() {
//
//							@Override
//							public void onClick(ClickEvent event) {
//								service.redigerRecept(receptNavn.getText(), 
//										Integer.parseInt(receptID.getText()), 
//										Integer.parseInt(raavareID.getText()), 
//										Integer.parseInt(nomNetto.getText()), 
//										Integer.parseInt(tolerance.getText()),
//										rv.getRecept_id(),
//										new AsyncCallback<Void>() {
//
//									@Override
//									public void onFailure(Throwable caught) {
//										Window.alert(caught.getMessage());
//									}
//
//									@Override
//									public void onSuccess(Void result) {
//										
//										rediger.setVisible(true);
//										gem.setVisible(false);
//										annuller.setVisible(false);
//										
//										receptNavn.setEnabled(false);
//										receptID.setEnabled(false);
//										raavareID.setEnabled(false);
//										nomNetto.setEnabled(false);
//										tolerance.setEnabled(false);
//										
//									}
//
//								});

//								annuller.addClickHandler(new ClickHandler() {
//
//									@Override
//									public void onClick(ClickEvent event) {
//										receptNavn.setText(rv.getReceptNavn());
//										receptID.setText(rv.getRecept_id()+"");
//										//TODO Skal laves
//										raavareID.setText(rv.getRaavare_id()+"");
//										nomNetto.setText(rv.getNom_netto()+"");
//										tolerance.setText(rv.getTolerance()+"");
//
//										receptNavn.setEnabled(false);
//										receptID.setEnabled(false);
//										raavareID.setEnabled(false);
//										nomNetto.setEnabled(false);
//										tolerance.setEnabled(false);
//
//										gem.setVisible(false);
//										annuller.setVisible(false);
//										rediger.setVisible(true);
//									}
//
//								});
//							}
//						});
//
//						Final_Real.attachContent(vPanel);
//
//					}
//				}
			}
		});
	}
}

