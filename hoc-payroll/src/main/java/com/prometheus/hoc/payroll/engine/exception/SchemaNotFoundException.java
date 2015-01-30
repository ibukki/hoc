package com.prometheus.hoc.payroll.engine.exception;

public class SchemaNotFoundException extends Exception {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String msg;
	
	public SchemaNotFoundException(String msg){
		super();
		this.msg = msg;
	}

	/* (non-Javadoc)
	 * @see java.lang.Throwable#getMessage()
	 */
	@Override
	public String getMessage() {
		return this.msg;
	}
	
	
}
