package com.prometheus.hoc.payroll.period.vo;

import java.io.Serializable;

public class PayrollPeriodCompKey implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int payYear;
	
	private int paySeq;
	
	private int payType;

	/**
	 * @return the payYear
	 */
	public int getPayYear() {
		return payYear;
	}

	/**
	 * @param payYear the payYear to set
	 */
	public void setPayYear(int payYear) {
		this.payYear = payYear;
	}

	/**
	 * @return the paySeq
	 */
	public int getPaySeq() {
		return paySeq;
	}

	/**
	 * @param paySeq the paySeq to set
	 */
	public void setPaySeq(int paySeq) {
		this.paySeq = paySeq;
	}

	/**
	 * @return the payType
	 */
	public int getPayType() {
		return payType;
	}

	/**
	 * @param payType the payType to set
	 */
	public void setPayType(int payType) {
		this.payType = payType;
	}
	
	
}
