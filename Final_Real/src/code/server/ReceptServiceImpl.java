package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IReceptService;
import code.shared.DALException;
import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;

@SuppressWarnings("serial")
public class ReceptServiceImpl extends RemoteServiceServlet implements IReceptService {

	public ReceptServiceImpl() {}
	ReceptDAO receptDAO = new ReceptDAO();
	@Override
	public ArrayList<ReceptDTO> getRecept() throws Exception {
			return receptDAO.getRecepter();

	}
	@Override
	public void addRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp) throws DALException {
		receptDAO.addRecept(receptNavn, recept_id, komp);
	}
}
