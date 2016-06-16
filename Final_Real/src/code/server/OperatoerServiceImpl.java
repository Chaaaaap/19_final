package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IOperatoerService;
import code.shared.DALException;
import code.shared.OperatoerDTO;

@SuppressWarnings("serial")
public class OperatoerServiceImpl extends RemoteServiceServlet implements IOperatoerService {

	private OperatoerDAO oprDAO = new OperatoerDAO();

	@Override
	public OperatoerDTO login(int name, String password) throws DALException {
		OperatoerDTO opr = oprDAO.getOperatoer(name);
		if(opr!=null && opr.getPassword().equals(password)) {
			return opr;
		}
		throw new DALException("Enten findes brugeren ikke, ellers er koden forkert");

	}

	@Override
	public OperatoerDTO skiftPassword(OperatoerDTO opr, String nyPassword) throws DALException {
		oprDAO.skiftPassword(opr.getOprID(), nyPassword);
		return opr;
	}

	@Override
	public OperatoerDTO opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws DALException {
		oprDAO.opretBruger(oprID, navn, ini, CPR, password, type);
		return null;
	}


	@Override
	public ArrayList<OperatoerDTO> getOperatoerer() throws DALException {
		return oprDAO.getOperatoerer();
	}

	@Override
	public void redigerBruger(int oprID, int old_id, String navn, String ini, String cpr, String password, String type) throws DALException {
		oprDAO.redigerBruger(oprID, old_id, navn, ini, cpr, password, type);
	}

	@Override
	public void deaktiverBruger(int oprID, int aktiv) throws DALException {
		oprDAO.deaktiverBruger(oprID, aktiv);
		
	}

	@Override
	public void aktiverBruger(int oprID, int aktiv) throws DALException {
		oprDAO.aktiverBruger(oprID, aktiv);
		
	}
	
	

}
