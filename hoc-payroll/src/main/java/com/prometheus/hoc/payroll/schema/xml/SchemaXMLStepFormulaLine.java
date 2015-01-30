package com.prometheus.hoc.payroll.schema.xml;

import java.io.Serializable;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("formula-line")
public class SchemaXMLStepFormulaLine implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 5514289548038844477L;
	
	
	@XStreamImplicit(itemFieldName="formula-item")
	private List<SchemaXMLStepFormulaItem> items;

	/**
	 * @return the items
	 */
	public List<SchemaXMLStepFormulaItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<SchemaXMLStepFormulaItem> items) {
		this.items = items;
	}
	
	
}
