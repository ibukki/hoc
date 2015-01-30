package com.prometheus.hoc.payroll.function;

import java.io.Serializable;

import com.bubuwork.bukki.function.ParamTypeEnum;

public class ConfFunctionParam implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String name;
	
	private ParamTypeEnum type;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the type
	 */
	public ParamTypeEnum getType() {
		return type;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(ParamTypeEnum type) {
		this.type = type;
	}
	
	
}
