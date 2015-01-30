package com.prometheus.hoc.original;

public enum EffectiveDating {

  /** none effective dating */
  NEVER,
  /** change on a daily basis */
  DAILY,
  /**
   * always create a new effective version
   * regardless how many changes per day
   */
  ALWAYS,
  /**
   * used by composite objects that follows
   * parent's effective dating
   */
  SUPPORTS;
}
