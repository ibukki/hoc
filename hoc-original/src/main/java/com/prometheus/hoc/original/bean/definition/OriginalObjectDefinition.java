package com.prometheus.hoc.original.bean.definition;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class OriginalObjectDefinition extends GeneralDefinition implements Serializable {

  private String name;
  
  private List<OORelationDefinition> relationDefs = new ArrayList<OORelationDefinition>();
}
  