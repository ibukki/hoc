package com.prometheus.hoc.common.module.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("sub-module")
public class ModuleConfSubModule extends ModuleConfBase implements Serializable{

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -5032952722298872212L;

	@XStreamAsAttribute
	private String id;
	
	@XStreamAlias("pages")
	private List<ModuleConfPage> pages; 
	
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
	
}
