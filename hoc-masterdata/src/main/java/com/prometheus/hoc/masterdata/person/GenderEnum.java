package com.prometheus.hoc.masterdata.person;

public enum GenderEnum {
	
	FEMALE(0),
	
	MALE(1),
	
	UNKOWN(2);
	
	private int code;
	
	GenderEnum(int code){
		this.code = code;
	}
	
	public int value(){
		return this.code;
	}
}
