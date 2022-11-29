package com.tyss.homedelivey.exception;

@SuppressWarnings("serial")
public class NotFoundException extends RuntimeException{
         
	public NotFoundException(String message) {
		super(message);
	}
}