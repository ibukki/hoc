package com.prometheus.hoc.payroll.schema.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.prometheus.hoc.payroll.schema.util.SchemaStepParser;
import com.prometheus.hoc.payroll.schema.xml.SchemaXMLStepFormulaLine;

@JsonIgnoreProperties(value={"formula","$$hashKey"})
public class PayrollSchemaParam implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 128447870631366786L;

	private String paramName;
	
	private String paramType;
	
	private String paramDescr;
	
	private int sequence;
	
	private List<SchemaXMLStepFormulaLine> formulaLines;
	
	
	/**
	 * @return the paramName
	 */
	public String getParamName() {
		return paramName;
	}

	/**
	 * @param paramName the paramName to set
	 */
	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	/**
	 * @return the paramType
	 */
	public String getParamType() {
		return paramType;
	}

	/**
	 * @param paramType the paramType to set
	 */
	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	/**
	 * @return the paramDescr
	 */
	public String getParamDescr() {
		return paramDescr;
	}

	/**
	 * @param paramDescr the paramDescr to set
	 */
	public void setParamDescr(String paramDescr) {
		this.paramDescr = paramDescr;
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
	
	public String getFormula(){
		String output = "";
		if(this.formulaLines != null && this.formulaLines.size() > 0){
			output = SchemaStepParser.convertFromLineItemsToString(this.formulaLines);
		}
		int seqLength = System.getProperty("line.separator").length();
		if(output.endsWith(System.getProperty("line.separator"))){
			output = output.substring(0, output.length() - seqLength);
		}
		return output;
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
