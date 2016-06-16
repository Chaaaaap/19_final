package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IRaavareService;
import code.shared.DALException;
import code.shared.RaavareDTO;

@SuppressWarnings("serial")
public class RaavareServiceImpl extends RemoteServiceServlet implements IRaavareService {

	public RaavareServiceImpl() {
		
	}
	RaavareDAO rvDAO = new RaavareDAO();
	@Override
	public ArrayList<RaavareDTO> getRaavarer() throws Exception {
			return rvDAO.getRaavarer();

	}
	@Override
	public void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws DALException {
			rvDAO.addRaavare(raavare_id, raavare_navn, leverandør);
	}
	@Override
	public void redigerRaavare(int raavare_id, String raavare_navn, String leverandør, int glid) throws DALException {
		rvDAO.redigerRaavare(raavare_id, raavare_navn, leverandør, glid);
	}
}