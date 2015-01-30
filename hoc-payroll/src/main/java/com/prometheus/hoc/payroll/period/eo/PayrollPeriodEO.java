package com.prometheus.hoc.payroll.period.eo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="py_pay_period")
public class PayrollPeriodEO implements Serializable{
	
	/**
	 * Serializable
	 */
	private static final long serialVersionUID = -9150172328271710526L;

	@Id
	@Column(name="PY_YEAR", length=4)
	private int payYear;
	
	@Id
	@Column(name="PY_SEQ", length=10)
	private int paySeq;
	
	@Id
	@Column(name="PY_TYPE", length=2)
	private int payType;
	
	@Column(name="PY_NAME", length = 50)
	private String payName;
	
	@Column(name="BEGDA")
	private Date begda;
	
	@Column(name="ENDDA")
	private Date endda;
	
	@Column(name="PAY_DATE")
	private Date payDate;

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

	/**
	 * @return the begda
	 */
	public Date getBegda() {
		return begda;
	}

	/**
	 * @param begda the begda to set
	 */
	public void setBegda(Date begda) {
		this.begda = begda;
	}

	/**
	 * @return the endda
	 */
	public Date getEndda() {
		return endda;
	}

	/**
	 * @param endda the endda to set
	 */
	public void setEndda(Date endda) {
		this.endda = endda;
	}

	/**
	 * @return the payDate
	 */
	public Date getPayDate() {
		return payDate;
	}

	/**
	 * @param payDate the payDate to set
	 */
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	/**
	 * @return the payName
	 */
	public String getPayName() {
		return payName;
	}

	/**
	 * @param payName the payName to set
	 */
	public void setPayName(String payName) {
		this.payName = payName;
	}
	
	
	
}
