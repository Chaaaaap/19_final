package code.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.ReceptDTO;

public class ReceptDAO implements IReceptDAO {

	private Connector connector = new Connector();

	@Override
	public void addRecept(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance) throws Exception{
		// TODO Skal laves om med transaction pewpew
		Connection con = connector.getConnection();
		String query = "INSERT INTO recept VALUES('"+receptNavn+
				"', "+recept_id+", "+raavare_id+", "+nom_netto+", "+tolerance+" );";
		try {
			con.setAutoCommit(false);
			connector.doUpdate("INSERT INTO recept VALUES("+recept_id+", '"+receptNavn+"';");
			connector.doUpdate("INSERT INTO receptkomponent VALUES("+recept_id+", "+raavare_id+", "+nom_netto+", "+tolerance+";");
			
		} catch(Exception e) {
			con.rollback();
			throw e;
		} finally {
			con.commit();
			con.setAutoCommit(true);
		}

	}

	@Override
	public ArrayList<ReceptDTO> getRecepter() throws Exception {
		ArrayList<ReceptDTO> rvList = new ArrayList<ReceptDTO>();
		ResultSet resultSet;
		try {
			resultSet = connector.doQuery("SELECT * FROM recept NATURAL JOIN receptkomponent");
			if(!resultSet.first()) throw new Exception("Listen er tom");
			do {
				rvList.add(new ReceptDTO(resultSet.getString("receptNavn"),resultSet.getInt("recept_id"),
						resultSet.getInt("raavare_id"), resultSet.getInt("nom_netto"),resultSet.getInt("tolerance")));

			} while(resultSet.next());
		} catch(Exception e) {
			throw e;
		} 
		return rvList;

	}

	@Override
	public void redigerRecept(String receptNavn, int recept_id, int raavare_id, int nom_netto, int tolerance, int glid) 
			throws Exception {
		try {
			connector.doUpdate("UPDATE recept SET receptNavn = '"+receptNavn+"', recept_id = "
					+recept_id+", raavare_id = "+raavare_id+", nom_netto = "+nom_netto+", tolerance = "+tolerance+" "
					+ "WHERE recept_id = "+glid+" ;)");		
		} catch(Exception e) {
			throw e;
		}
	}

}
