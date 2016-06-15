package code.test;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import code.server.OperatoerDAO;
import code.shared.DALException;
import code.shared.OperatoerDTO;

public class OperatoerDAOTest {
	
	OperatoerDAO oprDAO = new OperatoerDAO();
	OperatoerDTO opr;

	@Test
	public void testOpretOperatoerPositiv() {
		int x = 100;
		try {
		opr = new OperatoerDTO(x, "Smølf", "S", "1234589685", "Hej123", 1, "administrator");
		oprDAO.opretBruger(x, "Smølf", "S", "1234589685", "Hej123", "administrator");
		
		Assert.assertEquals(oprDAO.getOperatoer(x), opr);
		} catch(DALException e) {
			
		} 
		
	}
	
	@Test
	public void testRedigerOperatoerPositiv() {
		int nytID = 99;
		int glID = 100;
		try {
			oprDAO.redigerBruger(nytID, glID, "Smølf", "S", "1234589685", "Hej123", "administrator");
			
			Assert.assertEquals(nytID, oprDAO.getOperatoer(nytID));
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testGetOperatoerList() {
		OperatoerDAO dao = new OperatoerDAO();
		try {
		List<OperatoerDTO> oprList = dao.getOperatoerer();
		assertNotNull("ArrayList was null, check your databse", oprList);
		} catch(DALException e) {
			
			
		}
	}
	
	@Test
	public void testGetOperatoer() {
		OperatoerDAO dao = new OperatoerDAO();
		try {
		OperatoerDTO opr = dao.getOperatoer(1);
		assertNotNull("Operator was null, check your database", opr);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testDeactivate() {
		int x = 99;
		OperatoerDAO dao = new OperatoerDAO();
		try {
			dao.deaktiverBruger(x, 0);
			Assert.assertEquals(dao.getOperatoer(x).getStatus(), 0);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testActivate() {
		int x = 99;
		OperatoerDAO dao = new OperatoerDAO();
		try {
			dao.aktiverBruger(x, 1);
			Assert.assertEquals(dao.getOperatoer(x).getStatus(), 1);
		} catch(DALException e) {
			
		}
	}

}
