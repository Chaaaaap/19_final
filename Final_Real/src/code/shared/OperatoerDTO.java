package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperatoerDTO implements Serializable {

	private String password;
	private String type;
	private int oprID;
	private String oprNavn;
	private String ini;
	private String cpr;
	private int aktiv;
	
	public OperatoerDTO(int oprID, String oprNavn, String ini, String cpr, 
			String password, int aktiv, String type) {
		this.password = password;
		this.type = type;
		this.oprID = oprID;
		this.oprNavn = oprNavn;
		this.ini = ini;
		this.cpr = cpr;
		this.aktiv = aktiv;
	}
	
	public OperatoerDTO() {
		
	}

	public String getPassword() {
		return password;
	}

	public Object getType() {
		return type;
	}

	public int getOprID() {
		return oprID;
	}
	
	public void skiftPassword(String password) {
		this.password = password;
	}

	public String getOprNavn() {
		return oprNavn;
	}

	public String getIni() {
		return ini;
	}

	public String getCPR() {
		return cpr;
	}
	
	public int getStatus() {
		return aktiv;
	}


}
