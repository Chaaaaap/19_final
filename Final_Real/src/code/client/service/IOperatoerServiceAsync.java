package code.client.service;

import java.util.ArrayList;
import com.google.gwt.user.client.rpc.AsyncCallback;
import code.shared.OperatoerDTO;

public interface IOperatoerServiceAsync {

	
	void getOperatoerer(AsyncCallback<ArrayList<OperatoerDTO>> asyncCallback);
	void login(int oprID, String password, AsyncCallback<OperatoerDTO> callback);
	void skiftPassword(OperatoerDTO opr, String nyPassword, AsyncCallback<OperatoerDTO> callback);
	void opretBruger(int oprID, String navn, String ini, String CPR, String password, String type, AsyncCallback<OperatoerDTO> callback);
	void redigerBruger(int oprID, int old_id, String navn, String ini, String CPR, String password, String type, AsyncCallback<Void> asyncCallback);
	void deaktiverBruger(int oprID, int aktiv, AsyncCallback<Void> asyncCallback);
	void aktiverBruger(int oprID, int aktiv, AsyncCallback<Void> asyncCallback);
}
