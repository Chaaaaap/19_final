package code.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gwt.user.client.Window;

import code.connector.Connector;
import code.connector.ConnectorVaegt;

public class VsConController implements IVsConController {

	String ip;
	ConnectorVaegt conV = new ConnectorVaegt(ip);
	Connector conDB = new Connector();

	@Override
	public void run(){
		Thread send = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					boolean run = true;

					DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
					BufferedReader scan = new BufferedReader (new InputStreamReader(System.in));

					//					os.writeBytes("RM20 8 \"Indtast\" \"Operatør\" \"ID\"\r\n");

					while (run) {

						String command = scan.readLine().toString();

						os.writeBytes(command+ "\r\n");

						if (command.equals("Q")) {
							run = false;
							scan.close();
						}
					}

				} catch (Exception e) {
					System.err.println("Exception:  " + e);
				}

			}
		});

		Thread receive = new Thread(new Runnable() {

			@Override
			public void run() {

				DataInputStream is;
				try {
					is = new DataInputStream(conV.getSocket().getInputStream());
					while(true){

						String read = is.readLine();	

						if(read.equals("ES")||read.equals("P111 L")||read.equals("RM20 L")||read.equals("D L")){
							Window.alert("Den indtastede kommando er ikke korrekt syntax");
						}
						else {
							System.out.println(read);

						}
					}

				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		});

		send.start();
		receive.start();

	}

	@Override
	public void sendBesked(String sendB){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
			os.writeBytes(sendB+"\r\n");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String modtagBesked(){
		DataInputStream is;
		String read = null;
		try {
			is = new DataInputStream(conV.getSocket().getInputStream());
			read = is.readLine();

		}catch (IOException e) {
			e.printStackTrace();
		}
		return read;
	}

}
