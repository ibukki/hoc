package com.prometheus.hoc.payroll.schema.xml;


import com.thoughtworks.xstream.annotations.XStreamAlias;

@XStreamAlias("step")
public class SchemaXMLStep {
	
	@XStreamAlias("input-param")
	private SchemaXMLStepInput input;
	
	@XStreamAlias("output-param")
	private SchemaXMLStepOutput output;
	

	/**
	 * @return the input
	 */
	public SchemaXMLStepInput getInput() {
		return input;
	}

	/**
	 * @param input the input to set
	 */
	public void setInput(SchemaXMLStepInput input) {
		this.input = input;
	}

	/**
	 * @return the output
	 */
	public SchemaXMLStepOutput getOutput() {
		return output;
	}

	/**
	 * @param output the output to set
	 */
	public void setOutput(SchemaXMLStepOutput output) {
		this.output = output;
	}
}
