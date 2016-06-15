package code.test;

import org.junit.Assert;
import org.junit.Test;

import code.shared.validate.Validator;

public class ValidatorTest {
	
	Validator val = new Validator();
	
	@Test
	public void testValidateIntPositive() {
		boolean test = val.validateInt("100");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateIntNegative() {
		boolean test = val.validateInt("Søren");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDoublePositive() {
		boolean test = val.validateDouble("100.0");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDoubleNegative() {
		boolean test = val.validateDate("Søren");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidatePasswordPositive1() {
		boolean test = val.validatePassword("Hej123");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidatePasswordNegative() {
		boolean test = val.validatePassword("MusenErGrimtoOgtyve");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidatePasswordNegative2() {
		boolean test = val.validatePassword("Muse2");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidatePasswordNegative3() {
		boolean test = val.validatePassword("test1234");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDatePositive1() {
		boolean test = val.validateDate("2016-07-19");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDatePositive2() {
		boolean test = val.validateDate("2016-02-29");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDateNegative1() {
		boolean test = val.validateDate("2001-02-29");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDateNegative2() {
		boolean test = val.validateDate("2016-00-01");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDateNegative3() {
		boolean test = val.validateDate("2016-01-00");
		
		Assert.assertTrue(test);
	}
	
	@Test
	public void testValidateDateNegative4() {
		boolean test = val.validateDate("2016-09-31");
		
		Assert.assertTrue(test);
	}
	
	
	
	

}
