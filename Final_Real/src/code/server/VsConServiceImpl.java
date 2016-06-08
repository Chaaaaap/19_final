package code.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import code.client.service.IServiceVsCon;

@SuppressWarnings("serial")
public class VsConServiceImpl extends RemoteServiceServlet implements IServiceVsCon{

	private VsCon vsCon = new VsCon();
	
	@Override
	public void Simulator() throws Exception {
		vsCon.Simulator();
	}

}
