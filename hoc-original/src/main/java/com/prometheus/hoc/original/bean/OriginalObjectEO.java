package com.prometheus.hoc.original.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Table;

import com.prometheus.hoc.original.EffectiveStatus;
import com.prometheus.hoc.original.OriginalObject;

@Entity
@SuppressWarnings("serial")
@Table(name = "original_object")
public class OriginalObjectEO extends GeneralEO implements OriginalObject {

  private Long internalId;

  private String code;

  private String name;

  private Date effectiveStartDate;

  private Date effectiveEndDate;

  private EffectiveStatus status;

  private Long transactionSequence;

  private OOTypeEO type;

  private SysFieldsEO sysFields;

  private CustFieldsEO custFields;

  private TranslateFieldsEO translateFields;

  private List<OORelationEO> relations = new ArrayList<OORelationEO>();

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.prometheus.hoc.original.EffectiveDated#setEffectiveStartDate(java.util
   * .Date)
   */
  public void setEffectiveStartDate(Date date) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.EffectiveDated#getEffectiveStartDate()
   */
  public Date getEffectiveStartDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.prometheus.hoc.original.EffectiveDated#setEffectiveEndDate(java.util
   * .Date)
   */
  public void setEffectiveEndDate(Date date) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.EffectiveDated#getEffectiveEndDate()
   */
  public Date getEffectiveEndDate() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.prometheus.hoc.original.EffectiveDated#setEffectiveStatus(com.prometheus
   * .hoc.original.EffectiveStatus)
   */
  public void setEffectiveStatus(EffectiveStatus status) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.EffectiveDated#getEffectiveStatus()
   */
  public EffectiveStatus getEffectiveStatus() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.prometheus.hoc.original.EffectiveDated#setTransactionSequence(java.
   * lang.Long)
   */
  public void setTransactionSequence(Long transactionSequence) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.EffectiveDated#getTransactionSequence()
   */
  public Long getTransactionSequence() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#setId(java.lang.Long)
   */
  public void setId(Long id) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#getId()
   */
  public Long getId() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#setTypeId(java.lang.Long)
   */
  public void setTypeId(Long typeId) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#getTypeId()
   */
  public Long getTypeId() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.prometheus.hoc.original.OriginalObject#setInternalId(java.lang.Long)
   */
  public void setInternalId(Long internalId) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#getInternalId()
   */
  public Long getInternalId() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#setCode(java.lang.String)
   */
  public void setCode(String code) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#getCode()
   */
  public String getCode() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#setName(java.lang.String)
   */
  public void setName(String name) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#getName()
   */
  public String getName() {
    // TODO Auto-generated method stub
    return null;
  }

  /*
   * (non-Javadoc)
   * 
   * @see
   * com.prometheus.hoc.original.OriginalObject#setParentObject(com.prometheus
   * .hoc.original.OriginalObject)
   */
  public void setParentObject(OriginalObject parent) {
    // TODO Auto-generated method stub

  }

  /*
   * (non-Javadoc)
   * 
   * @see com.prometheus.hoc.original.OriginalObject#getParentObject()
   */
  public OriginalObject getParentObject() {
    // TODO Auto-generated method stub
    return null;
  }

}
