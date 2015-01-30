package com.prometheus.hoc.common.bean;

import java.io.Serializable;

public class ModuleIncludeBean implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5054649921158697194L;

	private String module;
	
	private String pageUrl;
	
	public ModuleIncludeBean(){
		
	}
	
	
	public ModuleIncludeBean(String module, String pageUrl){
		this.module = module;
		this.pageUrl = pageUrl;
	}
	
	
	/**
	 * @return the module
	 */
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the pageUrl
	 */
	public String getPageUrl() {
		return pageUrl;
	}

	/**
	 * @param pageUrl the pageUrl to set
	 */
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	
	
}
