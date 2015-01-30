package com.prometheus.hoc.masterdata.person.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.masterdata.person.eo.PersonEO;

@Component
public class PersonDao {
	
	@Autowired
	private BaseHibernateDao baseHibernateDao;
	
	private PersonEO savePerson(PersonEO peo){
		this.baseHibernateDao.saveorUpdate(peo);
		return peo;
	}
}
