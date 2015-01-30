package com.prometheus.hoc.payroll.schema.eo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="py_schema_step")
public class SchemaConfStepEO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="STEP_ID",insertable = true, updatable = true, nullable = false)
	private Long stepId;
	
	@Column(name="SEQUENCE",length=4)
	private Long stepSeq;
	
	@Column(name="STEP_NAME",length=50)
	private String stepName;
	
	@Column(name="DESCRIPTION", columnDefinition="text")
	private String stepDescr;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
	@JoinColumn(name="SCHEMA_CONFID")
	private SchemaConfEO schemaConf;
	
	@Lob
	@Column(name="STEP_CONTENT", columnDefinition="BLOB")
	private byte[] stepContent;

	/**
	 * @return the stepId
	 */
	public Long getStepId() {
		return stepId;
	}

	/**
	 * @param stepId the stepId to set
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
	 * @return the schemaConf
	 */
	public SchemaConfEO getSchemaConf() {
		return schemaConf;
	}

	/**
	 * @param schemaConf the schemaConf to set
	 */
	public void setSchemaConf(SchemaConfEO schemaConf) {
		this.schemaConf = schemaConf;
	}

	/**
	 * @return the stepContent
	 */
	public byte[] getStepContent() {
		return stepContent;
	}

	/**
	 * @param stepContent the stepContent to set
	 */
	public void setStepContent(byte[] stepContent) {
		this.stepContent = stepContent;
	}
}
