package com.prometheus.hoc.original.bean.definition;

import java.io.Serializable;

import com.prometheus.hoc.original.EffectiveDating;

@SuppressWarnings("serial")
public class GeneralDefinition implements Serializable {

  private Long id;
  /** special, mapped to OOTypeEO */
  private Long typeId;
  private Long internalId;
  private String code;
  private String name;
  private EffectiveDating effectiveDating;
  
  private Boolean codeBased;
  private String transformTo;
  
  private String translateLang1;
  private String translateLang2;
  private String translateLang3;
  private String translateLang4;
  private String translateLang5;
}
