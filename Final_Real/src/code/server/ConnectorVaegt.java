package code.server;

import java.io.IOException;
import java.net.Socket;


public class ConnectorVaegt {

	private Socket socket;

	public ConnectorVaegt() {

		try {
			socket = new Socket("169.254.2.2", 8000);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public Socket getSocket(){
		return socket;
	}
}