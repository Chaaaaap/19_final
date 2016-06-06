package code.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IService;
import code.shared.OperatoerDTO;

@SuppressWarnings("serial")
public class ServiceImpl extends RemoteServiceServlet implements IService {

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
	
	

}
