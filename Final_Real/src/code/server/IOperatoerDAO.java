package code.server;

import code.shared.OperatoerDTO;

public interface IOperatoerDAO {

	OperatoerDTO getOperatoer(int name) throws Exception;
	void skiftPassword(int oprID, String nyPassword) throws Exception;
	void opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws Exception;
}
