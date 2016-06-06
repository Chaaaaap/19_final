package code.server;

import java.sql.ResultSet;
import java.sql.SQLException;

import code.connector.Connector;
import code.shared.OperatoerDTO;

public class OperatoerDAO implements IOperatoerDAO {
	
	private Connector connector;
	
	public OperatoerDAO() {
		connector = new Connector();
	}
	
	@Override
	public OperatoerDTO getOperatoer(int oprID) throws Exception{
		
	    try {
	    	ResultSet rs = connector.doQuery("SELECT * FROM operatoer WHERE opr_id = '" + oprID+"'");
	    	if (!rs.first()) throw new Exception("Operatoeren '" + oprID + "' findes ikke");
	    	return new OperatoerDTO (rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), 
	    			rs.getString("cpr"), rs.getString("password"), Integer.parseInt(rs.getString("aktiv")), rs.getString("type"));
	    }
	    catch (SQLException e) {
	    	e.printStackTrace();
	    	throw e;
	    }
	}
	
	@Override
	public void skiftPassword(int oprID, String nyPassword) throws Exception {
		try {
			connector.doUpdate("UPDATE operatoer SET password = '"+nyPassword+"' WHERE opr_id = '"+oprID+"'");
		} catch(Exception e) {
			throw e;
		}
	}

}
