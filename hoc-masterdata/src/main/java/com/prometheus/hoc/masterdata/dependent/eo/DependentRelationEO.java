package com.prometheus.hoc.masterdata.dependent.eo;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ms_dependent_rel")
public class DependentRelationEO {
	
	@Id
	@Column(name="relation_type", length=5, unique=true)
	private String relationType;
	
	@Column(name="relation_name", length=200)
	private String relationName;
	
	@Column(name="src_obj_type", length=30)
	private String srcObjType;
	
	@Column(name="tgt_obj_type", length=30)
	private String tgtObjType;
	
	
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
	 * @return the relationName
	 */
	public String getRelationName() {
		return relationName;
	}

	/**
	 * @param relationName the relationName to set
	 */
	public void setRelationName(String relationName) {
		this.relationName = relationName;
	}



	/**
	 * @return the srcObjType
	 */
	public String getSrcObjType() {
		return srcObjType;
	}



	/**
	 * @param srcObjType the srcObjType to set
	 */
	public void setSrcObjType(String srcObjType) {
		this.srcObjType = srcObjType;
	}



	/**
	 * @return the tgtObjType
	 */
	public String getTgtObjType() {
		return tgtObjType;
	}



	/**
	 * @param tgtObjType the tgtObjType to set
	 */
	public void setTgtObjType(String tgtObjType) {
		this.tgtObjType = tgtObjType;
	}
}
