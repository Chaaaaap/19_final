package code.test;

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
		
	}

}
