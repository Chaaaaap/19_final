package code.server;

import code.shared.OperatoerDTO;

public interface IOperatoerDAO {

	OperatoerDTO getOperatoer(int name) throws Exception;
	void skiftPassword(int oprID, String nyPassword) throws Exception;
}
