package com.prometheus.hoc.common.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Example;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * base hibernate dao
 * use spring to do the trick, check the spring-user.xml config
 * do not close the session, again let spring handle it
 * @author ibukki
 *
 */
@Repository
public class BaseHibernateDao {
	
	@Autowired
	private SessionFactory sessionFactory;  
		
	/**
	 * 
	 * @param object
	 * @return
	 */
	public int save(Object object) {
		int res = -1;
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.save(object);
			session.flush();
			ts.commit();
			res = 1;
		} catch (HibernateException e) {
			ts.rollback();
		} finally {
		}
		return res;
	}
	
	public int saveAll(List list){
		int res = -1;
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		for(Object object : list){
			session.saveOrUpdate(object);
		}
		session.flush();
		ts.commit();
		res = 1;
		
		return res;
	}
	
	public int saveorUpdate(Object object) {
		int res = -1;
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.saveOrUpdate(object);
			session.flush();
			ts.commit();
			res = 1;
		} catch (HibernateException e) {
			ts.rollback();
		} finally {
//			session.close();
		}
		return res;
	}
	
	/**
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public Object findById(Class cls, Serializable id) {
		Object obj = null;
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		obj = session.get(cls, id);
		ts.commit();
		return obj;
	}
	
	/**
	 * 
	 * @param object
	 * @return
	 */
	public int udpate(Object object) {
		int res = -1;
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		try {
			session.update(object);
			session.flush();
			ts.commit();
			res = 1;
		} catch (HibernateException e) {
			ts.rollback();
		} finally {
//			session.close();
		}
		return res;
	}
	
	/**
	 * 
	 * @param cls
	 * @param id
	 * @return
	 */
	public int delete(Class cls, Serializable id) {
		int res = -1;
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		Object object = findById(cls, id);
		try {
			session.delete(object);
			session.flush();
			ts.commit();
			res = 1;
		} catch (HibernateException e) {
			ts.rollback();
		} finally {
//			session.close();
		}
		return res;
	}
	
	/**
	 * 
	 * @param cls
	 * @return
	 */
	public List findAll(Class cls) {
		List list = new ArrayList();
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		list = session.createCriteria(cls).list();
		ts.commit();
		return list;
	}
	
	
	/**
	 * 
	 * @param cls
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public List findAll(Class cls, int pageNo, int pageSize) {
		List list = new ArrayList();
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		list = session.createCriteria(cls)
				.setFirstResult((pageNo - 1) * pageSize)
				.setMaxResults(pageSize).list();
		ts.commit();
		return list;
	}
	
	/**
	 * 
	 * @param cls
	 * @param obj
	 * @return
	 */
	public List findByObject(Class cls, Object obj) {
		List list = new ArrayList();
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		list = session.createCriteria(cls).add(Example.create(obj)).list();
		ts.commit();
		return list;
	}
	
	
	/**
	 * 
	 * @return
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}


	/**
	 * @return the sessionFactory
	 */
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}


	/**
	 * @param sessionFactory the sessionFactory to set
	 */
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
}