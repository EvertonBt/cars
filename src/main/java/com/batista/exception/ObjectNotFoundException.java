package com.batista.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// essa classe converte a exceção objectnotfound lançada pela aplicação no code 404 not found
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ObjectNotFoundException extends RuntimeException {

	public ObjectNotFoundException(String message) {
		super(message);
		
	}	
	
	public ObjectNotFoundException(String message, Throwable cause) { 
		super(message, cause);
		
	}
	
	
}
