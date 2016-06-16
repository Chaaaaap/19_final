package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IRaavareBatchService;
import code.shared.RaavareBatchDTO;

@SuppressWarnings("serial")
public class RaavareBatchServiceImpl extends RemoteServiceServlet implements IRaavareBatchService 
{

	public RaavareBatchServiceImpl() 
	{

	}
	
	RaavareBatchDAO raavareBatchDAO = new RaavareBatchDAO();
	
	@Override
	public ArrayList<RaavareBatchDTO> getRaavareBatch() throws Exception 
	{
		return raavareBatchDAO.getRaavareBatch();
	}
	
	@Override
	public void addRaavareBatch(int raavareBatch_id, int raavare_id, int mængde) throws Exception 
	{
		raavareBatchDAO.addRaavareBatch( raavareBatch_id, raavare_id, mængde);
	}
}
