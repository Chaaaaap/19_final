package code.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IOperatoerService;
import code.shared.OperatoerDTO;

@SuppressWarnings("serial")
public class OperatoerServiceImpl extends RemoteServiceServlet implements IOperatoerService {

	private OperatoerDAO oprDAO = new OperatoerDAO();

	@Override
	public OperatoerDTO login(int name, String password) throws Exception {
		OperatoerDTO opr = oprDAO.getOperatoer(name);
		if(opr!=null && opr.getPassword().equals(password)) {
			return opr;
		}
		throw new Exception("Enten findes brugeren ikke, ellers er koden forkert");

	}

	@Override
	public OperatoerDTO skiftPassword(OperatoerDTO opr, String nyPassword) throws Exception {
		oprDAO.skiftPassword(opr.getOprID(), nyPassword);
		return opr;
	}

	@Override
	public OperatoerDTO opretBruger(int oprID, String navn, String ini, String CPR, String password, String type) throws Exception {
		oprDAO.opretBruger(oprID, navn, ini, CPR, password, type);
		return null;
	}

	@Override
	public OperatoerDTO getOperatoerer() {
		
		return null;
	}
	
	

}
