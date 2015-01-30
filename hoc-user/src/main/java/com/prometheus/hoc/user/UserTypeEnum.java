package com.prometheus.hoc.user;

public enum UserTypeEnum {
	
	INDIVIDUAL(1),
	ENTERPRISE(2),
	TRAILER(3);
	
	private int code;
	
	UserTypeEnum(int code){
		this.code = code;
	}
	
	public int value(){
		return this.code;
	}
}
