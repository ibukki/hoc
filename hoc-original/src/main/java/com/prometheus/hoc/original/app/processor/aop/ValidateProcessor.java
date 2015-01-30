/**
 * 
 */
package com.prometheus.hoc.original.app.processor.aop;

import static com.prometheus.hoc.original.app.processor.aop.ProcessorAspectOrders.PRECEDENCE_1ST;
import static com.prometheus.hoc.original.app.processor.aop.ProcessorPointcuts.VALIDATE;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.core.annotation.Order;

import com.prometheus.hoc.original.app.context.OriginalContext;
import com.prometheus.hoc.original.app.context.ValidationContext;

/**
 * @author yyang
 * 
 */
@Aspect
@Order(value = PRECEDENCE_1ST)
public class ValidateProcessor {

  @Bean(name = OriginalContext.VALIDATION_CONTEXT, autowire = Autowire.BY_NAME)
  @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
  @Before(VALIDATE)
  public ValidationContext validate(ProceedingJoinPoint point) throws Throwable {
    return new ValidationContext();
  }

}
