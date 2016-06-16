package code.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.DALException;
import code.shared.RaavareBatchDTO;

public class RaavareBatchDAO implements IRaavareBatchDAO 
{
	Connector connector = new Connector();

	@Override
	public void addRaavareBatch(int raavareBatch_id, int raavare_id, int mængde) throws DALException 
	{
		try 
		{
			connector.doUpdate("INSERT INTO raavarebatch VALUES("+raavareBatch_id+", "+raavare_id+", '"+mængde+"');");
		} 
		catch(SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public ArrayList<RaavareBatchDTO> getRaavareBatch() throws DALException 
	{

		
		ArrayList<RaavareBatchDTO> rbList = new ArrayList<RaavareBatchDTO>();
		ResultSet rs;
		

		try 
		{
			rs = connector.doQuery("SELECT * FROM raavarebatch;");

			if(!rs.first()) throw new DALException("Listen er tom");
			do 
			{
				rbList.add(new RaavareBatchDTO(rs.getInt("rb_id"), rs.getInt("raavare_id"), rs.getInt("maengde")));
			} 
			while(rs.next());
			return rbList;
		} 
		catch(SQLException e) 
		{
			throw new DALException(e.getMessage());
		}
	}
}
