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

	public boolean validateDate(String value) {
		try {
			String[] dateArr = value.split("-");
			date(Integer.parseInt(dateArr[0]), Integer.parseInt(dateArr[1]), Integer.parseInt(dateArr[2]));
		} catch(Exception e) {
			return false;
		}

		return true;
	}

	private boolean date(int year, int month, int day) throws DALException {
		if(year < 0 || year > 9999){
			throw new DALException("datoen findes ikke");
		}
		if(month <= 0 || month > 12){
			throw new DALException("datoen findes ikke");
		}
		if(day <= 0 || day > 31){
			throw new DALException("datoen findes ikke");
		}
		boolean leapYear = (year%4== 0 && !(year%100 ==0 && year%400!=0) ? true : false);
		int daysInMonth;
		switch (month) {
		case 2: //februar
			if (leapYear){
				daysInMonth = 29;
			} else {
				daysInMonth = 28;
			}
			break;
		case 4: //De korte måneder
		case 6:
		case 9:
		case 11:
			daysInMonth = 30;
			break;
		default: //Alle de andre
			daysInMonth = 31;
			break;
		}
		if(day > daysInMonth) {
			throw new DALException("datoen findes ikke");
		}
		return true;
	}

}
