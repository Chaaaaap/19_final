package code.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import code.connector.ConnectorVaegt;

public class VsConController implements IVsConController {

	private ConnectorVaegt conV = new ConnectorVaegt();
	private OperatoerDAO oprDAO = new OperatoerDAO();
	private ProduktBatchDAO pbDAO = new ProduktBatchDAO();
	private ReceptDAO receptDAO = new ReceptDAO();
	private RaavareDAO raaDAO = new RaavareDAO();
	private double taraBeholder;
	private String produktBatch = null;


	@Override
	public void aseRun(){
		login();
		vaelgProduktbatch();	
		vaegtkontrol();
		afvejBeholder();	

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
	public void vaelgProduktbatch(){
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

	}
	@Override
	public void vaegtkontrol() {

		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());

			//punkt 7
			os.writeBytes("RM20 8 \"Er vægten ubelastet\" \"OK\" \"\"\r\n");

			System.out.println("hej" + modtagBesked());
			System.out.println("med"+modtagBesked());

			//punkt 8 vaelgproduktbatch virker måske ikke.
			try {
				pbDAO.updateStatus(Integer.parseInt(produktBatch), 1);
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
			os.writeBytes("T\r\n");
			modtagBesked();



			//			punkt 10 og 11
			os.writeBytes("RM20 8 \"Placer beholder på vægt\" \"OK\" \"\"\r\n");

			modtagBesked();
			modtagBesked();

			//			punkt 12, 13
			os.writeBytes("T\r\n");
			String beholderVaegt = modtagBesked();
			beholderVaegt = beholderVaegt.substring(8, 15);
			taraBeholder = Double.parseDouble(beholderVaegt);		


			//			punkt 14
			String raavareNavn = null;
			try {
				raavareNavn = raaDAO.getRaavare(Integer.parseInt(produktBatch)).getRaavare_navn();
			} catch (Exception e) {
				e.printStackTrace();
			}
			os.writeBytes("RM20 8 \"Indtast rb.nr fra"+raavareNavn+"\" \"\" \"\"\r\n");

			modtagBesked();
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
