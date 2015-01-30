/**
 * 
 */
package com.prometheus.hoc.original.app.processor.aop;

import static com.prometheus.hoc.original.app.processor.aop.ProcessorAspectOrders.PRECEDENCE_2ND;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.AROUND_DELETE;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.POST_DELETE;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.PRE_DELETE;

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
public class DeleteProcessor {

  @Before(PRE_DELETE)
  public Object preDelete(ProceedingJoinPoint point) throws Throwable {
    return point.proceed();
  }

  @AfterReturning(pointcut = POST_DELETE, returning = "retVal")
  public Object postDelete(Object retVal) {
    return retVal;
  }

  @Around(AROUND_DELETE)
  public Object aroundDelete(ProceedingJoinPoint point) throws Throwable {
    return point.proceed();
  }
}
