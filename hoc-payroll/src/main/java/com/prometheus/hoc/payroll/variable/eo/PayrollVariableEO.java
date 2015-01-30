package com.prometheus.hoc.payroll.variable.eo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="py_variable")
public class PayrollVariableEO implements Serializable{
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CONF_TYPE",length=20)
	private String configType;
	
	@Id
	@Column(name="CONF_GROUP_ID",length=10)
	private Long configGrpId;
		
	@Id
	@Column(name="SEQUENCE",length=4)
	private int sequence;
	
	@Column(name="CONF_DESCR",length=200)
	private String descr;
	
	@Column(name="AMOUNT1", scale=15, precision=2)
	private Double amount1;
	
	@Column(name="AMOUNT2",  scale=15, precision=2)
	private Double amount2;
	
	@Column(name="AMOUNT3",  scale=15, precision=2)
	private Double amount3;
	
	@Column(name="AMOUNT4",  scale=15, precision=2)
	private Double amount4;

	/**
	 * @return the configType
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * @param configType the configType to set
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
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
	 * @return the amount1
	 */
	public Double getAmount1() {
		return amount1;
	}

	/**
	 * @param amount1 the amount1 to set
	 */
	public void setAmount1(Double amount1) {
		this.amount1 = amount1;
	}

	/**
	 * @return the amount2
	 */
	public Double getAmount2() {
		return amount2;
	}

	/**
	 * @param amount2 the amount2 to set
	 */
	public void setAmount2(Double amount2) {
		this.amount2 = amount2;
	}

	/**
	 * @return the amount3
	 */
	public Double getAmount3() {
		return amount3;
	}

	/**
	 * @param amount3 the amount3 to set
	 */
	public void setAmount3(Double amount3) {
		this.amount3 = amount3;
	}

	/**
	 * @return the amount4
	 */
	public Double getAmount4() {
		return amount4;
	}

	/**
	 * @param amount4 the amount4 to set
	 */
	public void setAmount4(Double amount4) {
		this.amount4 = amount4;
	}

	/**
	 * @return the configGrpId
	 */
	public Long getConfigGrpId() {
		return configGrpId;
	}

	/**
	 * @param configGrpId the configGrpId to set
	 */
	public void setConfigGrpId(Long configGrpId) {
		this.configGrpId = configGrpId;
	}

	/**
	 * @return the descr
	 */
	public String getDescr() {
		return descr;
	}

	/**
	 * @param descr the descr to set
	 */
	public void setDescr(String descr) {
		this.descr = descr;
	}
	
}
