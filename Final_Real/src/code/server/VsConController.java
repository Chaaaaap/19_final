package code.server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class VsConController {

	ConnectorVaegt con = new ConnectorVaegt();
	
	public void run(){
		Thread send = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					boolean run = true;

					DataOutputStream os = new DataOutputStream(con.getSocket().getOutputStream());
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
					is = new DataInputStream(con.getSocket().getInputStream());
					while(true){
						String read = is.readLine();
						System.out.println(read);
					}

				} catch (IOException e) {
					e.printStackTrace();
				}		
			}
		});

		send.start();
		receive.start();


	}
}
