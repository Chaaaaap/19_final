package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RaavareBatchDTO implements Serializable {

	private int mængde;
	private int rb_id;
	private int raavare_id;

	public RaavareBatchDTO(int rb_id, int raavare_id, int mængde) {
		this.rb_id = rb_id;
		this.raavare_id = raavare_id;
		this.mængde = mængde;
	}

	public int getMængde() {
		return mængde;
	}

	public int getRb_id() {
		return rb_id;
	}

	public int getRaavare_id() {
		return raavare_id;
	}
	
	
}
