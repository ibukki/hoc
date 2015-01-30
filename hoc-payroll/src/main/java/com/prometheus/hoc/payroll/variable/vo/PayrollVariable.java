package com.prometheus.hoc.payroll.variable.vo;

import java.io.Serializable;

public class PayrollVariable implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private String configTypeId;
	
	private Long configGrpId;
	
	private String columnName;
	
	private int sequence;
	
	private Double amount;
	

	/**
	 * @return the configTypeId
	 */
	public String getConfigTypeId() {
		return configTypeId;
	}

	/**
	 * @param configTypeId the configTypeId to set
	 */
	public void setConfigTypeId(String configTypeId) {
		this.configTypeId = configTypeId;
	}


	/**
	 * @return the columnName
	 */
	public String getColumnName() {
		return columnName;
	}

	/**
	 * @param columnName the columnName to set
	 */
	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

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
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the configGrpId
	 */
	public Long getConfigGrpId() {
		return configGrpId;
	}

	/**
	 * @param configGrpId the configGrpId to set
	 */
	public void setConfigGrpId(Long configGrpId) {
		this.configGrpId = configGrpId;
	}
}
