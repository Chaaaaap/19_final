package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
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
import code.client.service.IOperatoerService;
import code.client.service.IOperatoerServiceAsync;
import code.shared.OperatoerDTO;
import code.shared.validate.Validator;

public class RedigerBruger extends Composite {

	private static RedigerBrugerUiBinder uiBinder = GWT.create(RedigerBrugerUiBinder.class);

	interface RedigerBrugerUiBinder extends UiBinder<Widget, RedigerBruger> {
	}

	private IOperatoerServiceAsync service;
	private Validator validator = new Validator();
	//	private Label errorLabel = new Label();

	public RedigerBruger() {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IOperatoerService.class);
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		redigerBruger();

	}
	@UiField Label oprID;
	@UiField Label oprNavn;
	@UiField Label ini;
	@UiField Label cpr;
	@UiField Label password;
	@UiField Label type;
	@UiField Label errorLabel;

	private void redigerBruger() {
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
				password.setText("Password");
				type.setText("Type");

				if(!result.isEmpty()) {
					for (OperatoerDTO operatoer : result) {
						final OperatoerDTO opr = operatoer;
						final HorizontalPanel hPanel = new HorizontalPanel();
						final TextBox id = new TextBox();
						final TextBox navn = new TextBox();
						final TextBox ini = new TextBox();
						final TextBox cpr = new TextBox();
						final TextBox passwordBox = new TextBox();
						final ListBox lbType = new ListBox();
						final Button rediger = new Button("Rediger");
						final Button gem = new Button("Gem");
						final Button annuller = new Button("Annuller");

						lbType.setHeight("23px");

						id.setText(operatoer.getOprID()+"");
						navn.setText(operatoer.getOprNavn());
						ini.setText(operatoer.getIni()+"");
						cpr.setText(operatoer.getCPR()+"");
						passwordBox.setText(operatoer.getPassword()+"");
						lbType.addItem(operatoer.getType(), "type");

						
						if(operatoer.getOprID() == 1) {
						}else if(operatoer.getType().equals("administrator")) {
							lbType.addItem("værkfører", "type");
							lbType.addItem("farmaceut", "type");
							lbType.addItem("operatør", "type");
						}else if (operatoer.getType().equals("operatør")) {
							lbType.addItem("værkfører", "type");
							lbType.addItem("farmaceut", "type");
							lbType.addItem("administrator", "type");
						}else if (operatoer.getType().equals("farmaceut")) {
							lbType.addItem("operatør", "type");
							lbType.addItem("værkfører", "type");
							lbType.addItem("administrator", "type");
						}else if (operatoer.getType().equals("værkfører")) {
							lbType.addItem("operatør", "type");
							lbType.addItem("farmaceut", "type");
							lbType.addItem("administrator", "type");
						}


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
						lbType.setEnabled(false);

						hPanel.add(id);
						hPanel.add(navn);
						hPanel.add(ini);
						hPanel.add(cpr);
						hPanel.add(passwordBox);
						hPanel.add(lbType);

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
									lbType.setEnabled(true);

									rediger.setVisible(false);
									gem.setVisible(true);
									annuller.setVisible(true);	
								
							}

						});

						gem.addClickHandler(new ClickHandler() {

							@Override
							public void onClick(ClickEvent event) {
								service.redigerBruger(Integer.parseInt(id.getText()), opr.getOprID(),
										navn.getText(), ini.getText(), cpr.getText(), passwordBox.getText(),
										lbType.getSelectedItemText(), new AsyncCallback<Void>() {

									@Override
									public void onFailure(Throwable caught) {
										Window.alert(caught.getMessage());
									}

									@Override
									public void onSuccess(Void result) {
										id.setEnabled(false);
										navn.setEnabled(false);
										ini.setEnabled(false);
										cpr.setEnabled(false);
										passwordBox.setEnabled(false);
										lbType.setEnabled(false);
										gem.setVisible(false);
										annuller.setVisible(false);
										rediger.setVisible(true);


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
								lbType.setEnabled(false);

								gem.setVisible(false);
								annuller.setVisible(false);
								rediger.setVisible(true);
							}

						});

						id.addKeyUpHandler(new KeyUpHandler() {

							@Override
							public void onKeyUp(KeyUpEvent event) {
								if(validator.validateInt(id.getText())) {
									errorLabel.setText("");
									gem.setEnabled(true);
								} else {
									gem.setEnabled(false);
									errorLabel.setText("Operatør ID skal være et heltal");
								}
							}
						});
						cpr.addKeyUpHandler(new KeyUpHandler() {

							@Override
							public void onKeyUp(KeyUpEvent event) {
								if(validator.validateCPR(cpr.getText())) {
									errorLabel.setText("");
									gem.setEnabled(true);
								} else {
									errorLabel.setText("CPR nummer skal skrive ddmmååxxxx");
									gem.setEnabled(false);
								}
							}
						});

						passwordBox.addKeyUpHandler(new KeyUpHandler() {

							@Override
							public void onKeyUp(KeyUpEvent event) {
								if(validator.validatePassword(passwordBox.getText())) {
									errorLabel.setText("");
									gem.setEnabled(true);
								} else {
									errorLabel.setText("Passwordet er ikke godt nok");
									gem.setEnabled(false);
								}
							}
						});
					}


					vPanel.add(errorLabel);
				}

				Final_Real.attachContent(vPanel);

			}


		});

	}
}
