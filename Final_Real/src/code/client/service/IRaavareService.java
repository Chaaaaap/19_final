package code.client.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import code.server.RaavareDAO;
import code.shared.RaavareDTO;

@RemoteServiceRelativePath("19_Final_Raavare")
public interface IRaavareService extends RemoteService {

	ArrayList<RaavareDTO> getRaavarer() throws SQLException;
}
