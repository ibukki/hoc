package com.prometheus.hoc.masterdata.dependent.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ms_dependent_rel_entry")
public class DependentRelationEntryEO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="entry_id", insertable = true, updatable = true, nullable = false, scale=10)
	private Long entryId;
	
	@Column(name="relation_type", length=5)
	private String relationType;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="src_obj_id")
	private Long srcObjId;
	
	@Column(name="tgt_obj_id")
	private Long tgtObjId;

	/**
	 * @return the entryId
	 */
	public Long getEntryId() {
		return entryId;
	}

	/**
	 * @param entryId the entryId to set
	 */
	public void setEntryId(Long entryId) {
		this.entryId = entryId;
	}

	/**
	 * @return the relationType
	 */
	public String getRelationType() {
		return relationType;
	}

	/**
	 * @param relationType the relationType to set
	 */
	public void setRelationType(String relationType) {
		this.relationType = relationType;
	}

	/**
	 * @return the startDate
	 */
	public Date getStartDate() {
		return startDate;
	}

	/**
	 * @param startDate the startDate to set
	 */
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	/**
	 * @return the endDate
	 */
	public Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	/**
	 * @return the srcObjId
	 */
	public Long getSrcObjId() {
		return srcObjId;
	}

	/**
	 * @param srcObjId the srcObjId to set
	 */
	public void setSrcObjId(Long srcObjId) {
		this.srcObjId = srcObjId;
	}

	/**
	 * @return the tgtObjId
	 */
	public Long getTgtObjId() {
		return tgtObjId;
	}

	/**
	 * @param tgtObjId the tgtObjId to set
	 */
	public void setTgtObjId(Long tgtObjId) {
		this.tgtObjId = tgtObjId;
	}


	
}
