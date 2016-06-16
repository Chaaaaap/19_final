package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReceptKomponentDTO implements Serializable {

	public ReceptKomponentDTO() {}
	
	private int recept_id, raavare_id;
	private double mængde, tolerance;
	
	public ReceptKomponentDTO(int recept_id, int raavare_id, double mængde, double tolerance) {
		this.tolerance = tolerance;
		this.mængde = mængde;
		this.raavare_id = raavare_id;
		this.recept_id = recept_id;
	}

	public int getRecept_id() {
		return recept_id;
	}

	public int getRaavare_id() {
		return raavare_id;
	}

	public double getMængde() {
		return mængde;
	}

	public double getTolerance() {
		return tolerance;
	}
	
	
}
