package code.server;

import java.sql.ResultSet;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.RaavareDTO;

public class RaavareDAO implements IRaavareDAO {
	
	private Connector connector = new Connector();

	@Override
	public void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws Exception{
		String query = "INSERT INTO raavare VALUES("+raavare_id+
					", '"+raavare_navn+"', '"+leverandør+"');";
		System.out.println("Hulubulu2\n"+query);
		try {
			connector.doUpdate(query);
		} catch(Exception e) {
			throw e;
		}
	}

	@Override
	public ArrayList<RaavareDTO> getRaavarer() throws Exception {
		ArrayList<RaavareDTO> rvList = new ArrayList<RaavareDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavare");
			if(!rs.next()) throw new Exception("Listen er tom");
			do {
				rvList.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"),
						rs.getString("leverandoer")));
			} while(rs.next());
		} catch(Exception e) {
			throw e;
		} 
		return rvList;
		
	}

	@Override
	public void redigerRaavare(int raavare_id, String raavare_navn, String leverandør) throws Exception {
		// TODO Hvad skal jeg insert på?
		
	}

	
}
