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
import code.client.service.IOperatoerService;
import code.client.service.IOperatoerServiceAsync;
import code.shared.OperatoerDTO;

public class RedigerBruger extends Composite {

	private static RedigerBrugerUiBinder uiBinder = GWT.create(RedigerBrugerUiBinder.class);

	interface RedigerBrugerUiBinder extends UiBinder<Widget, RedigerBruger> {
	}
	
//	private IOperatoerServiceAsync service;

	public RedigerBruger() {
		initWidget(uiBinder.createAndBindUi(this));
//		service = GWT.create(IOperatoerService.class);
	}
	@UiField Button visListe;
	@UiField Label oprID;
	@UiField Label oprNavn;
	@UiField Label ini;
	@UiField Label cpr;
	@UiField Label password;
	
	@UiHandler("visListe")
	void visListe(ClickEvent e) {
		redigerBruger();
	}
	
	private void redigerBruger() {
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		
		service.getOperatoerer(new AsyncCallback<ArrayList<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
				Window.alert("LULZ");
			}

			@Override
			public void onSuccess(ArrayList<OperatoerDTO> result) {
				VerticalPanel vPanel = new VerticalPanel();
				oprID.setText("Operatør ID");
				oprNavn.setText("Operatør navn");
				ini.setText("Inititaler");
				cpr.setText("CPR-nummer");
				password.setText("Password");
				
				if(!result.isEmpty()) {
					for (OperatoerDTO operatoer : result) {
						final OperatoerDTO opr = operatoer;
						final HorizontalPanel hPanel = new HorizontalPanel();
						final TextBox id = new TextBox();
						final TextBox navn = new TextBox();
						final TextBox ini = new TextBox();
						final TextBox cpr = new TextBox();
						final TextBox passwordBox = new TextBox();
						final Button rediger = new Button("Rediger");
						final Button gem = new Button("Gem");
						final Button annuller = new Button("Annuller");
						
						id.setText(operatoer.getOprID()+"");
						navn.setText(operatoer.getOprNavn());
						ini.setText(operatoer.getIni()+"");
						cpr.setText(operatoer.getCPR()+"");
						passwordBox.setText(operatoer.getPassword()+"");
						
						rediger.setStyleName("style.Rediger");
						gem.setStyleName("style.Rediger");
						annuller.setStyleName("style.Rediger");
						
						gem.setVisible(false);
						annuller.setVisible(false);
						
						id.setEnabled(false);
						navn.setEnabled(false);
						ini.setEnabled(false);
						cpr.setEnabled(false);
						passwordBox.setEnabled(false);
						
						hPanel.add(id);
						hPanel.add(navn);
						hPanel.add(ini);
						hPanel.add(cpr);
						hPanel.add(passwordBox);
						
						hPanel.add(rediger);
						hPanel.add(gem);
						hPanel.add(annuller);
						vPanel.add(hPanel);
						
						rediger.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								id.setEnabled(true);
								navn.setEnabled(true);
								ini.setEnabled(true);
								cpr.setEnabled(true);
								passwordBox.setEnabled(true);
								
								rediger.setVisible(false);
								gem.setVisible(true);
								annuller.setVisible(true);								
							}
							
						});
						
						gem.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								service.redigerBruger(Integer.parseInt(id.getText()),
										navn.getText(), ini.getText(), cpr.getText(), passwordBox.getText(),
										new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getMessage());
									}

									@Override
									public void onSuccess(Void result) {
										// TODO noget meningsfuldt.
										Window.alert("LULZ");
									}
									
								});
							}
							
						});
						
						annuller.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								id.setText(opr.getOprID()+"");
								navn.setText(opr.getOprNavn());
								ini.setText(opr.getIni());
								cpr.setText(opr.getCPR());
								passwordBox.setText(opr.getPassword());
								
								id.setEnabled(false);
								navn.setEnabled(false);
								ini.setEnabled(false);
								cpr.setEnabled(false);
								passwordBox.setEnabled(false);
								
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
