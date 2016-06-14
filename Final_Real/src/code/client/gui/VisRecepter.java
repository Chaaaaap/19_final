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
import code.client.service.IReceptService;
import code.client.service.IReceptServiceAsync;
import code.shared.ReceptDTO;

public class VisRecepter extends Composite 
{

	private static VisRecepterUiBinder uiBinder = GWT.create(VisRecepterUiBinder.class);

	interface VisRecepterUiBinder extends UiBinder<Widget, VisRecepter> 
	{

	}

	private IReceptServiceAsync service;

	public VisRecepter() 
	{
		initWidget(uiBinder.createAndBindUi(this));
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service = GWT.create(IReceptService.class);
		visRecept();
	}

	@UiField Label receptNavnLabel;
	@UiField Label receptIdLabel;
	@UiField VerticalPanel vPanel;

	private void visRecept() 
	{
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		service.getRecept(new AsyncCallback<ArrayList<ReceptDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());	
			}

			@Override
			public void onSuccess(ArrayList<ReceptDTO> result) {
				for (ReceptDTO receptDTO : result) {
					HorizontalPanel hPanel = new HorizontalPanel();
					Label labelNavn = new Label(receptDTO.getReceptNavn());
					Label labelID = new Label(receptDTO.getRecept_id()+"");
					
					hPanel.add(labelNavn);
					hPanel.add(labelID);
					
					vPanel.add(hPanel);
					
				}
			}
		});
	}
}