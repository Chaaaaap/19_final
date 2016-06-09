package code.server;

import java.util.ArrayList;

import code.shared.OperatoerDTO;

public interface IOperatoerDAO {

	OperatoerDTO getOperatoer(int name) throws Exception;
	void skiftPassword(int oprID, String nyPassword) throws Exception;
	void opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws Exception;
	ArrayList<OperatoerDTO> getOperatoerer() throws Exception;
	void redigerBruger(int opr_id, int old_id, String opr_navn, String ini, String cpr, String password) throws Exception;
}
