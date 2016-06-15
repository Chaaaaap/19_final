package code.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import code.server.RaavareDAO;
import code.shared.DALException;
import code.shared.RaavareDTO;

public class RaavareDAOTest {

	RaavareDAO rDAO = new RaavareDAO();

	@Test
	public void testGetReceptList() {
		try {
			ArrayList<RaavareDTO> rList = rDAO.getRaavarer();
			Assert.assertNotNull(rList);
		} catch(DALException e) {

		}
	}

	@Test
	public void testOpretRaavare() {
		try {
			rDAO.addRaavare(666, "Test", "TestLand");
			ArrayList<RaavareDTO> rList = rDAO.getRaavarer();
			Assert.assertEquals("Test", rList.get(rList.size()-1).getRaavare_navn());
		} catch(DALException e) {

		}
	}

	@Test
	public void testRedigerRaavare() {
		int nytID = 667;
		int glID = 666;
		try {
			rDAO.redigerRaavare(nytID, "Test", "TestLand", glID);
			ArrayList<RaavareDTO> rList = rDAO.getRaavarer();
			Assert.assertEquals("Test", rList.get(rList.size()-1).getRaavare_navn());
		} catch(DALException e) {

		}
	}
	
}
