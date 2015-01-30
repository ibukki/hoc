package com.prometheus.hoc.payroll.period.vo;

import java.io.Serializable;

import com.prometheus.hoc.payroll.period.eo.PayrollPeriodEO;

public class PayrollPeriodVO extends PayrollPeriodEO implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3380086753502832345L;
	
	
	private String begdaTxt;
	
	private String enddaTxt;
	
	private String payDateTxt;

	/**
	 * @return the begdaTxt
	 */
	public String getBegdaTxt() {
		return begdaTxt;
	}

	/**
	 * @param begdaTxt the begdaTxt to set
	 */
	public void setBegdaTxt(String begdaTxt) {
		this.begdaTxt = begdaTxt;
	}

	/**
	 * @return the enddaTxt
	 */
	public String getEnddaTxt() {
		return enddaTxt;
	}

	/**
	 * @param enddaTxt the enddaTxt to set
	 */
	public void setEnddaTxt(String enddaTxt) {
		this.enddaTxt = enddaTxt;
	}

	/**
	 * @return the payDateTxt
	 */
	public String getPayDateTxt() {
		return payDateTxt;
	}

	/**
	 * @param payDateTxt the payDateTxt to set
	 */
	public void setPayDateTxt(String payDateTxt) {
		this.payDateTxt = payDateTxt;
	}
}
