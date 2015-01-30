package com.prometheus.hoc.common.context;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.prometheus.hoc.common.bean.CompanyBean;

@Component
public class GlobalContext implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	
	private CompanyBean companyBean;


	/**
	 * @return the companyBean
	 */
	public CompanyBean getCompanyBean() {
		return companyBean;
	}


	/**
	 * @param companyBean the companyBean to set
	 */
	public void setCompanyBean(CompanyBean companyBean) {
		this.companyBean = companyBean;
	}
}
