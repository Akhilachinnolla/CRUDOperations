package com.nt.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import org.springframework.http.HttpStatus;

@RestControllerAdvice
public class ActorsOperationsAdvice {

	
	    @ExceptionHandler(IllegalArgumentException.class)
	    public ResponseEntity<String> handleIllegalIAE(IllegalArgumentException iae){
	    	return new ResponseEntity<String>(iae.getMessage(),HttpStatus.BAD_REQUEST);
	    }
		
		@ExceptionHandler(Exception.class)
		public ResponseEntity<String> handleAllException(Exception e){
		return new ResponseEntity<String>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);	
		}
		
		

	}



