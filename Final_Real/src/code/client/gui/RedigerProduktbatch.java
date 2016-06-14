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
import code.client.service.IProduktBatchService;
import code.client.service.IProduktBatchServiceAsync;
import code.shared.ProduktBatchDTO;
import code.shared.ProduktBatchKomponentDTO;


public class RedigerProduktbatch extends Composite 
{

	private static RedigerProduktbatchUiBinder uiBinder = GWT.create(RedigerProduktbatchUiBinder.class);

	interface RedigerProduktbatchUiBinder extends UiBinder<Widget, RedigerProduktbatch> 
	{

	}

	private IProduktBatchServiceAsync pbService;
	private TextBox pbIDBox = new TextBox();
	private TextBox statusBox = new TextBox();
	private TextBox receptIDBox = new TextBox();
	private TextBox[] rbID;
	private TextBox[] tara;
	private TextBox[] netto;
	private TextBox[] oprID;
	private ListBox pbList;

	public RedigerProduktbatch() {
		
		initWidget(uiBinder.createAndBindUi(this));
		rbID = new TextBox[5];
		tara = new TextBox[5];
		netto = new TextBox[5];
		oprID = new TextBox[5];
		pbList = new ListBox();
		pbList.addItem("", "blank");
		vPanel.insert(pbList, 0);
		pbIDBox.getElement().getStyle().setProperty("margin", "17px 0px 0px 0px");
		statusBox.getElement().getStyle().setProperty("margin", "17px 0px 0px 0px");
		receptIDBox.getElement().getStyle().setProperty("margin", "17px 0px 0px 0px");
		
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		pbService = GWT.create(IProduktBatchService.class);
		
		redigerProduktBatch();
	}

	@UiField Label pbIDLabel;
	@UiField Label statusLabel;
	@UiField Label receptIDLabel;
	@UiField Label pbkomp1Label;
	@UiField Label pbkomp2Label;
	@UiField Label pbkomp3Label;
	@UiField Label pbkomp4Label;
	@UiField Label pbkomp5Label;
	@UiField VerticalPanel vPanel;
	@UiField HorizontalPanel hPanel;



	private void redigerProduktBatch() {
		Final_Real.clearContent();
		Final_Real.attachContent(this);

		pbService.getProduktBatches(new AsyncCallback<ArrayList<ProduktBatchDTO>>() {
//
			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<ProduktBatchDTO> result) {
				final ArrayList<ProduktBatchDTO> dtoList = result;
				
				
				
				
				for (ProduktBatchDTO pbDTO : result) {
					pbList.addItem(pbDTO.getPb_id()+"");
				}
				
				pbList.addChangeHandler(new ChangeHandler() {

					@Override
					public void onChange(ChangeEvent event) {
						
						final int i = pbList.getSelectedIndex();
						String pbValgt = pbList.getSelectedValue();
						ArrayList<ProduktBatchKomponentDTO> list = dtoList.get(i-1).getKomp();
						
						//TODO Index fucker mega...
						pbIDBox.setEnabled(false);
						pbIDBox.setText(dtoList.get(i-1).getPb_id()+"");
						statusBox.setEnabled(false);
						statusBox.setText(dtoList.get(i-1).getStatus()+"");
						receptIDBox.setEnabled(false);
						receptIDBox.setText(dtoList.get(i-1).getRecept_id()+"");
						hPanel.add(pbIDBox);
						hPanel.add(statusBox);
						hPanel.add(receptIDBox);
						
						if(!pbValgt.equals("blank")) {
						
						for (int j = 0; j < list.size(); j++) {
							final int index = j;
							Window.alert(list.get(j).getRb_id()+"");
							VerticalPanel redigerPanel = new VerticalPanel();
							HorizontalPanel knapPanel = new HorizontalPanel();
							Label idLabel = new Label("Råvarebatch ID");
							rbID[j] = new TextBox();
							rbID[j].setText(list.get(j).getRb_id()+"");
							rbID[j].setEnabled(false);
							
							Label taraLabel = new Label("Tara");
							tara[j] = new TextBox();
							tara[j].setText(list.get(j).getTara()+"");
							tara[j].setEnabled(false);
							
							Label nettoLabel = new Label("Netto");
							netto[j] = new TextBox();
							netto[j].setText(list.get(j).getNetto()+"");
							netto[j].setEnabled(false);
							
							Label oprIDLabel = new Label("Operatør ID");
							oprID[j] = new TextBox();
							oprID[j].setText(list.get(j).getOprID()+"");
							oprID[j].setEnabled(false);
							
							final Button rediger = new Button("Rediger");
							final Button ok = new Button("Gem");
							ok.setVisible(false);
							ok.setWidth("75px");
							final Button annuller = new Button("Annuller");
							annuller.setVisible(false);
							annuller.setWidth("75px");
							
							redigerPanel.add(idLabel);
							redigerPanel.add(rbID[j]);
							redigerPanel.add(taraLabel);
							redigerPanel.add(tara[j]);
							redigerPanel.add(nettoLabel);
							redigerPanel.add(netto[j]);
							redigerPanel.add(oprIDLabel);
							redigerPanel.add(oprID[j]);
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
									
									pbIDBox.setEnabled(true);
									statusBox.setEnabled(true);
									receptIDBox.setEnabled(true);
									rbID[index].setEnabled(true);
									tara[index].setEnabled(true);
									netto[index].setEnabled(true);
									oprID[index].setEnabled(true);
									
								}
								
							});
							
							ok.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									// TODO Auto-generated method stub
									
								}
								
							});
							
							annuller.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									rediger.setVisible(true);
									ok.setVisible(false);
									annuller.setVisible(false);
									
//									nameBox.setEnabled(false);
//									nameBox.setText(dtoList.get(i-1).getReceptNavn());
//									receptIDBox.setEnabled(false);
//									receptIDBox.setText(dtoList.get(i-1).getRecept_id()+"");
//									type[index].setEnabled(false);
//									type[index].setText(list.get(index).getRaavare_id()+"");
//									maengde[index].setEnabled(false);
//									maengde[index].setText(list.get(index).getMængde()+"");
//									tol[index].setEnabled(false);
//									tol[index].setText(list.get(index).getTolerance()+"");
									
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

