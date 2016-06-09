package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.OperatoerClient;
import code.shared.OperatoerDTO;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class MainMenu extends Composite {

	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);

	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	private MainMenu menu = this;
	private OperatoerDTO opr;
	private OperatoerClient client;
	public MainMenu(OperatoerDTO opr, OperatoerClient client) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.opr = opr;
		final OperatoerClient oprClient = client;
		this.client = oprClient;
		
		loggedIn.setWidth("300px");
		loggedIn.setText("Du er logget ind som: "+opr.getOprNavn());
		
		menuBar.setAutoOpen(true);
		menuBar.setAnimationEnabled(true);

		if(opr.getType().equals("operatør")) {
			brugerMenu.setVisible(false);
			receptMenu	.setVisible(false);
			raavareMenu.setVisible(false);
			raavarebatchMenu.setVisible(false);
			produktbatchMenu.setVisible(false);
	
		} else if(opr.getType().equals("værkfører")) {
			brugerMenu.setVisible(false);
			receptMenu.setVisible(false);
			raavareMenu.setVisible(false);
			
		} else if(opr.getType().equals("farmaceut")) {
			brugerMenu.setVisible(false);
			
		}
		
//		opretBruger.setScheduledCommand(new LaunchCommand(new OpretBruger(client)));
//		redigerBruger.setScheduledCommand(new LaunchCommand(new RedigerBruger()));
//		deaktiverBruger.setScheduledCommand(new LaunchCommand(new DeaktiverBruger()));
//		opretRecept.setScheduledCommand(new LaunchCommand(new OpretRecept()));		
//		redigerRecept.setScheduledCommand(new LaunchCommand(new RedigerRecept()));		
//		visRecepter.setScheduledCommand(new LaunchCommand(new VisRecepter()));		
//		opretRaavare.setScheduledCommand(new LaunchCommand(new OpretRaavare()));		
//		redigerRaavare.setScheduledCommand(new LaunchCommand(new RedigerRaavare()));		
//		visRaavarer.setScheduledCommand(new LaunchCommand(new VisRaavarer()));		
//		opretRaavarebatch.setScheduledCommand(new LaunchCommand(new OpretRaavarebatch()));		
//		redigerRaavarebatch.setScheduledCommand(new LaunchCommand(new RedigerRaavarebatch()));		
//		visRaavarebatches.setScheduledCommand(new LaunchCommand(new VisRaavarebatches()));		
//		opretProduktbatch.setScheduledCommand(new LaunchCommand(new OpretProduktbatch()));		
//		redigerProduktbatch.setScheduledCommand(new LaunchCommand(new RedigerProduktbatch()));		
//		visProduktbatches.setScheduledCommand(new LaunchCommand(new VisProduktbatches()));
		opretBruger.setScheduledCommand(new Command() {@Override public void execute() {new OpretBruger(oprClient); } });
		redigerBruger.setScheduledCommand(new Command() {@Override public void execute() {new RedigerBruger(); } });
		deaktiverBruger.setScheduledCommand(new Command() {@Override public void execute() {new DeaktiverBruger(); } });
		opretRecept.setScheduledCommand(new Command() {@Override public void execute() {new OpretRecept(); } });
		redigerRecept.setScheduledCommand(new Command() {@Override public void execute() {new RedigerRecept(); } });
		visRecepter.setScheduledCommand(new Command() {@Override public void execute() {new VisRecepter(); } });
		opretRaavare.setScheduledCommand(new Command() {@Override public void execute() {new OpretRaavare(); } });
		redigerRaavare.setScheduledCommand(new Command() {@Override public void execute() {new RedigerRaavare(); } });
		visRaavarer.setScheduledCommand(new Command() {@Override public void execute() {new VisRaavarer(); } });
		opretRaavarebatch.setScheduledCommand(new Command() {@Override public void execute() {new OpretRaavarebatch(); } });
		redigerRaavarebatch.setScheduledCommand(new Command() {@Override public void execute() {new RedigerRaavarebatch(); } });
		visRaavarebatches.setScheduledCommand(new Command() {@Override public void execute() {new VisRaavarebatches(); } });
		opretProduktbatch.setScheduledCommand(new Command() {@Override public void execute() {new OpretProduktbatch(); } });
		redigerProduktbatch.setScheduledCommand(new Command() {@Override public void execute() {new RedigerProduktbatch(); } });
		visProduktbatches.setScheduledCommand(new Command() {@Override public void execute() {new VisProduktbatches(); } });
	}
	
	@UiField Label loggedIn;

	@UiField Button afvej;
	@UiField Button skiftPassword;
	@UiField MenuBar menuBar;
	
	@UiField MenuItem brugerMenu;
	@UiField MenuItem opretBruger;
	@UiField MenuItem redigerBruger;
	@UiField MenuItem deaktiverBruger;
	
	@UiField MenuItem receptMenu;
	@UiField MenuItem opretRecept;
	@UiField MenuItem redigerRecept;
	@UiField MenuItem visRecepter;
	
	@UiField MenuItem raavareMenu;
	@UiField MenuItem opretRaavare;
	@UiField MenuItem redigerRaavare;
	@UiField MenuItem visRaavarer;
	
	@UiField MenuItem raavarebatchMenu;
	@UiField MenuItem opretRaavarebatch;
	@UiField MenuItem redigerRaavarebatch;
	@UiField MenuItem visRaavarebatches;
	
	@UiField MenuItem produktbatchMenu;
	@UiField MenuItem opretProduktbatch;
	@UiField MenuItem redigerProduktbatch;
	@UiField MenuItem visProduktbatches;
	
	
	@UiHandler("skiftPassword")
	void skiftPassword(ClickEvent e) {
		SkiftPassword skiftPassword = new SkiftPassword(opr, client);
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		Final_Real.attachContent(skiftPassword);
		
	}
	
	@UiHandler("afvej")
	void afvej(ClickEvent e) {
		Afvejning afveje = new Afvejning();
		Final_Real.clearContent();
		Final_Real.attachContent(this);
		Final_Real.attachContent(afveje);
	}
	

//	private class LaunchCommand implements Command{
//
//		Composite c;
//		
//		public LaunchCommand(Composite c) {
//			super();
//			this.c = c;
//		}
//
//		@Override
//		public void execute() {
//			Final_Real.clearContent();
//			Final_Real.attachContent(menu);
//			Final_Real.attachContent(c);
//			
//		}
//		
//	}
	
}
