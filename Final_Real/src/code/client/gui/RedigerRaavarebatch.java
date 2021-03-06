//package code.client.gui;
//
//import java.util.ArrayList;
//
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.event.dom.client.ClickEvent;
//import com.google.gwt.event.dom.client.ClickHandler;
//import com.google.gwt.uibinder.client.UiBinder;
//import com.google.gwt.uibinder.client.UiField;
//import com.google.gwt.user.client.Window;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.ui.Button;
//import com.google.gwt.user.client.ui.Composite;
//import com.google.gwt.user.client.ui.HorizontalPanel;
//import com.google.gwt.user.client.ui.Label;
//import com.google.gwt.user.client.ui.TextBox;
//import com.google.gwt.user.client.ui.VerticalPanel;
//import com.google.gwt.user.client.ui.Widget;
//
//import code.client.Final_Real;
//import code.client.service.IRaavareBatchService;
//import code.client.service.IRaavareBatchServiceAsync;
//import code.shared.RaavareBatchDTO;
////
//
//public class RedigerRaavarebatch extends Composite 
//{
//
//	private static RedigerRaavarebatchUiBinder uiBinder = GWT.create(RedigerRaavarebatchUiBinder.class);
//
//	interface RedigerRaavarebatchUiBinder extends UiBinder<Widget, RedigerRaavarebatch> 
//	{
//
//	}
//
//	private IRaavareBatchServiceAsync service;
//
//
//	public RedigerRaavarebatch() 
//	{
//		initWidget(uiBinder.createAndBindUi(this));
//		Final_Real.clearContent();
//		Final_Real.attachContent(this);
//		service = GWT.create(IRaavareBatchService.class);
//		redigerRaavareBatch();
//	}
//
//	@UiField Label raavareBatchIdLabel;
//	@UiField Label raavareIdLabel;
//	@UiField Label mængdeLabel;
//
//
//	private void redigerRaavareBatch()
//	{
//		Final_Real.clearContent();
//		Final_Real.attachContent(this);
//
//		service.getRaavareBatch(new AsyncCallback<ArrayList<RaavareBatchDTO>>() 
//		{
//
//			@Override
//			public void onFailure(Throwable caught) 
//			{
//				Window.alert(caught.getMessage());
//			}
//
//			
//			
//			@Override
//			public void onSuccess(ArrayList<RaavareBatchDTO> result) 
//			{
//				Window.alert("hej1");
//
//				VerticalPanel vPanel = new VerticalPanel();
//				raavareBatchIdLabel.setText("RåvareBatch ID");
//				raavareIdLabel.setText("Råvare ID");
//				mængdeLabel.setText("Mængde");
//				
//				Window.alert("hej2");
//
//				if(result!=null && !result.isEmpty()) 
//				{
//					Window.alert("hej3");
//					
//					for (RaavareBatchDTO raavareBatch : result) 
//					{
//						final RaavareBatchDTO rv = raavareBatch;
//						final HorizontalPanel hPanel = new HorizontalPanel();
//						
//						final TextBox raavareBatchID = new TextBox();
//						final TextBox raavareId = new TextBox();
//						final TextBox mængde = new TextBox();
//						
//						final Button rediger = new Button("Rediger");
//						final Button gem = new Button("Gem");
//						final Button annuller = new Button("Annuller");
//
//						raavareBatchID.setText(raavareBatch.getRaavareBatch_id()+"");
//						raavareId.setText(raavareBatch.getRaavare_id()+"");
//						mængde.setText(raavareBatch.getMængde()+"");
//
//						rediger.setStyleName("style.Rediger");
//						gem.setStyleName("style.Rediger");
//						annuller.setStyleName("style.Rediger");
//
//						gem.setVisible(false);
//						annuller.setVisible(false);
//
//						raavareBatchID.setEnabled(false);
//						raavareId.setEnabled(false);
//						mængde.setEnabled(false);
//
//						hPanel.add(raavareBatchID);
//						hPanel.add(raavareId);
//						hPanel.add(mængde);
//						
//						hPanel.add(rediger);
//						hPanel.add(gem);
//						hPanel.add(annuller);
//						vPanel.add(hPanel);
//
//						rediger.addClickHandler(new ClickHandler() 
//						{
//
//							@Override
//							public void onClick(ClickEvent event) 
//							{
//								raavareBatchID.setEnabled(true);
//								raavareId.setEnabled(true);
//								mængde.setEnabled(true);
//
//								rediger.setVisible(false);
//								gem.setVisible(true);
//								annuller.setVisible(true);								
//							}
//
//						});
//
//						gem.addClickHandler(new ClickHandler() 
//						{
//
//							@Override
//							public void onClick(ClickEvent event) 
//							{
//								service.redigerRaavareBatch(Integer.parseInt(raavareBatchID.getText()),
//										Integer.parseInt(raavareId.getText()), 
//										Integer.parseInt(mængde.getText()), 
//										Integer.parseInt(rv.getRaavareBatch_id()+""), 
//										new AsyncCallback<Void>() 
//								{
//
//									@Override
//									public void onFailure(Throwable caught) 
//									{
//										Window.alert(caught.getMessage()+"hej");
//									}
//
//									@Override
//									public void onSuccess(Void result) 
//									{
//										raavareBatchID.setEnabled(false);
//										raavareId.setEnabled(false);
//										mængde.setEnabled(false);
//
//										rediger.setVisible(true);
//										gem.setVisible(false);
//										annuller.setVisible(false);
//									}
//
//								});
//							}
//
//						});
//
//						annuller.addClickHandler(new ClickHandler() 
//						{
//
//							@Override
//							public void onClick(ClickEvent event) 
//							{
//								raavareBatchID.setText(rv.getRaavareBatch_id()+"");
//								raavareId.setText(rv.getRaavare_id()+"");
//								mængde.setText(rv.getMængde()+"");
//
//								raavareBatchID.setEnabled(false);
//								raavareId.setEnabled(false);
//								mængde.setEnabled(false);
//
//								gem.setVisible(false);
//								annuller.setVisible(false);
//								rediger.setVisible(true);
//							}
//
//						});
//					}
//				}
//
//				Final_Real.attachContent(vPanel);
//			}
//
//		});
//	}
//}