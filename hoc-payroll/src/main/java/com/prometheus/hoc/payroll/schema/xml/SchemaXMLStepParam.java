package com.prometheus.hoc.payroll.schema.xml;

import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("param")
public class SchemaXMLStepParam {
	
	private int sequence;
	
	@XStreamAsAttribute
	private String name;
	
	private String type;
	
	private String description;
	
	@XStreamAlias("formula-body")
	private List<SchemaXMLStepFormulaLine> formulaLines;
	
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
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
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
	 * @return the sequence
	 */
	public int getSequence() {
		return sequence;
	}

	/**
	 * @param sequence the sequence to set
	 */
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	/**
	 * @return the formulaLines
	 */
	public List<SchemaXMLStepFormulaLine> getFormulaLines() {
		return formulaLines;
	}

	/**
	 * @param formulaLines the formulaLines to set
	 */
	public void setFormulaLines(List<SchemaXMLStepFormulaLine> formulaLines) {
		this.formulaLines = formulaLines;
	}

}
