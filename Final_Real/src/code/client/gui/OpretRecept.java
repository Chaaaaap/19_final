package code.client.gui;

import java.util.ArrayList;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.IRaavareService;
import code.client.service.IRaavareServiceAsync;
import code.client.service.IReceptService;
import code.client.service.IReceptServiceAsync;
import code.shared.RaavareDTO;
import code.shared.ReceptKomponentDTO;

public class OpretRecept extends Composite {

	private static OpretReceptUiBinder uiBinder = GWT.create(OpretReceptUiBinder.class);

	interface OpretReceptUiBinder extends UiBinder<Widget, OpretRecept> {

	}

	private IReceptServiceAsync receptService;
	private IRaavareServiceAsync raavareService;
	private ListBox type[];
	private int length = 0;
	private TextBox[] maengde;
	private TextBox[] tol;

	public OpretRecept() {
		initWidget(uiBinder.createAndBindUi(this));

		type = new ListBox[5];
		maengde = new TextBox[5];
		tol = new TextBox[5];
		for (int i = 0; i < maengde.length; i++) {
			type[i] = new ListBox();
			type[i].addItem("", "blank");
			maengde[i] = new TextBox();
			tol[i] = new TextBox();
		}
		panelEt.insert(type[0], 1);
		panelEt.insert(maengde[0], 3);
		panelEt.insert(tol[0], 5);
		
		panelTo.insert(type[1], 1);
		panelTo.insert(maengde[1], 3);
		panelTo.insert(tol[1], 5);
		
		panelTre.insert(type[2], 1);
		panelTre.insert(maengde[2], 3);
		panelTre.insert(tol[2], 5);
		
		panelFire.insert(type[3], 1);
		panelFire.insert(maengde[3], 3);
		panelFire.insert(tol[3], 5);
		
		panelFem.insert(type[4], 1);
		panelFem.insert(maengde[4], 3);
		panelFem.insert(tol[4], 5);
		
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		raavareService = GWT.create(IRaavareService.class);
		receptService = GWT.create(IReceptService.class);
		
		opretRecept();
		
		

	}
	
	@UiField VerticalPanel panelEt;
	@UiField VerticalPanel panelTo;
	@UiField VerticalPanel panelTre;
	@UiField VerticalPanel panelFire;
	@UiField VerticalPanel panelFem;
	
	@UiField TextBox boxID;
	@UiField TextBox boxNavn;

	@UiField Label maengde_lbl;
	@UiField Label tol_lbl;

	@UiField Label maengde_lbl1;
	@UiField Label tol_lbl1;
	
	@UiField Label raavare_lbl2;
	@UiField Label maengde_lbl2;
	@UiField Label tol_lbl2;
	
	@UiField Label raavare_lbl3;
	@UiField Label maengde_lbl3;
	@UiField Label tol_lbl3;

	@UiField Label raavare_lbl4;
	@UiField Label maengde_lbl4;
	@UiField Label tol_lbl4;
	
	@UiField Button submit;
	
	@UiHandler("submit")
	void opretReceptKnap(ClickEvent e) {
		
		for (int i = 0; i < type.length; i++) {
			if(!type[i].getSelectedValue().equals("blank"))
				length++;
		}
		
		final ArrayList<ReceptKomponentDTO> komp = new ArrayList<ReceptKomponentDTO>();
		
		for (int i = 0; i < length; i++) {
			if(!type[i].getSelectedValue().equals("blank")) {
				komp.add(new ReceptKomponentDTO(Integer.parseInt(boxID.getText()), Integer.parseInt(type[i].getSelectedValue()),
						Integer.parseInt(maengde[i].getText()), Integer.parseInt(tol[i].getText())));
				
			}
		}
		
		receptService.addRecept(boxNavn.getText(), Integer.parseInt(boxID.getText()),
				komp, new AsyncCallback<Void>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());
			}

			@Override
			public void onSuccess(Void result) {
				Window.alert("Recepten er oprettet");
				boxNavn.setText("");
				boxID.setText("");
				for (int i = 0; i < komp.size(); i++) {
					type[i].setSelectedIndex(0);
					maengde[i].setText("");
					tol[i].setText("");
				}
			}

		});
	}

	


	private void opretRecept() {
		
		raavareService.getRaavarer(new AsyncCallback<ArrayList<RaavareDTO>>() {

			@Override
			public void onFailure(Throwable caught) {
				Window.alert(caught.getMessage());

			}

			//TODO alt her skal lige gåes igennem for at se om der bliver brugt de nyskabte arrays.
			@Override
			public void onSuccess(ArrayList<RaavareDTO> result) {
				for (RaavareDTO raavareDTO : result) {
					type[0].addItem(raavareDTO.getRaavare_navn()+" fra "+raavareDTO.getLeverandør(), raavareDTO.getRaavare_id()+"");
					type[1].addItem(raavareDTO.getRaavare_navn()+" fra "+raavareDTO.getLeverandør(), raavareDTO.getRaavare_id()+"");
					type[2].addItem(raavareDTO.getRaavare_navn()+" fra "+raavareDTO.getLeverandør(), raavareDTO.getRaavare_id()+"");
					type[3].addItem(raavareDTO.getRaavare_navn()+" fra "+raavareDTO.getLeverandør(), raavareDTO.getRaavare_id()+"");
					type[4].addItem(raavareDTO.getRaavare_navn()+" fra "+raavareDTO.getLeverandør(), raavareDTO.getRaavare_id()+"");
				}
				
				
			}

		});


		Final_Real.clearContent();
		Final_Real.attachContent(this);

		maengde_lbl.setVisible(false);
		tol_lbl.setVisible(false);
		maengde[0].setVisible(false);
		tol[0].setVisible(false);

		maengde_lbl1.setVisible(false);
		tol_lbl1.setVisible(false);
		maengde[1].setVisible(false);
		tol[1].setVisible(false);

		raavare_lbl2.setVisible(false);
		maengde_lbl2.setVisible(false);
		tol_lbl2.setVisible(false);
		maengde[2].setVisible(false);
		tol[2].setVisible(false);

		raavare_lbl3.setVisible(false);
		type[3].setVisible(false);
		maengde_lbl3.setVisible(false);
		tol_lbl3.setVisible(false);
		maengde[3].setVisible(false);
		tol[3].setVisible(false);

		raavare_lbl4.setVisible(false);
		type[4].setVisible(false);
		maengde_lbl4.setVisible(false);
		tol_lbl4.setVisible(false);
		maengde[4].setVisible(false);
		tol[4].setVisible(false);

		type[2].setVisible(false);
		type[3].setVisible(false);
		type[4].setVisible(false);


		type[0].addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = type[0].getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = type[0].getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					maengde_lbl.setVisible(false);
					tol_lbl.setVisible(false);
					maengde[0].setVisible(false);
					tol[0].setVisible(false);
				}else{
					maengde_lbl.setVisible(true);
					tol_lbl.setVisible(true);
					maengde[0].setVisible(true);
					tol[0].setVisible(true);
				}
			}
		});

		type[1].addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				type[2].setVisible(true);
				// Get the index of the selected item
				int itemSelected = type[1].getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = type[1].getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					type[2].setVisible(false);
					raavare_lbl2.setVisible(false);
					maengde_lbl1.setVisible(false);
					tol_lbl1.setVisible(false);
					maengde[1].setVisible(false);
					tol[1].setVisible(false);
				}else{
					raavare_lbl2.setVisible(true);
					maengde_lbl1.setVisible(true);
					tol_lbl1.setVisible(true);
					maengde[1].setVisible(true);
					tol[1].setVisible(true);
				}
			}
		});

		type[2].addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				type[3].setVisible(true);
				// Get the index of the selected item
				int itemSelected = type[2].getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = type[2].getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					type[3].setVisible(false);
					raavare_lbl3.setVisible(false);
					maengde_lbl2.setVisible(false);
					tol_lbl2.setVisible(false);
					maengde[2].setVisible(false);
					tol[2].setVisible(false);
				}else{
					raavare_lbl3.setVisible(true);
					maengde_lbl2.setVisible(true);
					tol_lbl2.setVisible(true);
					maengde[2].setVisible(true);
					tol[2].setVisible(true);
				}
			}
		});

		type[3].addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				type[4].setVisible(true);
				// Get the index of the selected item
				int itemSelected = type[3].getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = type[3].getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					type[4].setVisible(false);
					raavare_lbl4.setVisible(false);
					maengde_lbl3.setVisible(false);
					tol_lbl3.setVisible(false);
					maengde[3].setVisible(false);
					tol[3].setVisible(false);
				}else{
					raavare_lbl4.setVisible(true);
					maengde_lbl3.setVisible(true);
					tol_lbl3.setVisible(true);
					maengde[3].setVisible(true);
					tol[3].setVisible(true);
				}
			}
		});

		type[4].addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = type[4].getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = type[4].getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					maengde_lbl4.setVisible(false);
					tol_lbl4.setVisible(false);
					maengde[4].setVisible(false);
					tol[4].setVisible(false);
				}else{
					maengde_lbl4.setVisible(true);
					tol_lbl4.setVisible(true);
					maengde[4].setVisible(true);
					tol[4].setVisible(true);
				}
			}
		});
		
		
	}
}
