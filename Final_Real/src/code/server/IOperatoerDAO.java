package code.server;

import java.util.ArrayList;

import code.shared.DALException;
import code.shared.OperatoerDTO;

public interface IOperatoerDAO {

	OperatoerDTO getOperatoer(int name) throws DALException;
	void skiftPassword(int oprID, String nyPassword) throws DALException;
	void opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws DALException;
	ArrayList<OperatoerDTO> getOperatoerer() throws DALException;
	void redigerBruger(int opr_id, int old_id, String opr_navn, String ini, String cpr, String password, String type) throws DALException;
}
