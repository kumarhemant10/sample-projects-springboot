package com.hk.prj.usermanagement.exception;

public class ResourceNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4094490748298545535L;

	public ResourceNotFoundException(String msg) {
        super(msg);
    }

    public ResourceNotFoundException(String msg, Exception e) {
        super(msg + " because of " + e.toString());
    }
}
