package com.prometheus.hoc.payroll.function;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value="className")
public class ConfFunction implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	private String name;
	
	private List<ConfFunctionParam> paramList;
	
	private String description;
	
	private String className;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the paramList
	 */
	public List<ConfFunctionParam> getParamList() {
		return paramList;
	}

	/**
	 * @param paramList the paramList to set
	 */
	public void setParamList(List<ConfFunctionParam> paramList) {
		this.paramList = paramList;
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
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}

	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	
}
