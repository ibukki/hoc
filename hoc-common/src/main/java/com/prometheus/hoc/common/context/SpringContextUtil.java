package com.prometheus.hoc.common.context;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextUtil implements ApplicationContextAware {
private static ApplicationContext applicationContext;     

	public void setApplicationContext(ApplicationContext context) throws BeansException {
	  applicationContext = context;
	}
	
	public static ApplicationContext getApplicationContext() {
	  return applicationContext;
	}

}