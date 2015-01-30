package com.prometheus.hoc.masterdata.wage.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ms_wage_entry")
public class WageEntryEO {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="entry_id", insertable = true, updatable = true, nullable = false, scale=10)
	private Long entryId;
	
	@Column(name="person_id", scale=10)
	private Long personId;
	
	@Column(name="sequence")
	private int sequence;
	
	@Column(name="start_date")
	private Date startDate;
	
	@Column(name="end_date")
	private Date endDate;
	
	@Column(name="wagetype_id")
	private String wageTypeId;
	
	@Column(name="amount", scale=15, precision=2)
	private Double amount;

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
	 * @return the wageTypeId
	 */
	public String getWageTypeId() {
		return wageTypeId;
	}

	/**
	 * @param wageTypeId the wageTypeId to set
	 */
	public void setWageTypeId(String wageTypeId) {
		this.wageTypeId = wageTypeId;
	}

	/**
	 * @return the amount
	 */
	public Double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	/**
	 * @return the personId
	 */
	public Long getPersonId() {
		return personId;
	}

	/**
	 * @param personId the personId to set
	 */
	public void setPersonId(Long personId) {
		this.personId = personId;
	}
	
	
}
