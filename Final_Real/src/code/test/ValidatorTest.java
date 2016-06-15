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
	
	

}
