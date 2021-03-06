package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
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
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IRaavareService.class);
		visRaavarer();
	}
	
	@UiField Label idLabel;
	@UiField Label navnLabel;
	@UiField Label levLabel;
	
	private void visRaavarer() {
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
						HorizontalPanel hPanel = new HorizontalPanel();
						Label id = new Label(raavare.getRaavare_id()+"");
						Label navn = new Label(raavare.getRaavare_navn());
						Label lev = new Label(raavare.getLeverandør());
						hPanel.add(id);
						hPanel.add(navn);
						hPanel.add(lev);
						vPanel.add(hPanel);
					}
				}

				Final_Real.attachContent(vPanel);
			}
			
		});
	}
}