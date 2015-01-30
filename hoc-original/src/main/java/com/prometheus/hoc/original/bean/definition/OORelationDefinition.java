package com.prometheus.hoc.original.bean.definition;

import java.io.Serializable;

import com.prometheus.hoc.original.OORelationModel;
import com.prometheus.hoc.original.OORelationMultiplicity;

@SuppressWarnings("serial")
public class OORelationDefinition implements Serializable {

  private OORelationModel relationModel;
  private OORelationMultiplicity relationMulti;

}
