package code.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import code.server.ReceptDAO;
import code.shared.DALException;
import code.shared.ReceptDTO;
import code.shared.ReceptKomponentDTO;

public class ReceptDAOTest {

	ReceptDAO rDAO = new ReceptDAO();
	
	@Test
	public void testGetReceptList() {
		try {
			ArrayList<ReceptDTO> receptList = rDAO.getRecepter();
			Assert.assertNotNull(receptList);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testGetRecept() {
		try {
			ReceptDTO dto = rDAO.getRecept(1);
			Assert.assertNotNull(dto);
		} catch(DALException e) {
			
		}
	}
	
	@Test
	public void testOpretRecept() {
		int id = 100;
		ArrayList<ReceptKomponentDTO> list = new ArrayList<ReceptKomponentDTO>();
		list.add(new ReceptKomponentDTO(id, 1, 100, 10));
		try {
			rDAO.addRecept("Teeeest", 100, list);
			Assert.assertNotNull(rDAO.getRecept(id));
		} catch(DALException e) {
			
		}
	}
	
}
