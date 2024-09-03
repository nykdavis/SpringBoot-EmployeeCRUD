package com.piersqure.boot.exception;

@SuppressWarnings("serial")
public class EmployeeAlreadyExistsException extends RuntimeException {
	public EmployeeAlreadyExistsException(String message) {
		super(message);
	}
}
