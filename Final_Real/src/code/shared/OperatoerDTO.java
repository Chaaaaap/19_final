package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperatoerDTO implements Serializable {

	private String password;
	private String type;
	private int oprID;
	
	public OperatoerDTO(int oprID, String oprNavn, String ini, String cpr, 
			String password, int aktiv, String type) {
		this.password = password;
		this.type = type;
		this.oprID = oprID;
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

}
