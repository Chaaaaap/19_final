package code.client.service;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.OperatoerDTO;

@RemoteServiceRelativePath("19_Final")
public interface IService extends RemoteService{

	OperatoerDTO login(int oprID, String password) throws Exception;
	OperatoerDTO skiftPassword(OperatoerDTO opr, String glPassword, String nyPassword);
}
