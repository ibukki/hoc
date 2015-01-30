package com.prometheus.hoc.payroll.engine.bean;

import java.util.List;

public class ExecuteParam {
	
	private String configId;
	
	private List<Long> employeeIdList;
	
	private Long countryId;
	
	private Long eegroupId;
	

	/**
	 * @return the configId
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * @return the employeeIdList
	 */
	public List<Long> getEmployeeIdList() {
		return employeeIdList;
	}

	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
	}

	/**
	 * @param employeeIdList the employeeIdList to set
	 */
	public void setEmployeeIdList(List<Long> employeeIdList) {
		this.employeeIdList = employeeIdList;
	}

	/**
	 * @return the countryId
	 */
	public Long getCountryId() {
		return countryId;
	}

	/**
	 * @param countryId the countryId to set
	 */
	public void setCountryId(Long countryId) {
		this.countryId = countryId;
	}

	/**
	 * @return the eegroupId
	 */
	public Long getEegroupId() {
		return eegroupId;
	}

	/**
	 * @param eegroupId the eegroupId to set
	 */
	public void setEegroupId(Long eegroupId) {
		this.eegroupId = eegroupId;
	}
}
