package com.prometheus.hoc.payroll.schema.xml;

import java.io.Serializable;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("formula-item")
public class SchemaXMLStepFormulaItem implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;
	
	@XStreamAsAttribute
	@XStreamAlias("key")
	private String formulaKey;
	
	/**
	 * item type
	 */
	@XStreamAsAttribute
	private int type;

	/**
	 * @return the formulaKey
	 */
	public String getFormulaKey() {
		return formulaKey;
	}

	/**
	 * @param formulaKey the formulaKey to set
	 */
	public void setFormulaKey(String formulaKey) {
		this.formulaKey = formulaKey;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	
}
