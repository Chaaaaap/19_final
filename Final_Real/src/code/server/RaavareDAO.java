package code.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.DALException;
import code.shared.RaavareDTO;

public class RaavareDAO implements IRaavareDAO {
	
	private Connector connector = new Connector();

	@Override
	public void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws DALException{
		String query = "INSERT INTO raavare VALUES("+raavare_id+
					", '"+raavare_navn+"', '"+leverandør+"');";
		try {
			connector.doUpdate(query);
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public ArrayList<RaavareDTO> getRaavarer() throws DALException {
		ArrayList<RaavareDTO> rvList = new ArrayList<RaavareDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavare");
			if(!rs.next()) throw new DALException("Listen er tom");
			do {
				rvList.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"),
						rs.getString("leverandoer")));
			} while(rs.next());
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		} 
		return rvList;
		
	}

	@Override
	public void redigerRaavare(int raavare_id, String raavare_navn, String leverandør, int glid) throws DALException {
		try {
		connector.doUpdate("UPDATE raavare SET raavare_id = "+raavare_id+", raavare_navn = '"
		+raavare_navn+"', leverandoer = '"+leverandør+"' WHERE raavare_id = "+glid+";");		
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
	
	@Override
	public String getRaavare(int pb_id) throws DALException {
		ResultSet rs;
		String navn;
		try {
			rs = connector.doQuery("SELECT raavare_navn FROM produktbatch natural join produktbatchkomponent natural join raavarebatch natural join raavare where pb_id ="+pb_id +" group by raavare_navn;");
			navn = rs.getString("raavare_navn");
			if(!rs.next()) throw new DALException("Raavaren findes ikke");
			
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		} 
		return navn;
		
	}
	
}
