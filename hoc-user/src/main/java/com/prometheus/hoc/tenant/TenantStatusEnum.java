package com.prometheus.hoc.tenant;

public enum TenantStatusEnum {
	
	UNDER_REVIEW(0),
	ACTIVE(1),
	LOCKED(2);
	
	private int code;
	
	TenantStatusEnum(int code){
		this.code = code;
	}
	
	public int value(){
		return this.code;
	}
}
