package code.server;

import java.sql.ResultSet;

import code.connector.Connector;
import code.shared.OperatoerDTO;

public class OperatoerDAO implements IOperatoerDAO {
	
	private Connector connector = new Connector();

	@Override
	public OperatoerDTO getOperatoer(int oprID) throws Exception{
		
	    try {
	    	ResultSet rs = connector.doQuery("SELECT * FROM operatoer WHERE opr_id = '" + oprID+"'");
	    	if (!rs.first()) throw new Exception("Operatoeren '" + oprID + "' findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), 
	    			rs.getString("cpr"), rs.getString("password"), Integer.parseInt(rs.getString("aktiv")), rs.getString("type"));
	    }
	    catch (Exception e) {
	    	throw new Exception(e.getMessage());
	    }
	}

}
