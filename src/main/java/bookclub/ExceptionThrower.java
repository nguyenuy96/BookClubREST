package bookclub;

import org.springframework.http.HttpStatus;

import Components.HandleException;

public class ExceptionThrower {
	
	public void throwException(HttpStatus httpStatus, String message) throws HandleException{
		HandleException handleException = new HandleException();
		handleException.setCode(httpStatus.value());
		handleException.setMessage(message);
		throw handleException;
	}
}
