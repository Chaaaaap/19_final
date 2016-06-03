package code.server;

import code.shared.OperatoerDTO;

public interface IOperatoerDAO {

	OperatoerDTO getOperatoer(int name) throws Exception;
}
