package com.prometheus.hoc.common.module.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("module")
public class ModuleConfModule extends ModuleConfBase implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5032952722298872212L;

	@XStreamAsAttribute
	private String id;
	
	
	@XStreamAlias("pages")
	private List<ModuleConfPage> pages; 
	
	@XStreamImplicit(itemFieldName="sub-module")
	private List<ModuleConfSubModule> subModules;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}


	/**
	 * @return the pages
	 */
	public List<ModuleConfPage> getPages() {
		return pages;
	}

	/**
	 * @param pages the pages to set
	 */
	public void setPages(List<ModuleConfPage> pages) {
		this.pages = pages;
	}
	
	public void addPage(ModuleConfPage page){
		if(this.pages == null){
			this.pages = new ArrayList<ModuleConfPage>();
		}
		this.pages.add(page);
	}

	/**
	 * @return the subModules
	 */
	public List<ModuleConfSubModule> getSubModules() {
		return subModules;
	}

	/**
	 * @param subModules the subModules to set
	 */
	public void setSubModules(List<ModuleConfSubModule> subModules) {
		this.subModules = subModules;
	}
	
	public void addSubModule(ModuleConfSubModule subModule){
		if(this.subModules == null){
			this.subModules = new ArrayList<ModuleConfSubModule>();
		}
		this.subModules.add(subModule);
	}

}
