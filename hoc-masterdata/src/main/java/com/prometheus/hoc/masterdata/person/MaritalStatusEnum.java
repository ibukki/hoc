package com.prometheus.hoc.masterdata.person;

public enum MaritalStatusEnum {
	
	SINGLE(1),
	
	MARRIED(2);
	
	private int code;
	
	MaritalStatusEnum(int code){
		this.code = code;
	}
	
	public int value(){
		return this.code;
	}
}
