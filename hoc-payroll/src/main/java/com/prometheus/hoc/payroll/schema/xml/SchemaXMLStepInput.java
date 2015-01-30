package com.prometheus.hoc.payroll.schema.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

@XStreamAlias("input-param")
public class SchemaXMLStepInput {
	
	@XStreamImplicit(itemFieldName="param")
	private List<SchemaXMLStepParam> params;

	/**
	 * @return the params
	 */
	public List<SchemaXMLStepParam> getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(List<SchemaXMLStepParam> params) {
		this.params = params;
	}
	
	
}
