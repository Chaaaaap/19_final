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

	ConnectorVaegt conV = new ConnectorVaegt();
	Connector conDB = new Connector();
	OperatoerDAO oprDAO = new OperatoerDAO();
	ProduktBatchDAO pbDAO = new ProduktBatchDAO();
	ReceptDAO receptDAO = new ReceptDAO();
	RaavareDAO raaDAO = new RaavareDAO();


@Override
public void aseRun(){
//	login();
//	vaelgProduktbatch();
	
		vaegtkontrol();
//		afvejBeholder();	

}
	
	
	@Override
	public void login(){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
			os.writeBytes("RM20 8 \"Indtast Operatør ID\" \"\" \"\"\r\n");

			String oprID;

			modtagBesked();

			oprID = modtagBesked();

			oprID = oprID.substring(oprID.length()-2, oprID.length()-1);
			System.out.println(oprID);

			try {
				String oprNavn = oprDAO.getOperatoer(Integer.parseInt(oprID)).getOprNavn();


				os.writeBytes("P111 \"" + oprNavn + "\"\r\n");
				modtagBesked();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
//			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int vaelgProduktbatch(){
		String produktBatch = null;
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
			os.writeBytes("RM20 8 \"Indtast produktbatch nr \" \"\" \"\"\r\n");

			modtagBesked();
			
			produktBatch = modtagBesked();
			produktBatch = produktBatch.substring(produktBatch.length()-2, produktBatch.length()-1);

			try {

				int receptNr;

				receptNr = pbDAO.getProduktBatch(Integer.parseInt(produktBatch)).getRecept_id();

				String receptNavn = receptDAO.getRecept(receptNr).getReceptNavn();


				os.writeBytes("P111 \"" + receptNavn + "\"\r\n");
				modtagBesked();
			}catch (Exception e) {
				e.printStackTrace();
			}
//			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return Integer.parseInt(produktBatch);
	}
	@Override
	public void vaegtkontrol() {
		
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());

			//punkt 7
			os.writeBytes("RM20 8 \"Tjek at vaegten er ubelastet. \" \"OK\" \"\"\r\n");

			modtagBesked();
			modtagBesked();
			
			//punkt 8
			try {
				System.out.println("heja");
				pbDAO.updateStatus(1, 1);
				System.out.println("hej");
			} catch (Exception e) {
			
				e.printStackTrace();
			}

//			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void afvejBeholder(){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
			
//			tarer fra vægt, punkt 9		
			do{
				modtagBesked();
				
			}while(modtagBesked() != "TA");
		
			
//			punkt 10 og 11
			os.writeBytes("RM20 8 \"Placer beholder på vægt\" \"OK\" \"\"\r\n");

			modtagBesked();
			modtagBesked();

			
		
//			PLAN B dwadadohafoahfiowfoaifhoawfhaohfhwofhafhwoahfohawofhaohfoaho
//			os.writeBytes("S\r\n");
					
//			punkt 12
			String beholderVaegt = modtagBesked();
System.out.println(beholderVaegt);

//			punkt 13		
			do{
				modtagBesked();
				
			}while(modtagBesked() != "TA");
			
//			punkt 14
			
			
//			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public void tarer(){
		DataOutputStream os;
		try {
			os = new DataOutputStream(conV.getSocket().getOutputStream());

			os.writeBytes("T\r\n");

			modtagBesked();
			
//			os.close();
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

			System.out.println(read);
//			is.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return read;

	}



}
