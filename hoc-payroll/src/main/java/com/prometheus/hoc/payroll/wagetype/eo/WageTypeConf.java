package com.prometheus.hoc.payroll.wagetype.eo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="WAGETYPE_CONF")
public class WageTypeConf implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -789556482067297091L;


	@Id
	@Column(name="WAGETYPEID", length=20)
	private String wagetypeId;
	
	@Column(name="DESCRIPTION", length=200)
	private String description;
	
	@Column(name="TYPE",length=1)
	private Character type;


	/**
	 * @return the wagetypeId
	 */
	public String getWagetypeId() {
		return wagetypeId;
	}

	/**
	 * @param wagetypeId the wagetypeId to set
	 */
	public void setWagetypeId(String wagetypeId) {
		this.wagetypeId = wagetypeId;
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
	 * @return the type
	 */
	public Character getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Character type) {
		this.type = type;
	}
	
	
}
