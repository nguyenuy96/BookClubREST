package controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import Components.ExceptionResponse;
import Components.HandleException;

@ControllerAdvice
public class ExceptionController {

	@ExceptionHandler(HandleException.class)
	public ResponseEntity<ExceptionResponse> handlingException(HandleException handleException) throws Exception {
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setCode(handleException.getCode());
		exceptionResponse.setDescription(handleException.getMessage());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.valueOf(handleException.getCode()));
	}

}
