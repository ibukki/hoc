package com.prometheus.hoc.payroll.schema.eo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity
@Table(name="py_schema_conf")
public class SchemaConfEO {
	
	@Id
	@Column(name="CONFIG_ID",length=20, unique=true)
	private String configId;
	
	@Column(name="DESCRIPTION",columnDefinition="text")
	private String description;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy = "schemaConf",fetch=FetchType.EAGER, cascade={CascadeType.ALL})
	private List<SchemaConfStepEO> confStepList;

	/**
	 * @return the configId
	 */
	public String getConfigId() {
		return configId;
	}

	/**
	 * @param configId the configId to set
	 */
	public void setConfigId(String configId) {
		this.configId = configId;
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
	 * @return the confStepList
	 */
	public List<SchemaConfStepEO> getConfStepList() {
		return confStepList;
	}

	/**
	 * @param confStepList the confStepList to set
	 */
	public void setConfStepList(List<SchemaConfStepEO> confStepList) {
		this.confStepList = confStepList;
	}
	
}
