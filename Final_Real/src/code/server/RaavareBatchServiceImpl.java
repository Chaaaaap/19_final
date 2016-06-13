package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IRaavareBatchService;
import code.shared.RaavareBatchDTO;

@SuppressWarnings("serial")
public class RaavareBatchServiceImpl extends RemoteServiceServlet implements IRaavareBatchService 
{

public RaavareBatchServiceImpl() {
		
	}
	RaavareBatchDAO rvDAO = new RaavareBatchDAO();
	@Override
	public ArrayList<RaavareBatchDTO> getRaavarerBatch() throws Exception {
			return rvDAO.getRaavareBatch();

	}
	@Override
	public void addRaavareBatch(int raavareBatch_id, int raavare_id, int mængde) throws Exception {
			rvDAO.addRaavareBatch( raavareBatch_id, raavare_id, mængde);
	}
	@Override
	public void redigerRaavareBatch(int raavareBatch_id, int raavare_id, int mængde, int glid) throws Exception {
		rvDAO.redigerRaavareBatch(raavareBatch_id, raavare_id, mængde, glid);
	}
	
}
