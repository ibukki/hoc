package com.prometheus.hoc.tenant.vo;

import java.io.Serializable;

import com.prometheus.hoc.tenant.eo.TenantEO;

public class TenantVO extends TenantEO implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -7939421492432886597L;

	private String superUser;
	
	private String superUserPass;

	/**
	 * @return the superUser
	 */
	public String getSuperUser() {
		return superUser;
	}

	/**
	 * @param superUser the superUser to set
	 */
	public void setSuperUser(String superUser) {
		this.superUser = superUser;
	}

	/**
	 * @return the superUserPass
	 */
	public String getSuperUserPass() {
		return superUserPass;
	}

	/**
	 * @param superUserPass the superUserPass to set
	 */
	public void setSuperUserPass(String superUserPass) {
		this.superUserPass = superUserPass;
	}
}
