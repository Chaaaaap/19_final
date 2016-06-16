package code.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IServiceVsCon;
import code.shared.DALException;

@SuppressWarnings("serial")
public class VsConServiceImpl extends RemoteServiceServlet implements IServiceVsCon{

	private VsConController vsCon = new VsConController();
	
	
	@Override
	public void ConnectorVaegt() throws DALException {
		vsCon.aseRun();	
	}

}
