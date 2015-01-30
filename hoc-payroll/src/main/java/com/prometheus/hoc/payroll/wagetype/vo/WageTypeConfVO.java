package com.prometheus.hoc.payroll.wagetype.vo;

import java.io.Serializable;

import com.prometheus.hoc.payroll.wagetype.eo.WageTypeConf;

public class WageTypeConfVO extends WageTypeConf implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 4278030217528589800L;

	private String typeText;

	/**
	 * @return the typeText
	 */
	public String getTypeText() {
		return typeText;
	}

	/**
	 * @param typeText the typeText to set
	 */
	public void setTypeText(String typeText) {
		this.typeText = typeText;
	}

}
