package code.shared;

import java.io.Serializable;

public class RaavareDTO implements Serializable {
	private static final long serialVersionUID = -6970077387551979504L;
	private int raavare_id;
	private String raavare_navn;
	private String leverandør;
	
	public RaavareDTO(){}

	public RaavareDTO(int raavare_id, String raavare_navn, String leverandør) {
		this.raavare_id = raavare_id;
		this.raavare_navn = raavare_navn;
		this.leverandør = leverandør;
	}

	public int getRaavare_id() {
		return raavare_id;
	}

	public String getRaavare_navn() {
		return raavare_navn;
	}

	public String getLeverandør() {
		return leverandør;
	}
	
}
