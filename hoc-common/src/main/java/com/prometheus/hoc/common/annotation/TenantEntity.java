/**
 * 
 */
package com.prometheus.hoc.common.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author bubuwork
 * indicator to a table for tenant specific
 */
@Target({java.lang.annotation.ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface TenantEntity {
	
}
