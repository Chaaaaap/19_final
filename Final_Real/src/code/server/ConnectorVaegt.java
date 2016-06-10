package code.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;


public class ConnectorVaegt {

	private Socket socket;
	
	public void VaegtConnect() {

		try {
			socket = new Socket("169.254.2.2", 8000);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		Thread send = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					boolean run = true;
					
					DataOutputStream os = new DataOutputStream(socket.getOutputStream());
//					DataInputStream is = new DataInputStream(socket.getInputStream());
					BufferedReader scan = new BufferedReader (new InputStreamReader(System.in));

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
					is = new DataInputStream(socket.getInputStream());
				while(true){
					String read = is.readLine();
					System.out.println(read);
				}
				
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
				
			}
		});

		send.start();
		receive.start();

	}
}