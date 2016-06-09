package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.OperatoerDTO;

@RemoteServiceRelativePath("19_Final")
public interface IOperatoerService extends RemoteService{

	ArrayList<OperatoerDTO> getOperatoerer() throws Exception;
	OperatoerDTO login(int oprID, String password) throws Exception;
	OperatoerDTO skiftPassword(OperatoerDTO opr, String nyPassword) throws Exception;
	OperatoerDTO opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws Exception;
	void redigerBruger(int oprID, int old_id, String navn, String ini, String CPR, String password) throws Exception;
}
