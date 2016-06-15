package code.server;

import code.shared.DALException;

public interface IVsConController {
	
	void aseRun() throws NumberFormatException, DALException;
	String modtagBesked();
	void login() throws Exception;
	void vaelgProduktbatch();
	void vaegtkontrol();
	void afslutning();

}
