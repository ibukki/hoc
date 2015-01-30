package com.prometheus.hoc.authentication;

public class GeneralSecurityException extends Exception {

	private static final long serialVersionUID = 262928809980139393L;

	public GeneralSecurityException() {
		super();
	}
	
	public GeneralSecurityException(String message) {
		super(message);
	}
	
	public GeneralSecurityException(Throwable cause) {
		super(cause);
	}
	
	public GeneralSecurityException(String message, Throwable cause) {
		super(message, cause);
	}
}
