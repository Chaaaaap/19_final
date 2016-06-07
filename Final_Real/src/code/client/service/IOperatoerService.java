package code.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.OperatoerDTO;

@RemoteServiceRelativePath("19_Final")
public interface IOperatoerService extends RemoteService{

	OperatoerDTO login(int oprID, String password) throws Exception;
	OperatoerDTO skiftPassword(OperatoerDTO opr, String nyPassword) throws Exception;
	OperatoerDTO opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws Exception;
}
