package code.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import code.connector.Connector;
import code.shared.DALException;
import code.shared.ProduktBatchDTO;
import code.shared.ProduktBatchKomponentDTO;

public class ProduktBatchDAO implements IProduktbatchDAO {

	private Connector connector = new Connector();
	private int x = 0;
	private int l;

	@Override
	public ArrayList<ProduktBatchDTO> getProduktBatches() throws DALException {
		int i = 0;
		ArrayList<ProduktBatchDTO> pbList = new ArrayList<ProduktBatchDTO>();
		ResultSet resultSet;
		try {
			ResultSet length = connector.doQuery("SELECT COUNT(pb_id) FROM produktbatch;");
			if(!length.first()) throw new DALException("Listen er tom");
			l = length.getInt("COUNT(pb_id)");
			resultSet = connector.doQuery("SELECT * FROM produktbatch NATURAL JOIN produktbatchkomponent");
			if(!resultSet.first()) throw new DALException("Listen er tom");
			while(i < l-1) {
				ArrayList<ProduktBatchKomponentDTO> komp = new ArrayList<ProduktBatchKomponentDTO>();
				do {
					x = resultSet.getInt("pb_id");
					komp.add(new ProduktBatchKomponentDTO(resultSet.getInt("pb_id"), resultSet.getInt("rb_id"),
							resultSet.getInt("tara"), resultSet.getInt("netto"), resultSet.getInt("opr_id")));

					System.out.println("\nx = "+x);
				} while(resultSet.next() && resultSet.getInt("pb_id") == x);
				resultSet.previous();
				pbList.add(new ProduktBatchDTO(resultSet.getInt("pb_id"),resultSet.getInt("status"), resultSet.getInt("recept_id"), resultSet.getString("dato")));
				if(!resultSet.next())
					return pbList;
			}
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		} 
		return pbList;

	}

	@Override
	public ProduktBatchDTO getProduktBatch(int pbID) throws DALException{

		try {
			ResultSet rs = connector.doQuery("SELECT * FROM produktbatch WHERE pb_id = '" + pbID+"'");
			if (!rs.first()) throw new DALException("Operatoeren '" + pbID + "' findes ikke");
			return new ProduktBatchDTO(rs.getInt("pb_id"), rs.getInt("status"),
					rs.getInt("recept_id"), null);	    }
		catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public void updateStatus(int pb_id, int status) throws DALException{
		try {
			connector.doUpdate("UPDATE produktbatch SET status="+status+" WHERE pb_id=" +pb_id);
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public void addProduktBatch(int pb_id, int recept_id, String dato) throws DALException {
		String query = "INSERT INTO produktbatch VALUES("+pb_id+", 1, "+recept_id+", '"+dato+"')";
		try {
			connector.doUpdate(query);
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	public void opretPBKomp(int pb_id, int rb_id, double tara, double netto, int opr_id) throws DALException{
		try {
			connector.doUpdate("INSERT INTO produktbatchkomponent values ("+pb_id+","+rb_id+","+tara+","+netto+","+opr_id+")");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());		}
	}

}
