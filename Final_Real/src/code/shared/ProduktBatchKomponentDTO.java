package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ProduktBatchKomponentDTO implements Serializable {
	
	public ProduktBatchKomponentDTO() {}
	
	private int pb_id, rb_id, oprID;
	private double tara, netto;
	
	public ProduktBatchKomponentDTO(int pb_id, int rb_id, double tara, double netto, int oprID) {
	this.pb_id = pb_id;
	this.rb_id = rb_id;
	this.tara = tara;
	this.netto = netto;
	this.oprID = oprID;
	}

	public int getPb_id() {
		return pb_id;
	}

	public int getRb_id() {
		return rb_id;
	}

	public double getTara() {
		return tara;
	}

	public double getNetto() {
		return netto;
	}
	
	public int getOprID() {
		return oprID;
	}
		
	
}
