package code.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import code.connector.ConnectorVaegt;
import code.shared.DALException;

public class VsConController implements IVsConController {

	private ConnectorVaegt conV = new ConnectorVaegt();
	private OperatoerDAO oprDAO = new OperatoerDAO();
	private ProduktBatchDAO pbDAO = new ProduktBatchDAO();
	private ReceptDAO receptDAO = new ReceptDAO();
	private double taraBeholder;
	private String produktBatch;
	private String vaegt;
	private int oprNr;
	private String oprID;
	private int rbNr;
	private String rbID;
	private int pbNr;
	private Double vaegtInt;
	private int receptNr;

	@Override
	public void aseRun() throws NumberFormatException, DALException{

		login();
		vaelgProduktbatch();
		for (int i = 0; i < receptDAO.countPBK(receptNr); i++) {
			afvejBeholder();	
		}
		afslutning();
	}


	@Override
	public void login(){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());

			os.writeBytes("RM20 8 \"Tast Operatoer ID\" \"\" \"\"\r\n");
			String tjekStart;
			tjekStart = modtagBesked();

			if(!tjekStart.equals("RM20 B")){
				modtagBesked();
			}

			oprID = modtagBesked();

			oprID = oprID.substring(8, oprID.length()-1);

			try {
				oprNr = Integer.parseInt(oprID);

				String oprNavn = oprDAO.getOperatoer(oprNr).getOprNavn();
				os.writeBytes("P111 \"" + oprNavn + "\"\r\n");
				modtagBesked();
			}catch (Exception e) {
				e.printStackTrace();
				os.writeBytes("RM20 8 \"ID findes ikke\" \"OK\" \"\"\r\n");
				modtagBesked();
				modtagBesked();
				login();
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
			os.writeBytes("RM20 8 \"Tast produktbatch nr \" \"\" \"\"\r\n");

			modtagBesked();

			produktBatch = modtagBesked();
			produktBatch = produktBatch.substring(8, produktBatch.length()-1);

			try {
				pbNr = Integer.parseInt(produktBatch);

				receptNr = pbDAO.getProduktBatch(Integer.parseInt(produktBatch)).getRecept_id();
				String receptNavn = receptDAO.getRecept(receptNr).getReceptNavn();


				os.writeBytes("P111 \"" + receptNavn + "\"\r\n");
				modtagBesked();

			}catch (Exception e) {
				e.printStackTrace();
				os.writeBytes("RM20 8 \"ID findes ikke\" \"OK\" \"\"\r\n");
				modtagBesked();
				modtagBesked();
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
			os.writeBytes("RM20 8 \"Er vaegten ubelastet\" \"OK\" \"\"\r\n");
			modtagBesked();
			modtagBesked();

			//punkt 8 vaelgproduktbatch virker måske ikke.
			try {
				pbDAO.updateStatus(pbNr, 1);
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
			vaegtkontrol();
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());

			//			tarer fra vægt, punkt 9	
			os.writeBytes("T\r\n");
			modtagBesked();



			//			punkt 10 og 11
			os.writeBytes("RM20 8 \"Placer beholder\" \"OK\" \"\"\r\n");
			modtagBesked();
			modtagBesked();

			//			punkt 12, 13
			os.writeBytes("T\r\n");
			String beholderVaegt = modtagBesked();
			beholderVaegt = beholderVaegt.substring(8, 15);
			taraBeholder = Double.parseDouble(beholderVaegt);		


			//			punkt 14
			os.writeBytes("RM20 8 \"Tast raavarebatch nr\" \"\" \"\"\r\n");
			modtagBesked();



			rbID = modtagBesked();

			rbID = rbID.substring(8,rbID.length()-1);

			rbNr = Integer.parseInt(rbID);

			os.writeBytes("P111 \"Tryk [-> for afvej\"\r\n");
			modtagBesked();

			os.writeBytes("K 3\r\n");

			do {
				modtagBesked();
			}while(!modtagBesked().contains("K C 4"));

			os.writeBytes("K 1\r\n");
			modtagBesked();

			os.writeBytes("S\r\n");
			vaegt = modtagBesked();

			vaegt = vaegt.substring(8, vaegt.length()-2);
			vaegtInt = Double.parseDouble(vaegt);

			try {
				pbDAO.opretPBKomp(pbNr,rbNr,taraBeholder,vaegtInt,oprNr);
			} catch (DALException e) {
				e.printStackTrace();
				os.writeBytes("RM20 8 \"Kunne ikke oprettes\" \"OK\" \"\"\r\n");
				modtagBesked();
				modtagBesked();
				afvejBeholder();
			}


		} catch (IOException e) {
			e.printStackTrace();
		}
	}	

	@Override
	public void afslutning(){
		try {
			DataOutputStream os = new DataOutputStream(conV.getSocket().getOutputStream());



			os.writeBytes("RM20 8 \"Afvejning afsluttet\" \"OK\" \"\" \r\n");
			modtagBesked();
			modtagBesked();
			
			os.writeBytes("RM20 8 \"Toem vaegten\" \"OK\" \"\" \r\n");
			modtagBesked();
			modtagBesked();
			
			os.writeBytes("P111 \"\" \r\n");
			modtagBesked();
			
			os.writeBytes("T\r\n");
			modtagBesked();
			
			
			pbDAO.updateStatus(Integer.parseInt(produktBatch), 2);
		} catch (Exception e) {
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
