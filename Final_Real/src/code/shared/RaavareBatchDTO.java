package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RaavareBatchDTO implements Serializable {

	private int mængde;
	private int raavareBatch_id;
	private int raavare_id;

	public RaavareBatchDTO(int raavareBatch_id, int raavare_id, int mængde) {
		this.raavareBatch_id = raavareBatch_id;
		this.raavare_id = raavare_id;
		this.mængde = mængde;
	}

	public int getMængde() {
		return mængde;
	}

	public int getRaavareBatch_id() {
		return raavareBatch_id;
	}

	public int getRaavare_id() {
		return raavare_id;
	}
	
	
}
