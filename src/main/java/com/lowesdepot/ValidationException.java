package com.lowesdepot;

public class ValidationException extends Exception {

	private static final long serialVersionUID = 158671423470971166L;

	public ValidationException(String errorMessage) {
        super(errorMessage);
    }
}
