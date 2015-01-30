package com.prometheus.hoc.payroll.wagetype.vo;

public class WageTypeSearch {
	
	private String type;
	
	private String descr;
	
	private String wagetypeId;

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @return the wagetypeId
	 */
	public String getWagetypeId() {
		return wagetypeId;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}

	/**
	 * @param wagetypeId the wagetypeId to set
	 */
	public void setWagetypeId(String wagetypeId) {
		this.wagetypeId = wagetypeId;
	}
}
