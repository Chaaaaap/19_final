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
	public void redigerRaavare(int raavare_id, String raavare_navn, String leverandør, int glid) throws Exception {
		try {
		connector.doUpdate("UPDATE raavare SET raavare_id = "+raavare_id+", raavare_navn = '"
		+raavare_navn+"', leverandoer = '"+leverandør+"' WHERE raavare_id = "+glid+";");		
		} catch(Exception e) {
			throw e;
		}
	}
	
	@Override
	public RaavareDTO getRaavare(int pb_id) throws Exception {
		RaavareDTO raaDTO = new RaavareDTO();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT raavare_navn FROM produktbatch natural join produktbatchkomponent natural join raavarebatch natural join raavare where pb_id ="+pb_id +" group by raavare_navn;");
			if(!rs.next()) throw new Exception("Listen er tom");
			
		} catch(Exception e) {
			throw e;
		} 
		return raaDTO;
		
	}

	
}
