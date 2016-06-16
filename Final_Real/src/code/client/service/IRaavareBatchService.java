package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.DALException;
import code.shared.RaavareBatchDTO;

@RemoteServiceRelativePath("19_Final_RaavareBatch")
public interface IRaavareBatchService extends RemoteService 
{
	ArrayList<RaavareBatchDTO> getRaavareBatch() throws DALException;
	void addRaavareBatch(int raavareBatch_id, int raavare_id, double mængde) throws DALException;
}
