package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AlertControllerNotFoundException extends Exception {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AlertControllerNotFoundException(String message) {
		super(message);
	}
}
