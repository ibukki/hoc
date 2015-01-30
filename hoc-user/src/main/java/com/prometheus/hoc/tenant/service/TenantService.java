package com.prometheus.hoc.tenant.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.tenant.eo.TenantEO;

@Service
public class TenantService {
	
	@Autowired
	private BaseHibernateDao baseHibernateDao;
	
	public void saveTenant(TenantEO tenant) {
		baseHibernateDao.saveorUpdate(tenant);
	}
	
}
