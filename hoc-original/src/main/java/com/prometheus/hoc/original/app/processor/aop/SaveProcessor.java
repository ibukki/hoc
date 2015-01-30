/**
 * 
 */
package com.prometheus.hoc.original.app.processor.aop;

import static com.prometheus.hoc.original.app.processor.aop.ProcessorAspectOrders.PRECEDENCE_2ND;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.AROUND_SAVE;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.POST_SAVE;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.PRE_SAVE;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;

/**
 * @author yyang
 * 
 */
@Aspect
@Order(value = PRECEDENCE_2ND)
public class SaveProcessor {

  @Before(PRE_SAVE)
  public Object preSave(ProceedingJoinPoint point) throws Throwable {
    return point.proceed();
  }

  @AfterReturning(pointcut = POST_SAVE, returning = "retVal")
  public Object postSave(Object retVal) {
    return retVal;
  }

  @Around(AROUND_SAVE)
  public Object aroundSave(ProceedingJoinPoint point) throws Throwable {
    return point.proceed();
  }

}
