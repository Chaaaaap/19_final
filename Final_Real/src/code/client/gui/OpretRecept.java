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

		lbl.setVisible(false);
		lbl1.setVisible(false);
		maengdeBox.setVisible(false);
		tolBox.setVisible(false);
		lbl2.setVisible(false);
		lbl3.setVisible(false);
		maengdeBox1.setVisible(false);
		tolBox1.setVisible(false);
		lbl4.setVisible(false);
		lbl5.setVisible(false);
		maengdeBox2.setVisible(false);
		tolBox2.setVisible(false);

		boxType.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = boxType.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					lbl.setVisible(false);
					lbl1.setVisible(false);
					maengdeBox.setVisible(false);
					tolBox.setVisible(false);
				}else{
					lbl.setVisible(true);
					lbl1.setVisible(true);
					maengdeBox.setVisible(true);
					tolBox.setVisible(true);
				}
			}
		});
		
		boxType1.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = boxType.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					lbl2.setVisible(false);
					lbl3.setVisible(false);
					maengdeBox1.setVisible(false);
					tolBox1.setVisible(false);
				}else{
					lbl2.setVisible(true);
					lbl3.setVisible(true);
					maengdeBox1.setVisible(true);
					tolBox1.setVisible(true);
				}
			}
		});
		
		boxType2.addChangeHandler(new ChangeHandler() {

			@Override
			public void onChange(ChangeEvent event) {
				// Get the index of the selected item
				int itemSelected = boxType.getSelectedIndex();

				// Get the string value of the item that has been selected
				String itemStringSelected = boxType.getValue(itemSelected);

				if(itemStringSelected.equals("blank")){
					lbl4.setVisible(false);
					lbl5.setVisible(false);
					maengdeBox2.setVisible(false);
					tolBox2.setVisible(false);
				}else{
					lbl4.setVisible(true);
					lbl5.setVisible(true);
					maengdeBox2.setVisible(true);
					tolBox2.setVisible(true);
				}
			}
		});
	}
	
	
	
	

	@UiField TextBox boxID;
	@UiField TextBox boxNavn;

	@UiField ListBox boxType;
	@UiField Label lbl;
	@UiField Label lbl1;
	@UiField TextBox maengdeBox;
	@UiField TextBox tolBox;

	@UiField ListBox boxType1;
	@UiField Label lbl2;
	@UiField Label lbl3;
	@UiField TextBox maengdeBox1;
	@UiField TextBox tolBox1;

	@UiField ListBox boxType2;
	@UiField Label lbl4;
	@UiField Label lbl5;
	@UiField TextBox maengdeBox2;
	@UiField TextBox tolBox2;


}
