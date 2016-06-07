package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ChangeEvent;
import com.google.gwt.event.dom.client.ChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.ListBox;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Widget;

public class OpretRecept extends Composite {

	private static OpretReceptUiBinder uiBinder = GWT.create(OpretReceptUiBinder.class);

	interface OpretReceptUiBinder extends UiBinder<Widget, OpretRecept> {
	}

	public OpretRecept() {
		initWidget(uiBinder.createAndBindUi(this));

		maengde_lbl.setVisible(false);
		tol_lbl.setVisible(false);
		maengdeBox.setVisible(false);
		tolBox.setVisible(false);
		
		maengde_lbl1.setVisible(false);
		tol_lbl1.setVisible(false);
		maengdeBox1.setVisible(false);
		tolBox1.setVisible(false);
		
		raavare_lbl2.setVisible(false);
		maengde_lbl2.setVisible(false);
		tol_lbl2.setVisible(false);
		maengdeBox2.setVisible(false);
		tolBox2.setVisible(false);
		
		raavare_lbl3.setVisible(false);
		boxType3.setVisible(false);
		maengde_lbl3.setVisible(false);
		tol_lbl3.setVisible(false);
		maengdeBox3.setVisible(false);
		tolBox3.setVisible(false);
		
		raavare_lbl4.setVisible(false);
		boxType4.setVisible(false);
		maengde_lbl4.setVisible(false);
		tol_lbl4.setVisible(false);
		maengdeBox4.setVisible(false);
		tolBox4.setVisible(false);
		
		boxType2.setVisible(false);
		boxType3.setVisible(false);
		boxType4.setVisible(false);

		boxType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = boxType.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					maengde_lbl.setVisible(false);
					tol_lbl.setVisible(false);
					maengdeBox.setVisible(false);
					tolBox.setVisible(false);
				}else{
					maengde_lbl.setVisible(true);
					tol_lbl.setVisible(true);
					maengdeBox.setVisible(true);
					tolBox.setVisible(true);
				}
			}
		});
		
		boxType1.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				boxType2.setVisible(true);
				// Get the index of the selected item
				int itemSelected = boxType1.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType1.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					boxType2.setVisible(false);
					raavare_lbl2.setVisible(false);
					maengde_lbl1.setVisible(false);
					tol_lbl1.setVisible(false);
					maengdeBox1.setVisible(false);
					tolBox1.setVisible(false);
				}else{
					raavare_lbl2.setVisible(true);
					maengde_lbl1.setVisible(true);
					tol_lbl1.setVisible(true);
					maengdeBox1.setVisible(true);
					tolBox1.setVisible(true);
				}
			}
		});
		
		boxType2.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				boxType3.setVisible(true);
				// Get the index of the selected item
				int itemSelected = boxType2.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType2.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					boxType3.setVisible(false);
					raavare_lbl3.setVisible(false);
					maengde_lbl2.setVisible(false);
					tol_lbl2.setVisible(false);
					maengdeBox2.setVisible(false);
					tolBox2.setVisible(false);
				}else{
					raavare_lbl3.setVisible(true);
					maengde_lbl2.setVisible(true);
					tol_lbl2.setVisible(true);
					maengdeBox2.setVisible(true);
					tolBox2.setVisible(true);
				}
			}
		});
		
		boxType3.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				boxType4.setVisible(true);
				// Get the index of the selected item
				int itemSelected = boxType3.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType3.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					boxType4.setVisible(false);
					raavare_lbl4.setVisible(false);
					maengde_lbl3.setVisible(false);
					tol_lbl3.setVisible(false);
					maengdeBox3.setVisible(false);
					tolBox3.setVisible(false);
				}else{
					raavare_lbl4.setVisible(true);
					maengde_lbl3.setVisible(true);
					tol_lbl3.setVisible(true);
					maengdeBox3.setVisible(true);
					tolBox3.setVisible(true);
				}
			}
		});
		
		boxType4.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = boxType4.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType4.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					maengde_lbl4.setVisible(false);
					tol_lbl4.setVisible(false);
					maengdeBox4.setVisible(false);
					tolBox4.setVisible(false);
				}else{
					maengde_lbl4.setVisible(true);
					tol_lbl4.setVisible(true);
					maengdeBox4.setVisible(true);
					tolBox4.setVisible(true);
				}
			}
		});
	}
	

	@UiField TextBox boxID;
	@UiField TextBox boxNavn;

	@UiField ListBox boxType;
	@UiField Label maengde_lbl;
	@UiField Label tol_lbl;
	@UiField TextBox maengdeBox;
	@UiField TextBox tolBox;

	@UiField ListBox boxType1;
	@UiField Label maengde_lbl1;
	@UiField Label tol_lbl1;
	@UiField TextBox maengdeBox1;
	@UiField TextBox tolBox1;

	@UiField ListBox boxType2;
	@UiField Label raavare_lbl2;
	@UiField Label maengde_lbl2;
	@UiField Label tol_lbl2;
	@UiField TextBox maengdeBox2;
	@UiField TextBox tolBox2;
	
	@UiField ListBox boxType3;
	@UiField Label raavare_lbl3;
	@UiField Label maengde_lbl3;
	@UiField Label tol_lbl3;
	@UiField TextBox maengdeBox3;
	@UiField TextBox tolBox3;
	
	@UiField ListBox boxType4;
	@UiField Label raavare_lbl4;
	@UiField Label maengde_lbl4;
	@UiField Label tol_lbl4;
	@UiField TextBox maengdeBox4;
	@UiField TextBox tolBox4;


}
