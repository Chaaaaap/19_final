package code.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import code.server.ProduktBatchDAO;
import code.shared.DALException;
import code.shared.ProduktBatchDTO;

public class ProduktBatchDAOTest {

	ProduktBatchDAO pbDAO = new ProduktBatchDAO();
	
	@Test
	public void testGetProduktbatchList() {
		try {
			ArrayList<ProduktBatchDTO> pbList = pbDAO.getProduktBatches();
			Assert.assertNotNull(pbList);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testOpretProduktbatch() {
		int id = 999;
		try {
			pbDAO.addProduktBatch(id, 100, "2016-06-16");
			Assert.assertNotNull(id);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testUpdateProduktbatch() {
		int id = 999;
		try {
			pbDAO.updateStatus(id, 2);
			Assert.assertEquals(2, pbDAO.getProduktBatch(id).getStatus());
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testGetProduktbatch() {
		int id = 999;
		try {
			Assert.assertNotNull(pbDAO.getProduktBatch(id));
		} catch(DALException e) {
			
		}
	}

}
