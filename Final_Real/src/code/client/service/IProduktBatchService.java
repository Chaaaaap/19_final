package code.client.service;

import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;

import code.shared.ProduktBatchDTO;

public interface IProduktBatchService extends RemoteService {

	ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception;
}
