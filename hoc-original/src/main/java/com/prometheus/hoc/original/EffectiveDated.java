package com.prometheus.hoc.original;

import java.util.Date;

/**
 * Effective dated interface
 * 
 * @author yyang
 * 
 */
public interface EffectiveDated {

  void setEffectiveStartDate(Date date);

  Date getEffectiveStartDate();

  void setEffectiveEndDate(Date date);

  Date getEffectiveEndDate();

  void setEffectiveStatus(EffectiveStatus status);

  EffectiveStatus getEffectiveStatus();

  void setTransactionSequence(Long transactionSequence);

  Long getTransactionSequence();
}
