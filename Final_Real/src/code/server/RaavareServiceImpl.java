package code.server;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IRaavareService;
import code.shared.RaavareDTO;

@SuppressWarnings("serial")
public class RaavareServiceImpl extends RemoteServiceServlet implements IRaavareService {

	public RaavareServiceImpl() {
		
	}
	RaavareDAO rvDAO = new RaavareDAO();
	@Override
	public ArrayList<RaavareDTO> getRaavarer() throws Exception {
		try{
			return rvDAO.getRaavarer();
		}catch(Exception e){
			throw e;
		}
	}

}
