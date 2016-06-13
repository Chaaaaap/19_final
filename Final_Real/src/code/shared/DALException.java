package code.shared;

import java.io.Serializable;

@SuppressWarnings("serial")
public class DALException extends Exception implements Serializable {
	
	public DALException() {}
	
	public DALException(String msg) {
		super(msg);
	}

}
