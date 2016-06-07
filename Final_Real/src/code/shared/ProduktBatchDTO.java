package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProduktBatchDTO implements Serializable {

	private String status;
	private int pb_id, recept_id, tara, netto, oprID, rb_id;
	
	public ProduktBatchDTO(int pb_id, String status, int recept_id, int tara, int netto, int oprID, int rb_id) {
		this.pb_id = pb_id;
		this.recept_id = recept_id;
		this.status = status;
		this.tara = tara;
		this.netto = netto;
		this.oprID = oprID;
		this.rb_id = rb_id;
	}

	public String getStatus() {
		return status;
	}

	public int getPb_id() {
		return pb_id;
	}

	public int getRecept_id() {
		return recept_id;
	}

	public int getTara() {
		return tara;
	}

	public int getNetto() {
		return netto;
	}

	public int getOprID() {
		return oprID;
	}
	
	public int getRb_id() {
		return rb_id;
	}
	
	
}
