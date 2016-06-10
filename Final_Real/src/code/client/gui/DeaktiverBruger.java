package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
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
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IOperatoerService;
import code.client.service.IOperatoerServiceAsync;
import code.shared.OperatoerDTO;

public class DeaktiverBruger extends Composite {

	private static DeaktiverBrugerUiBinder uiBinder = GWT.create(DeaktiverBrugerUiBinder.class);

	interface DeaktiverBrugerUiBinder extends UiBinder<Widget, DeaktiverBruger> {
	}
	private IOperatoerServiceAsync service;
	private OperatoerDTO opr;

	public DeaktiverBruger() {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IOperatoerService.class);
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		deaktiverBruger();
	}

	@UiField Label oprID;
	@UiField Label oprNavn;
	@UiField Label ini;
	@UiField Label cpr;
	@UiField Label aktiv;

	private void deaktiverBruger() {
		Final_Real.clearContent();
		Final_Real.attachContent(this);

		service.getOperatoerer(new AsyncCallback<ArrayList<OperatoerDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(ArrayList<OperatoerDTO> result) {
				VerticalPanel vPanel = new VerticalPanel();
				oprID.setText("Operatør ID");
				oprNavn.setText("Operatør navn");
				ini.setText("Inititaler");
				cpr.setText("CPR-nummer");
				aktiv.setText("Aktiv");

				if(!result.isEmpty() ) {
					for (OperatoerDTO operatoer : result) {
						if(operatoer.getOprID() != 1) {
							final OperatoerDTO opr = operatoer;
							final HorizontalPanel hPanel = new HorizontalPanel();
							final Label id = new Label();
							final Label navn = new Label();
							final Label ini = new Label();
							final Label cpr = new Label();
							final Label status = new Label();
							final Button deaktiver = new Button("Deaktiver");
							final Button ok = new Button("OK");
							final Button annuller = new Button("Annuller");
							final Button aktiver = new Button("Aktiver");

							if(opr.getStatus() == 1) {
								id.setText(operatoer.getOprID()+"");
								navn.setText(operatoer.getOprNavn());
								ini.setText(operatoer.getIni()+"");
								cpr.setText(operatoer.getCPR()+"");
								status.setText("Ja");
							}else {
								id.setText(operatoer.getOprID()+"");
								navn.setText(operatoer.getOprNavn());
								ini.setText(operatoer.getIni()+"");
								cpr.setText(operatoer.getCPR()+"");
								status.setText("Nej");	
							}

							deaktiver.setStyleName("style.Rediger");
							ok.setStyleName("style.Rediger");
							annuller.setStyleName("style.Rediger");
							aktiver.setStyleName("style.Rediger");

							if(opr.getStatus() == 1) {
								ok.setVisible(false);
								annuller.setVisible(false);
								deaktiver.setVisible(true);
								aktiver.setVisible(false);
							}else{
								ok.setVisible(false);
								annuller.setVisible(false);
								deaktiver.setVisible(false);
								aktiver.setVisible(true);
							}

							hPanel.add(id);
							hPanel.add(navn);
							hPanel.add(ini);
							hPanel.add(cpr);
							hPanel.add(status);

							hPanel.add(deaktiver);
							hPanel.add(ok);
							hPanel.add(annuller);
							hPanel.add(aktiver);
							vPanel.add(hPanel);

							deaktiver.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {

									if(opr.getOprID() == 1) {
										Window.alert("Du kan ikke deaktivere Sys-Admin!!");
									}else {
										deaktiver.setVisible(false);
										ok.setVisible(true);
										annuller.setVisible(true);	
									}
								}

							});

							ok.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									service.deaktiverBruger(opr.getOprID(), opr.getStatus(),
											new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert(caught.getMessage());
										}

										@Override
										public void onSuccess(Void result) {

											deaktiverBruger();
											ok.setVisible(false);
											annuller.setVisible(false);
											deaktiver.setVisible(false);
											aktiver.setVisible(true);
										}
									});
								}
							});

							annuller.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									ok.setVisible(false);
									annuller.setVisible(false);
									deaktiver.setVisible(true);
								}
							});

							aktiver.addClickHandler(new ClickHandler() {

								@Override
								public void onClick(ClickEvent event) {
									service.aktiverBruger(opr.getOprID(), opr.getStatus(),
											new AsyncCallback<Void>() {

										@Override
										public void onFailure(Throwable caught) {
											Window.alert(caught.getMessage());
										}

										@Override
										public void onSuccess(Void result) {

											deaktiverBruger();
											ok.setVisible(false);
											annuller.setVisible(false);
											deaktiver.setVisible(true);
											aktiver.setVisible(false);

										}
									});
								}
							});
						}
					}
				}

				Final_Real.attachContent(vPanel);

			}
		});
	}
}
