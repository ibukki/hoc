package com.prometheus.hoc.common.module.bean;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("page")
public class ModuleConfPage extends ModuleConfBase implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3353271206064203765L;

	@XStreamAsAttribute
	private String id;
	
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

}
