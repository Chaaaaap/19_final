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
	private int l;

	@Override
	public void addRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp) throws DALException{
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
	public ArrayList<ReceptDTO> getRecepter() throws DALException {
		int i = 0;
		ArrayList<ReceptDTO> rvList = new ArrayList<ReceptDTO>();
		ResultSet resultSet;
		try {
			ResultSet length = connector.doQuery("SELECT COUNT(recept_id) FROM recept;");
			if(!length.first()) throw new DALException("Listen er tom");
			l = length.getInt("COUNT(recept_id)");
			resultSet = connector.doQuery("SELECT * FROM recept NATURAL JOIN receptkomponent");
			if(!resultSet.first()) throw new DALException("Listen er tom");
			while(i < l-1) {
				ArrayList<ReceptKomponentDTO> komp = new ArrayList<ReceptKomponentDTO>();
				do {
					x = resultSet.getInt("recept_id");
					komp.add(new ReceptKomponentDTO(resultSet.getInt("recept_id"), resultSet.getInt("raavare_id"),
							resultSet.getInt("nom_netto"), resultSet.getInt("tolerance")));

					System.out.println("\nx = "+x);
				} while(resultSet.next() && resultSet.getInt("recept_id") == x);
				resultSet.previous();
				rvList.add(new ReceptDTO(resultSet.getString("recept_navn"),resultSet.getInt("recept_id"),komp));
				if(!resultSet.next())
					return rvList;
			}
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		} 
		return rvList;

	}

	@Override
	public void redigerRecept(String receptNavn, int recept_id, ArrayList<ReceptKomponentDTO> komp, int glid) 
			throws DALException {
		Connection con = connector.getConnection();
		try {
			con.setAutoCommit(false);
			connector.doUpdate("UPDATE recept SET recept_id = "+recept_id+", recept_navn = '"+receptNavn+
					"' WHERE recept_id = "+glid+";");
			for (ReceptKomponentDTO rkDTO : komp) {
				connector.doUpdate("UPDATE receptkomponent SET recept_id = "+recept_id+", raavare_id = "
			+rkDTO.getRaavare_id()+", nom_netto = "+rkDTO.getMængde()+", tolerance = "+rkDTO.getTolerance()+";");
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
	public ReceptDTO getRecept(int recept_id) throws DALException{

		try {
			ResultSet rs = connector.doQuery("SELECT * FROM recept NATURAL JOIN receptkomponent WHERE recept_id = " + recept_id+";");
			if (!rs.first()) throw new DALException("Recepted " + recept_id + " findes ikke");
			ArrayList<ReceptKomponentDTO> komp = new ArrayList<ReceptKomponentDTO>();
			do {
				komp.add(new ReceptKomponentDTO(rs.getInt("recept_id"), rs.getInt("raavare_id"),
						rs.getInt("nom_netto"), rs.getInt("tolerance")));
			}while(rs.next() && recept_id == rs.getInt("recept_id"));
			rs.previous();
			ReceptDTO dto =  new ReceptDTO(rs.getString("recept_navn"), rs.getInt("recept_id"), komp);
			if(!rs.next())
				return dto;
			return null;
		}catch (SQLException e) {
			throw new DALException(e.getMessage());
		}

	}
	@Override
	public int countPBK(int recept_id)throws DALException {
		ResultSet rs;
		String countString;
		int countInt;
		try {
			rs = connector.doQuery("Select count(recept_id) from receptkomponent where recept_id ="+recept_id);
			if(!rs.first()) throw new DALException("Listen er tom");
			countString = rs.getString("count(recept_id)");
			countInt = Integer.parseInt(countString);

			return countInt;
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}

	}
}
