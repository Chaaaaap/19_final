package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperatoerDTO implements Serializable {

	private String password;
	private String type;
	
	public OperatoerDTO(int oprID, String oprNavn, String ini, String cpr, 
			String password, int aktiv, String type) {
		this.password = password;
		this.type = type;
	}
	
	public OperatoerDTO() {
		
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	public Object getType() {
		// TODO Auto-generated method stub
		return type;
	}

}
