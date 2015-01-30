package com.prometheus.hoc.payroll.schema.vo;

import java.io.Serializable;
import java.util.List;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
@JsonIgnoreProperties(value={"$$hashKey","stepInputFormatted","stepOutputFormatted"})
public class PayrollSchemaStep implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -1424051642786299916L;
	

	private Long stepId;
	
	private Long stepSeq;
	
	private String stepName;
	
	private String stepDescr;
	
	private List<PayrollSchemaParam> stepInput;
	
	private List<PayrollSchemaParam> stepOutput;
	
	
	/**
	 * @return the stepId
	 */
	public Long getStepId() {
		return stepId;
	}

	/**
	 * @param long1 the stepId to set
	 */
	public void setStepId(Long stepId) {
		this.stepId = stepId;
	}

	/**
	 * @return the stepSeq
	 */
	public Long getStepSeq() {
		return stepSeq;
	}

	/**
	 * @param stepSeq the stepSeq to set
	 */
	public void setStepSeq(Long stepSeq) {
		this.stepSeq = stepSeq;
	}

	/**
	 * @return the stepName
	 */
	public String getStepName() {
		return stepName;
	}

	/**
	 * @param stepName the stepName to set
	 */
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}

	/**
	 * @return the stepDescr
	 */
	public String getStepDescr() {
		return stepDescr;
	}

	/**
	 * @param stepDescr the stepDescr to set
	 */
	public void setStepDescr(String stepDescr) {
		this.stepDescr = stepDescr;
	}


	/**
	 * @return the stepInput
	 */
	public List<PayrollSchemaParam> getStepInput() {
		return stepInput;
	}

	/**
	 * @param stepInput the stepInput to set
	 */
	public void setStepInput(List<PayrollSchemaParam> stepInput) {
		this.stepInput = stepInput;
	}

	/**
	 * @return the stepOutput
	 */
	public List<PayrollSchemaParam> getStepOutput() {
		return stepOutput;
	}

	/**
	 * @param stepOutput the stepOutput to set
	 */
	public void setStepOutput(List<PayrollSchemaParam> stepOutput) {
		this.stepOutput = stepOutput;
	}

}
