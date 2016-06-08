package code.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.RaavareDTO;

public class RaavareDAO implements IRaavareDAO {
	
	private Connector connector = new Connector();

	@Override
	public void addRaavare(int raavare_id, String raavare_navn, String leverandør) throws SQLException{
		
	}

	@Override
	public ArrayList<RaavareDTO> getRaavarer() throws SQLException {
		ArrayList<RaavareDTO> rvList = new ArrayList<RaavareDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM raavare");
			if(!rs.first()) throw new SQLException("Listen er tom");
			do {
				rvList.add(new RaavareDTO(rs.getInt("raavare_id"), rs.getString("raavare_navn"),
						rs.getString("leverandoer")));
			} while(rs.next());
		} catch(SQLException e) {
			throw e;
		}
		return rvList;
	}

	
}
