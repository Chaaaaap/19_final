package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReceptDTO implements Serializable {
	
	private ReceptKomponentDTO[] komp;
	private int recept_id;
	private String receptNavn;
	
	public ReceptDTO(String receptNavn, int recept_id, ReceptKomponentDTO[] komp) {
		this.recept_id = recept_id;
		this.komp = komp;
		this.receptNavn = receptNavn;
	}
	
	public ReceptDTO() {}

	public int getRecept_id() {
		return recept_id;
	}

	public ReceptKomponentDTO[] getKomp() {
		return komp;
	}

	public String getReceptNavn() {
		return receptNavn;
	}
	
	

}
