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
	public void login(){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
			os.writeBytes("RM20 8 \"Indtast Operatør ID\" \"\" \"\"\r\n");

			String oprID;

			modtagBesked();

			oprID = modtagBesked();

			oprID = oprID.substring(oprID.length()-2, oprID.length()-1);

			try {
				String oprNavn = oprDAO.getOperatoer(Integer.parseInt(oprID)).getOprNavn();


				os.writeBytes("P111 \"" + oprNavn + "\"\r\n");
				modtagBesked();
			}catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void vaelgProduktbatch(){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());
			os.writeBytes("RM20 8 \"Indtast produktbatch nr \" \"\" \"\"\r\n");

			modtagBesked();
			String produktBatch;
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
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void vaegtkontrol() {
		DataOutputStream os;
		try {
			os = new DataOutputStream(conV.getSocket().getOutputStream());

			os.writeBytes("RM20 8 \"Tjek at vaegten er ubelastet. \" \"OK\" \"\"\r\n");

			modtagBesked();
			modtagBesked();
			
			try {
				pbDAO.updateStatus(1, 1);
			} catch (Exception e) {
			
				e.printStackTrace();
			}

			os.close();
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
			is.close();
		}catch (IOException e) {
			e.printStackTrace();
		}
		return read;

	}



}
