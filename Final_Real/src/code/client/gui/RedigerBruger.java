package code.client.gui;

import java.util.List;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.IOperatoerService;
import code.client.service.IOperatoerServiceAsync;
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.OperatoerDTO;

public class RedigerBruger extends Composite {

	private static RedigerBrugerUiBinder uiBinder = GWT.create(RedigerBrugerUiBinder.class);

	interface RedigerBrugerUiBinder extends UiBinder<Widget, RedigerBruger> {
	}
	
	private IOperatoerServiceAsync service;

	public RedigerBruger() {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IOperatoerService.class);
	}
	
//	public void getOperatoerer() {
//		service.getOperatoerer(new AsyncCallback<List<OperatoerDTO>>() {
//
//			@Override
//			public void onFailure(Throwable caught) {
//				Window.alert(caught.getMessage());
//			}
//
//			@Override
//			public void onSuccess(List<OperatoerDTO> result) {
//				if(!result.isEmpty()) {
//					for (int i = 0; i <= result.size(); i++) {
//						if(i == 0)  {
//							HorizontalPanel hPanel = new HorizontalPanel();
//							Label labelID = new Label("Operatør ID");
//							labelID.setWidth("187px");
//							Label labelNavn = new Label("Operatør navn");
//							labelNavn.setWidth("187px");
//							Label labelIni = new Label("Initialer");
//							labelIni.setWidth("187px");
//							Label labelCpr = new Label("CPR-nummer");
//							labelCpr.setWidth("187px");
//							Label labelPassword = new Label("Password");
//							labelPassword.setWidth("187px");
//
//							hPanel.add(labelID);
//							hPanel.add(labelNavn);
//							hPanel.add(labelIni);
//							hPanel.add(labelCpr);
//							hPanel.add(labelPassword);
//
//							hPanel.getElement().setAttribute("align", "center");
//
//							CDIO3.attachContent(hPanel);
//						} else {
//							final OperatoerDTO opr = result.get(i-1);
//							HorizontalPanel hPanel = new HorizontalPanel();
//							final TextBox boxID = new TextBox();
//							boxID.setEnabled(false);
//							boxID.setText(opr.getOprID()+"");
//							hPanel.add(boxID);
//							
//							final TextBox boxNavn = new TextBox();
//							boxNavn.setEnabled(false);
//							boxNavn.setText(opr.getOprNavn());
//							hPanel.add(boxNavn);
//							
//							final TextBox boxIni = new TextBox();
//							boxIni.setEnabled(false);
//							boxIni.setText(opr.getIni());
//							hPanel.add(boxIni);
//							
//							final TextBox boxCpr = new TextBox();
//							boxCpr.setEnabled(false);
//							boxCpr.setText(result.get(i-1).getCpr());
//							hPanel.add(boxCpr);
//							
//							final PasswordTextBox boxPassword = new PasswordTextBox();
//							boxPassword.setEnabled(false);
//							boxPassword.setText(opr.getPassword());
//							hPanel.add(boxPassword);
//							
//							final Button rediger = new Button("Rediger");
//							hPanel.add(rediger);
//
//							final Button ok = new Button("Gem");
//							ok.setVisible(false);
//							hPanel.add(ok);
//							
//							final Button annuller = new Button("Annuller");
//							annuller.setVisible(false);
//							hPanel.add(annuller);	
//
//						}
//					}
//		
//				}
//			}
//			
//		});
//	}
}
