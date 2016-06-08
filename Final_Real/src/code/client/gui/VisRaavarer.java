package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.shared.RaavareDTO;

public class VisRaavarer extends Composite {

	private static VisRaavarerUiBinder uiBinder = GWT.create(VisRaavarerUiBinder.class);

	interface VisRaavarerUiBinder extends UiBinder<Widget, VisRaavarer> {
	}
	private IRaavareServiceAsync service;

	public VisRaavarer() {
		initWidget(uiBinder.createAndBindUi(this));
		service = GWT.create(IRaavareService.class);
		
	}
	
	@UiField Button visListe;
	@UiField Label idLabel;
	@UiField Label navnLabel;
	@UiField Label levLabel;
	@UiHandler("visListe")
	void visListe(ClickEvent e) {
		visRaavarer();
	}
	private void visRaavarer() {
		service.getRaavarer(new AsyncCallback<ArrayList<RaavareDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage()+"\n\n\n FUCK");	
			}

			@Override
			public void onSuccess(ArrayList<RaavareDTO> result) {
				VerticalPanel vPanel = new VerticalPanel();
				idLabel.setText("Råvare ID");
				navnLabel.setText("Råvare navn");
				levLabel.setText("Leverandør");
				
				if(!result.isEmpty()) {
					for (RaavareDTO raavareDTO : result) {
						
					}
				}

				Final_Real.attachContent(vPanel);
			}
			
		});
	}
}