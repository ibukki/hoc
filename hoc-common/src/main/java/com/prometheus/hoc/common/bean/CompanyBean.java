package com.prometheus.hoc.common.bean;

import java.io.Serializable;

public class CompanyBean implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7384296780886731039L;

	private String companyId;
	
	private Long clientId;

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the clientId
	 */
	public Long getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the clientId to set
	 */
	public void setClientId(Long clientId) {
		this.clientId = clientId;
	}
	
	
}
