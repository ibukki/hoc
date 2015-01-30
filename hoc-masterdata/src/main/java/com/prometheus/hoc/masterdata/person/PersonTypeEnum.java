package com.prometheus.hoc.masterdata.person;

public enum PersonTypeEnum {
	
	EMPLOYEE(1),
	
	EXTERNAL_USER(2),
	
	DEPEDENT(3);
	
	private int typeCode;
	
	PersonTypeEnum(int typeCode){
		this.typeCode = typeCode;
	}
	
	public int value(){
		return this.typeCode;
	}
}
