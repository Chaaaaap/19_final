package code.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IServiceVsCon;

@SuppressWarnings("serial")
public class VsConServiceImpl extends RemoteServiceServlet implements IServiceVsCon{

	private VsConController vsCon = new VsConController();
	
	
	@Override
	public void ConnectorVaegt() throws Exception {
//		vsCon.run();
		vsCon.login();
		
	}

}
