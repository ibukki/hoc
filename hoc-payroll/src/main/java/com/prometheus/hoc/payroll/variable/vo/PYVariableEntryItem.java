package com.prometheus.hoc.payroll.variable.vo;

import java.io.Serializable;

public class PYVariableEntryItem implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private int sequence;
	
	private Double amount1;
	
	private Double amount2;
	
	private Double amount3;
	
	private Double amount4;

	/**
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the amount1
	 */
	public Double getAmount1() {
		return amount1;
	}

	/**
	 * @param amount1 the amount1 to set
	 */
	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	/**
	 * @return the amount2
	 */
	public Double getAmount2() {
		return amount2;
	}

	/**
	 * @param amount2 the amount2 to set
	 */
	public void setAmount2(Double amount2) {
		this.amount2 = amount2;
	}

	/**
	 * @return the amount3
	 */
	public Double getAmount3() {
		return amount3;
	}

	/**
	 * @param amount3 the amount3 to set
	 */
	public void setAmount3(Double amount3) {
		this.amount3 = amount3;
	}

	/**
	 * @return the amount4
	 */
	public Double getAmount4() {
		return amount4;
	}

	/**
	 * @param amount4 the amount4 to set
	 */
	public void setAmount4(Double amount4) {
		this.amount4 = amount4;
	}

	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
