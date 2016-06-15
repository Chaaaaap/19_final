package code.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import code.server.RaavareBatchDAO;
import code.shared.DALException;
import code.shared.RaavareBatchDTO;

public class RaavareBatchDAOTest {

	RaavareBatchDAO rbDAO = new RaavareBatchDAO();
	
	@Test
	public void testGetRaavarebatchList() {
		try {
			ArrayList<RaavareBatchDTO> rbList = rbDAO.getRaavareBatch();
			Assert.assertNotNull(rbList);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testOpretRaavarebatch() {
		int id = 666;
		try {
			rbDAO.addRaavareBatch(id, 1, 50);
			ArrayList<RaavareBatchDTO> rbList = rbDAO.getRaavareBatch();
			Assert.assertEquals(id, rbList.get(rbList.size()-1).getRaavareBatch_id());
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testRedigerRaavarebatch() {
		int nyID = 667;
		int glID = 666;
		try {
			rbDAO.redigerRaavareBatch(nyID, 1, 50, glID);
			ArrayList<RaavareBatchDTO> rbList = rbDAO.getRaavareBatch();
			Assert.assertEquals(nyID, rbList.get(rbList.size()-1).getRaavareBatch_id());
		} catch(DALException e) {
			
		}
	}

}
