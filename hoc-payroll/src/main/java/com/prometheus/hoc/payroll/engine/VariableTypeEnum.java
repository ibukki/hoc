package com.prometheus.hoc.payroll.engine;

public enum VariableTypeEnum{
	
	WAGE("W_"),
	
	TEMP("T_"),
	
	GLOBAL("G_");
	
	
	private String prefix;
	
	VariableTypeEnum(String prefix){
		this.prefix = prefix;
	}
	
}
