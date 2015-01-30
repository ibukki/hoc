package com.prometheus.hoc.payroll.variable.eo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="py_variable_grp")
public class PayrollVariableGrpEO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="GRP_ID",insertable = true, updatable = true, nullable = false)
	private Long grpId;
	
	@Column(name="DESCRIPTION",length=200)
	private String description;

	/**
	 * @return the grpId
	 */
	public Long getGrpId() {
		return grpId;
	}

	/**
	 * @param grpId the grpId to set
	 */
	public void setGrpId(Long grpId) {
		this.grpId = grpId;
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
	
	
}
