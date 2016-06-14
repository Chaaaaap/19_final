package code.shared.validate;

import java.io.Serializable;

import code.shared.DALException;

@SuppressWarnings("serial")
public class Validator implements Serializable {


	public Validator() {
	}

	public boolean validateInt(String value) {
		try {
			Integer.parseInt(value);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean validateDouble(String value) {
		try {
			Double.parseDouble(value);
		} catch(NumberFormatException e) {
			return false;
		}
		return true;
	}

	public boolean validatePassword(String password) {
		try {
			if(password.length() < 6) {
				throw new DALException("Passwordet er under 6 karakterer");
			}
			char x;
			int smallLetters = 0;
			int capLetters = 0;
			int numbers = 0;
			for (int i = 0; i < password.length(); i++) {
				x = password.charAt(i);
				if(x < 'Z' && x > 'A') {
					capLetters = 1;
				} else if(x < 'z' && x > 'a') {
					smallLetters = 1;
				} else if(x < '9' && x > '0') {
					numbers = 1;
				}
			}
			if((capLetters + smallLetters + numbers) < 3) {
				throw new DALException("Passwordet er ikke godt nok");
			}

			return true;

		} catch(DALException e) {
			return false;
		}

	}

	public boolean validateCPR(String value) {
		try {
			if(value.length() == 10) {
				Integer.parseInt(value);
			} else {
				throw new DALException("CPR nummeret er for langt");
			}
		} catch(NumberFormatException | DALException e) {
			return false;
		}
		return true;
	}
	
	public boolean validate() {
		return true;
	}

}
