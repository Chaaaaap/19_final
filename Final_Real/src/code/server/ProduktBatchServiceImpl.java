package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IProduktBatchService;
import code.shared.ProduktBatchDTO;

@SuppressWarnings("serial")
public class ProduktBatchServiceImpl extends RemoteServiceServlet implements IProduktBatchService {

	public ProduktBatchServiceImpl() {
		
	}
	ProduktBatchDAO pbDAO = new ProduktBatchDAO();
	@Override
	public ArrayList<ProduktBatchDTO> getProduktBatches() throws Exception {
			return pbDAO.getProduktBatches();

	}

}
