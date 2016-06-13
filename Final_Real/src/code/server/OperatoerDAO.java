package code.server;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
	    	throw e;
	    }
	}
	
	@Override
	public void skiftPassword(int oprID, String nyPassword) throws Exception {
		try {
			connector.doUpdate("UPDATE operatoer SET password = '"+nyPassword+"' WHERE opr_id = '"+oprID+"'");
		} catch(SQLException e) {
			throw e;
		}
	}

	@Override
	public void opretBruger(int oprID, String navn, String ini, String CPR, String password, String type)
			throws Exception {
		try {
			connector.doUpdate("INSERT INTO operatoer(opr_id, opr_navn, ini, cpr, password, aktiv, type)"
					+ " VALUES " + "(" + oprID + ", '" + navn + "', '" + ini + "', '" + 
					CPR + "', '" + password + "'"+", '1', '"+type+"')");
			
		} catch(SQLException e) {
			throw e;
		}
	}

	@Override
	public ArrayList<OperatoerDTO> getOperatoerer() throws Exception {
		ArrayList<OperatoerDTO> oprList = new ArrayList<OperatoerDTO>();
		ResultSet rs;
		try {
			rs = connector.doQuery("SELECT * FROM grp19.operatoer");
			if(!rs.next()) throw new Exception("Listen er tom");
			do {
				oprList.add(new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"),
						rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), rs.getInt("aktiv"), rs.getString("type")));
			} while(rs.next());
		} catch(Exception e) {
			throw e;
		} 
		return oprList;
	}
	
	@Override // GAMMELT ID 
	public void redigerBruger(int opr_id, int old_id, String opr_navn, String ini, String cpr, String password, String type) throws Exception {
		try {
		connector.doUpdate("UPDATE operatoer SET opr_id = "+opr_id+", opr_navn = '"
		+opr_navn+"', ini = '"+ini+"', cpr = '"+cpr+"', password = '"+password+"', type = '"+type+"' WHERE opr_id = "+old_id+" ;");		
		} catch(Exception e) {
			throw e;
		}
	}

	public void deaktiverBruger(int oprID, int aktiv) throws Exception{
		try {
			connector.doUpdate("UPDATE operatoer SET aktiv = "+0+" WHERE opr_id = "+oprID+" ;");		
			} catch(Exception e) {
				throw e;
			}
		
	}
	
	public void aktiverBruger(int oprID, int aktiv) throws Exception{
		try {
			connector.doUpdate("UPDATE operatoer SET aktiv = "+1+" WHERE opr_id = "+oprID+" ;");		
			} catch(Exception e) {
				throw e;
			}
		
	}

}
