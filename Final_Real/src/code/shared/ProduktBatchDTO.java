package code.shared;

import java.io.Serializable;

public class ProduktBatchDTO implements Serializable {
	private static final long serialVersionUID = 5417965784796499144L;
	
	private int pb_id, status, recept_id;
	
	public ProduktBatchDTO() {}
	
	public ProduktBatchDTO(int pb_id, int status, int recept_id) {
		this.pb_id = pb_id;
		this.recept_id = recept_id;
		this.status = status;
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
	
	
}
