package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.DALException;
import code.shared.OperatoerDTO;

@RemoteServiceRelativePath("19_Final")
public interface IOperatoerService extends RemoteService{

	ArrayList<OperatoerDTO> getOperatoerer() throws DALException;
	OperatoerDTO login(int oprID, String password) throws DALException;
	OperatoerDTO skiftPassword(OperatoerDTO opr, String nyPassword) throws DALException;
	OperatoerDTO opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws DALException;
	void redigerBruger(int oprID, int old_id, String navn, String ini, String CPR, String password, String type) throws DALException;
	void deaktiverBruger(int oprID, int aktiv) throws DALException;
	void aktiverBruger(int oprID, int aktiv) throws DALException;

}
