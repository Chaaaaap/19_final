package code.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IServiceVsCon;

@SuppressWarnings("serial")
public class VsConServiceImpl extends RemoteServiceServlet implements IServiceVsCon{

	private ConnectorVaegt vsCon = new ConnectorVaegt();
	
	@Override
	public void ConnectorVaegt() throws Exception {
		vsCon.VaegtConnect();
	}

}
