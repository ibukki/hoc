package com.prometheus.hoc.common.db.hibernate;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.prometheus.hoc.common.context.GlobalContext;
import com.prometheus.hoc.common.context.SpringContextUtil;

public class CurrentTenantIdentifierResolverImpl implements CurrentTenantIdentifierResolver{
	
	public String resolveCurrentTenantIdentifier() {
		
		GlobalContext globalContext = SpringContextUtil.getApplicationContext().getBean(GlobalContext.class);
		if(globalContext != null){
			if(globalContext.getCompanyBean() != null){
				return ConnectionProviderUtils.MASTER_SCHEMA_NAME + globalContext.getCompanyBean().getCompanyId(); 
			}
		}
		return ConnectionProviderUtils.MASTER_SCHEMA_NAME;
	}

	public boolean validateExistingCurrentSessions() {
		return true;
	}
	
}
