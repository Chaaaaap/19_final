package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReceptDTO implements Serializable {
	
	private int recept_id, raavare_id, nom_netto, tolerance;
	private String receptNavn;
	
	public ReceptDTO(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance) {
		this.recept_id = recept_id;
		this.raavare_id = raavare_id;
		this.nom_netto = nom_netto;
		this.tolerance = tolerance;
		this.receptNavn = receptNavn;
	}
	
	public ReceptDTO() {}

	public int getRecept_id() {
		return recept_id;
	}

	public int getRaavare_id() {
		return raavare_id;
	}

	public int getNom_netto() {
		return nom_netto;
	}

	public int getTolerance() {
		return tolerance;
	}

	public String getReceptNavn() {
		return receptNavn;
	}
	
	

}
