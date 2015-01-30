package com.prometheus.hoc.original.bean;

import java.io.Serializable;

@SuppressWarnings("serial")
public class OORelationEO extends GeneralEO implements Serializable {

  private OOTypeEO srouceType;

  private OriginalObjectEO sourceObject;

  private OOTypeEO targetType;

  private OriginalObjectEO targetObject;

  /**
   * @return the srouceType
   */
  public OOTypeEO getSrouceType() {
    return srouceType;
  }

  /**
   * @param srouceType the srouceType to set
   */
  public void setSrouceType(OOTypeEO srouceType) {
    this.srouceType = srouceType;
  }

  /**
   * @return the sourceObject
   */
  public OriginalObjectEO getSourceObject() {
    return sourceObject;
  }

  /**
   * @param sourceObject the sourceObject to set
   */
  public void setSourceObject(OriginalObjectEO sourceObject) {
    this.sourceObject = sourceObject;
  }

  /**
   * @return the targetType
   */
  public OOTypeEO getTargetType() {
    return targetType;
  }

  /**
   * @param targetType the targetType to set
   */
  public void setTargetType(OOTypeEO targetType) {
    this.targetType = targetType;
  }

  /**
   * @return the targetObject
   */
  public OriginalObjectEO getTargetObject() {
    return targetObject;
  }

  /**
   * @param targetObject the targetObject to set
   */
  public void setTargetObject(OriginalObjectEO targetObject) {
    this.targetObject = targetObject;
  }
}
