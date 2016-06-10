package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;
import com.sun.org.apache.xalan.internal.xsltc.compiler.sym;

import code.client.service.VsConClient;
import code.shared.OperatoerDTO;

public class Afvejning extends Composite {

	private OperatoerDTO dto;
	private static AfvejningUiBinder uiBinder = GWT.create(AfvejningUiBinder.class);
	private VsConClient vsclient;

	interface AfvejningUiBinder extends UiBinder<Widget, Afvejning> {
	}

	public Afvejning(OperatoerDTO oprDTO) {
		initWidget(uiBinder.createAndBindUi(this));
		this.vsclient = new VsConClient(GWT.getHostPageBaseURL()+"19_Final_afvejning");
		this.dto = oprDTO;
		labelIP.setVisible(false);
		textIP.setVisible(false);
		okIP.setVisible(false);
	}

	@UiField Label label;
	@UiField TextBox textbox;
	@UiField Button OK;
	@UiField Label labelIP;
	@UiField TextBox textIP;
	@UiField Button okIP;



	@UiHandler("OK")
	void onClick(ClickEvent e) {
		
		Window.alert("Mr MUFFINMAN");
		
		if(Integer.parseInt(textbox.getText()) == dto.getOprID()){

			label.setVisible(false);
			textbox.setVisible(false);
			OK.setVisible(false);
			labelIP.setVisible(true);
			textIP.setVisible(true);
			okIP.setVisible(true);

		}
	}

	@UiHandler("okIP")
	void onClickIP(ClickEvent e) {
		labelIP.setVisible(false);
		textIP.setVisible(false);
		okIP.setVisible(false);

		vsclient.VaegtConnect();
		
	}

}
