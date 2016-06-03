package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OperatoerDTO implements Serializable {

	private String password;
	
	public OperatoerDTO(int oprID, String oprNavn, String ini, String cpr, 
			String password, int aktiv, String type) {
		this.password = password;
	}
	
	public OperatoerDTO() {
		
	}

	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

}
