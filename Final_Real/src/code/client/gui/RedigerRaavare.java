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
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.RaavareDTO;

public class RedigerRaavare extends Composite {

	private static RedigerRaavareUiBinder uiBinder = GWT.create(RedigerRaavareUiBinder.class);

	interface RedigerRaavareUiBinder extends UiBinder<Widget, RedigerRaavare> {
	}
	
	private IRaavareServiceAsync service;

	public RedigerRaavare() {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IRaavareService.class);
	}
	
	@UiField Button visListe;
	@UiField Label idLabel;
	@UiField Label navnLabel;
	@UiField Label levLabel;
	
	@UiHandler("visListe")
	void visListe(ClickEvent e) {
		redigerRaavare();
	}
	
	private void redigerRaavare() {
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		
		service.getRaavarer(new AsyncCallback<ArrayList<RaavareDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<RaavareDTO> result) {
				VerticalPanel vPanel = new VerticalPanel();
				idLabel.setText("Råvare ID");
				navnLabel.setText("Råvare navn");
				levLabel.setText("Leverandør");
				
				if(!result.isEmpty()) {
					for (RaavareDTO raavare : result) {
						final RaavareDTO rv = raavare;
						final HorizontalPanel hPanel = new HorizontalPanel();
						final TextBox id = new TextBox();
						final TextBox navn = new TextBox();
						final TextBox lev = new TextBox();
						final Button rediger = new Button("Rediger");
						final Button gem = new Button("Gem");
						final Button annuller = new Button("Annuller");
						
						id.setText(raavare.getRaavare_id()+"");
						navn.setText(raavare.getRaavare_navn());
						lev.setText(raavare.getLeverandør());
						
						rediger.setStyleName("style.Rediger");
						gem.setStyleName("style.Rediger");
						annuller.setStyleName("style.Rediger");
						
						gem.setVisible(false);
						annuller.setVisible(false);
						
						id.setEnabled(false);
						navn.setEnabled(false);
						lev.setEnabled(false);
						
						hPanel.add(id);
						hPanel.add(navn);
						hPanel.add(lev);
						hPanel.add(rediger);
						hPanel.add(gem);
						hPanel.add(annuller);
						vPanel.add(hPanel);
						
						rediger.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								id.setEnabled(true);
								navn.setEnabled(true);
								lev.setEnabled(true);
								
								rediger.setVisible(false);
								gem.setVisible(true);
								annuller.setVisible(true);								
							}
							
						});
						
						gem.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								service.redigerRaavare(Integer.parseInt(id.getText()),
										navn.getText(), lev.getText(), 
										Integer.parseInt(rv.getRaavare_id()+""), 
										new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getMessage());
									}

									@Override
									public void onSuccess(Void result) {
										id.setEnabled(false);
										navn.setEnabled(false);
										lev.setEnabled(false);
										
										rediger.setVisible(true);
										gem.setVisible(false);
										annuller.setVisible(false);
									}
									
								});
							}
							
						});
						
						annuller.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								id.setText(rv.getRaavare_id()+"");
								navn.setText(rv.getRaavare_navn());
								lev.setText(rv.getLeverandør());
								
								id.setEnabled(false);
								navn.setEnabled(false);
								lev.setEnabled(false);
								
								gem.setVisible(false);
								annuller.setVisible(false);
								rediger.setVisible(true);
							}
							
						});
					}
				}

				Final_Real.attachContent(vPanel);
			
			}
			
		});
	}

}
