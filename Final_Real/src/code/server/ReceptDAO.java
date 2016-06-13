package code.server;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.DALException;
import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;

public class ReceptDAO implements IReceptDAO {

	private Connector connector = new Connector();
	private int x = 0;

	@Override
	public void addRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp) throws DALException{
		System.out.println("\n\n\n SUUUUUUP");
		Connection con = connector.getConnection();
		try {
			con.setAutoCommit(false);
			connector.doUpdate("INSERT INTO recept VALUES("+recept_id+", '"+receptNavn+"');");
			for (int i = 0; i < komp.size(); i++) {
				connector.doUpdate("INSERT INTO receptkomponent VALUES("+recept_id+", "+komp.get(i).getRaavare_id()+", "
						+ komp.get(i).getMængde()+", "+komp.get(i).getTolerance()+");");
			}

		} catch(SQLException e) {
			try {
				con.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new DALException(e.getMessage());
		} finally {
			try {
				con.commit();
				con.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}

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
				ArrayList<ReceptKomponentDTO> komp = new ArrayList<ReceptKomponentDTO>();
				do {
					x = resultSet.getInt("recept_id");
					komp.add(new ReceptKomponentDTO());
					

				} while(resultSet.next() && resultSet.getInt("recept_id") == x);
				rvList.add(new ReceptDTO(resultSet.getString("recept_navn"),resultSet.getInt("recept_id"),
						komp));
			}while(resultSet.next());
		} catch(Exception e) {
			throw e;
		} 
		return rvList;

	}

	@Override
	public void redigerRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp, int glid) 
			throws Exception {
		Connection con = connector.getConnection();
		try {
			con.setAutoCommit(false);
			connector.doUpdate("UPDATE recept SET recept_id = "+recept_id+", recept_navn = '"+receptNavn+
					"' WHERE recept_id = "+glid+";");
		} catch(Exception e) {
			con.rollback();
			throw e;
		} finally {
			con.commit();
			con.setAutoCommit(true);
		}
	}

}
