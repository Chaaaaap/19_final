package code.shared;

import java.io.Serializable;
import java.util.ArrayList;

public class ProduktBatchDTO implements Serializable {
	private static final long serialVersionUID = 5417965784796499144L;
	
	private int pb_id, status, recept_id, rb_id, tara, netto, oprID;
	private ArrayList<ProduktBatchKomponentDTO> komp;
	
	public ProduktBatchDTO() {}
	
	public ProduktBatchDTO(int pb_id, int status, int recept_id, ArrayList<ProduktBatchKomponentDTO> komp /*, int rb_id, int tara, int netto, int oprID */) {
		this.pb_id = pb_id;
		this.recept_id = recept_id;
		this.status = status;
		this.komp = komp;
//		this.rb_id = rb_id;
//		this.tara = tara;
//		this.netto = netto;
//		this.oprID = oprID;
	}


	public int getStatus() {
		return status;
	}

	public int getPb_id() {
		return pb_id;
	}

	public int getRecept_id() {
		return recept_id;
	}
	
	public int getRb_id() {
		return rb_id;
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

	public ArrayList<ProduktBatchKomponentDTO> getKomp() {
		
		return komp;
	}
	
	
}
