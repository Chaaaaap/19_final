package code.connector;

import java.io.IOException;
import java.net.Socket;


public class ConnectorVaegt {

	private Socket socket;
	private String IP;

	public ConnectorVaegt(String ip) {
		this.IP = ip;
		try {
			socket = new Socket(IP, 8000);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}
	public Socket getSocket(){
		return socket;
	}
	
	public String getIP(){
		return IP;
	}
}