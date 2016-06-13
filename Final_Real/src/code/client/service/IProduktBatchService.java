package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.shared.ProduktBatchDTO;

@RemoteServiceRelativePath("19_Final_ProduktBatch")
public interface IProduktBatchService extends RemoteService {

	ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception;
	
}
