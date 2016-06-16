package code.connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import code.shared.OperatoerDTO;

/** @author Ronnie Dalsgaard */
public class Connector {
//	private final String HOST     = "localhost";
//	private final int    PORT     = 3306;
//	private final String DATABASE = "19_cdiofinal";
//	private final String USERNAME = "root"; 
//	private final String PASSWORD = "";
//	private Connection connection;
	
	private final String HOST     = "ec2-52-30-89-247.eu-west-1.compute.amazonaws.com";
	private final int    PORT     = 3306;
	private final String DATABASE = "grp19";
	private final String USERNAME = "grp19"; 
	private final String PASSWORD = "hS#Vk94G";
	private Connection connection;
	
	

	public Connector() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://" + HOST + ":" + PORT + "/" + DATABASE;
			connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	public Connection getConnection(){
		return connection;
	}

	public ResultSet doQuery(String query) throws SQLException{
		Statement stmt = connection.createStatement();
		ResultSet res = stmt.executeQuery(query);
		return res;
	}

	public void doUpdate(String query) throws SQLException{
		Statement stmt = connection.createStatement();
		stmt.executeUpdate(query);
	}
	   
	public OperatoerDTO loginUser(String cpr, String password) {
		OperatoerDTO returnuser = null;

		try {
			ResultSet rs = doQuery("SELECT * FROM operatoer WHERE `cpr`='"+cpr+"' AND `password`='"+password+"'");
			if (!rs.first()) throw new Exception("Operatoeren " + cpr + " findes ikke\n");
			returnuser = new OperatoerDTO(rs.getInt("opr_id"), rs.getString("opr_navn"), rs.getString("ini"), rs.getString("cpr"), rs.getString("password"), 1, "bruger");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return returnuser;
	}
}