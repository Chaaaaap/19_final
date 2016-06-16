package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")

public class RaavareBatchDTO implements Serializable 
{	
	private int raavareBatch_id, raavare_id;
	private double mængde;
	
	public RaavareBatchDTO(){}


	public RaavareBatchDTO(int raavareBatch_id, int raavare_id, double mængde) 
	{
		this.raavareBatch_id = raavareBatch_id;
		this.raavare_id = raavare_id;
		this.mængde = mængde;
	}
	
	public int getRaavareBatch_id() 
	{
		return raavareBatch_id;
	}


	public int getRaavare_id() 
	{
		return raavare_id;
	}
	
	public double getMængde() 
	{
		return mængde;
	}
}
