package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.RaavareBatchDTO;

@RemoteServiceRelativePath("19_Final_RaavareBatch")
public interface IRaavareBatchService extends RemoteService 
{
	ArrayList<RaavareBatchDTO> getRaavareBatch() throws Exception;
	void addRaavareBatch(int raavareBatch_id, int raavare_id, int mængde) throws Exception;
	void redigerRaavareBatch(int raavareBatch_id, int raavare_id, int mængde, int glid) throws Exception;
}
