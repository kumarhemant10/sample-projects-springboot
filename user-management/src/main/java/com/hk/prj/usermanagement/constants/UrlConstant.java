package com.hk.prj.usermanagement.constants;

public class UrlConstant {
	private UrlConstant() {
	    throw new IllegalStateException("Constant class");
	  }
	
	public static final String USERS ="/users";
	public static final String USERS_BY_ID =USERS+"/{id}";
}
