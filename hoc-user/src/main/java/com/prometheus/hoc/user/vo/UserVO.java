/**
 * 
 */
package com.prometheus.hoc.user.vo;

import java.io.Serializable;

import com.prometheus.hoc.user.eo.UserEO;

/**
 * @author haibin
 *
 */
public class UserVO extends UserEO implements Serializable {
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7246296535033464456L;

	private String userFullName;
	
	private String industry;
	
	private String company;
	
	private String title;
	
	/**
	 * @return the userName
	 */
	public String getUserFullName() {
		userFullName = this.getFirstName() + " " + this.getLastName();
		return this.userFullName;
	}

	/**
	 * @return the industry
	 */
	public String getIndustry() {
		return industry;
	}

	/**
	 * @param industry the industry to set
	 */
	public void setIndustry(String industry) {
		this.industry = industry;
	}

	/**
	 * @return the company
	 */
	public String getCompany() {
		return company;
	}

	/**
	 * @param company the company to set
	 */
	public void setCompany(String company) {
		this.company = company;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
}
