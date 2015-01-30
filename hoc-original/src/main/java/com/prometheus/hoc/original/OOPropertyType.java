package com.prometheus.hoc.original;

public enum OOPropertyType {

  STRING,
  NUMBER {
    boolean autoGenerate;
    int precision;
  },
  BOOLEAN,
  ENUM {
    Enum<?> getSourceClass() {
      return null;
    }
  },
  DATE,
  LOB,
  OO;

}
