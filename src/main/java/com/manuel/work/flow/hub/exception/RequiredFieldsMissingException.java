package com.manuel.work.flow.hub.exception;

public class RequiredFieldsMissingException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RequiredFieldsMissingException(String message) {
        super(message);
    }
}

