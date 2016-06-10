package code.server;

import java.util.ArrayList;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IReceptService;
import code.shared.RaavareDTO;
import code.shared.ReceptDTO;


@SuppressWarnings("serial")
public class ReceptServiceImpl extends RemoteServiceServlet implements IReceptService {

	public ReceptServiceImpl() {
		
	}
	ReceptDAO receptDAO = new ReceptDAO();
	@Override
	public ArrayList<ReceptDTO> getRecept() throws Exception {
			return receptDAO.getRecepter();

	}
	@Override
	public void addRecept(String receptNavn, int recept_id, int[] raavare_id, int[] nom_netto, int[] tolerance) throws Exception {
		receptDAO.addRecept(receptNavn, recept_id, raavare_id,nom_netto,tolerance);
	}
	@Override
	public void redigerRecept(String receptNavn, int recept_id, int[] raavare_id, int[] nom_netto, int[] tolerance, int glid) throws Exception {
		receptDAO.redigerRecept(receptNavn, recept_id, raavare_id,nom_netto,tolerance, glid);
	}
}
