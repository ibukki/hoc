package com.prometheus.hoc.tenant.eo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="global_tenant")
public class TenantEO {
	
	@Id
	@Column(name="company_id", length=50)
	private String companyId;
	
	@Column(name="company_name", length=300)
	private String companyName;
	
	@Column(name="status", length=1, precision=1)
	private int status;
	
	@Column(name="seat")
	private int seat;
	
	@Column(name="create_date")
	private Date createDate;
	
	@Column(name="super_account")
	private Long superAccount;

	/**
	 * @return the companyId
	 */
	public String getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @return the seat
	 */
	public int getSeat() {
		return seat;
	}

	/**
	 * @param seat the seat to set
	 */
	public void setSeat(int seat) {
		this.seat = seat;
	}

	/**
	 * @return the superAccount
	 */
	public Long getSuperAccount() {
		return superAccount;
	}

	/**
	 * @param superAccount the superAccount to set
	 */
	public void setSuperAccount(Long superAccount) {
		this.superAccount = superAccount;
	}
	
}
