package com.prometheus.hoc.original.bean;

import java.io.Serializable;
import java.util.Date;

/**
 * General entity object
 * @author yyang
 *
 */
@SuppressWarnings("serial")
public abstract class GeneralEO implements Serializable {

  /** rowId of table - PK */
  private Long id;

  /** user's id who created current record */
  private String createdBy;

  /** date when created current record */
  private Date createdDate;

  /** user's id who last modified current record */
  private String lastModifiedBy;

  /** date when current record was last modified */
  private Date lastModifiedDate;

  /**
   * @return the id
   */
  public Long getId() {
    return id;
  }

  /**
   * @param id the id to set
   */
  public void setId(Long id) {
    this.id = id;
  }

  /**
   * @return the createdBy
   */
  public String getCreatedBy() {
    return createdBy;
  }

  /**
   * @param createdBy the createdBy to set
   */
  public void setCreatedBy(String createdBy) {
    this.createdBy = createdBy;
  }

  /**
   * @return the createdDate
   */
  public Date getCreatedDate() {
    return createdDate;
  }

  /**
   * @param createdDate the createdDate to set
   */
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
  }

  /**
   * @return the lastModifiedBy
   */
  public String getLastModifiedBy() {
    return lastModifiedBy;
  }

  /**
   * @param lastModifiedBy the lastModifiedBy to set
   */
  public void setLastModifiedBy(String lastModifiedBy) {
    this.lastModifiedBy = lastModifiedBy;
  }

  /**
   * @return the lastModifiedDate
   */
  public Date getLastModifiedDate() {
    return lastModifiedDate;
  }

  /**
   * @param lastModifiedDate the lastModifiedDate to set
   */
  public void setLastModifiedDate(Date lastModifiedDate) {
    this.lastModifiedDate = lastModifiedDate;
  }
}
