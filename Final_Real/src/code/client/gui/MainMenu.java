package code.client.gui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.Widget;

import code.client.Final_Real;
import code.client.service.Client;
import code.shared.OperatoerDTO;
import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class MainMenu extends Composite {

	private static MainMenuUiBinder uiBinder = GWT.create(MainMenuUiBinder.class);

	interface MainMenuUiBinder extends UiBinder<Widget, MainMenu> {
	}

	private MainMenu menu = this;
	private OperatoerDTO opr;
	private Client client;
	public MainMenu(OperatoerDTO opr, Client client) {
		initWidget(uiBinder.createAndBindUi(this));
		
		this.opr = opr;
		this.client = client;
		
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
		
		opretBruger.setScheduledCommand(new LauchCommand(new OpretBruger()));
		redigerBruger.setScheduledCommand(new LauchCommand(new RedigerBruger()));
		deaktiverBruger.setScheduledCommand(new LauchCommand(new DeaktiverBruger()));
		opretRecept.setScheduledCommand(new LauchCommand(new OpretRecept()));		
		redigerRecept.setScheduledCommand(new LauchCommand(new RedigerRecept()));		
		visRecepter.setScheduledCommand(new LauchCommand(new VisRecepter()));		
		opretRaavare.setScheduledCommand(new LauchCommand(new OpretRaavare()));		
		redigerRaavare.setScheduledCommand(new LauchCommand(new RedigerRaavare()));		
		visRaavarer.setScheduledCommand(new LauchCommand(new VisRaavarer()));		
		opretRaavarebatch.setScheduledCommand(new LauchCommand(new OpretRaavarebatch()));		
		redigerRaavarebatch.setScheduledCommand(new LauchCommand(new RedigerRaavarebatch()));		
		visRaavarebatches.setScheduledCommand(new LauchCommand(new VisRaavarebatches()));		
		opretProduktbatch.setScheduledCommand(new LauchCommand(new OpretProduktbatch()));		
		redigerProduktbatch.setScheduledCommand(new LauchCommand(new RedigerProduktbatch()));		
		visProduktbatches.setScheduledCommand(new LauchCommand(new VisProduktbatches()));
	}
	
	

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
	
	private void opretBruger() {
		
	}
	private class LauchCommand implements Command{

		Composite c;
		
		public LauchCommand(Composite c) {
			super();
			this.c = c;
		}

		@Override
		public void execute() {
			Final_Real.clearContent();
			Final_Real.attachContent(menu);
			Final_Real.attachContent(c);
			
		}
		
	}
	
}
