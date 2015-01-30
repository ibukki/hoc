package com.prometheus.hoc.payroll.schema.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"$$hashKey"})
public class PayrollSchema implements Serializable{
	
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
	 * step list
	 */
	private List<PayrollSchemaStep> stepList;
	
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

	/**
	 * @return the stepList
	 */
	public List<PayrollSchemaStep> getStepList() {
		return stepList;
	}

	/**
	 * @param stepList the stepList to set
	 */
	public void setStepList(List<PayrollSchemaStep> stepList) {
		this.stepList = stepList;
	}
	
}
