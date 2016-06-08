package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

import code.client.service.VsConClient;

public class Afvejning extends Composite {

	private static AfvejningUiBinder uiBinder = GWT.create(AfvejningUiBinder.class);
	private VsConClient vsclient;
	
	interface AfvejningUiBinder extends UiBinder<Widget, Afvejning> {
	}

	public Afvejning() {
		initWidget(uiBinder.createAndBindUi(this));
		this.vsclient = new VsConClient(GWT.getHostPageBaseURL()+"19_Final_afvejning");
	}

	@UiField
	Button afvej;

	@UiHandler("afvej")
	void onClick(ClickEvent e) {
		vsclient.Simulator();
	}
	
}
