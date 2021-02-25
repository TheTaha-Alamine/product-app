package com.imedia24.serverside.exceptions;

public class NotFoundException extends RuntimeException{
	
	public NotFoundException(String object) {
		super("This "+object+" is not found try again");
	}

}
