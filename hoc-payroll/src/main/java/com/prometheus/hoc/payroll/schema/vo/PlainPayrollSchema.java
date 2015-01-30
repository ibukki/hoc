package com.prometheus.hoc.payroll.schema.vo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"$$hashKey"})
public class PlainPayrollSchema implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7028454942494708302L;
	
	/**
	 * config Id
	 */
	private String configId;
	
	/**
	 * description
	 */
	private String description;
	

	/**
	 * @return the configId
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
}
