package com.prometheus.hoc.original;

import java.io.Serializable;

public interface OriginalObject extends Serializable, EffectiveDated {

  void setId(Long id);

  Long getId();

  void setTypeId(Long typeId);

  Long getTypeId();

  void setInternalId(Long internalId);

  Long getInternalId();

  void setCode(String code);

  String getCode();

  void setName(String name);

  String getName();

  void setParentObject(OriginalObject parent);

  OriginalObject getParentObject();
}
