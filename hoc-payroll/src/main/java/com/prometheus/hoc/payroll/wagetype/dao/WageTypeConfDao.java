package com.prometheus.hoc.payroll.wagetype.dao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;











import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.payroll.wagetype.eo.WageTypeConf;
import com.prometheus.hoc.payroll.wagetype.vo.WageTypeConfVO;
import com.prometheus.hoc.payroll.wagetype.vo.WageTypeSearch;

@Repository
public class WageTypeConfDao extends BaseHibernateDao{
	/**
	 * 
	 * @return
	 */
	public int deleteAll(List<String> wagetypeIdList){
		Session session = this.getSession();
		int res = -1;
		Transaction t = session.beginTransaction();
		try {
			for (String wagetypeId : wagetypeIdList) {
				Object obj = session.get(WageTypeConf.class, wagetypeId);
				if(obj != null){
					session.delete(obj);
				}
			}
			session.flush();
			t.commit();
			res = 1;
		} catch (HibernateException e) {
			t.rollback();
		}
		return res;
	}
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public List<WageTypeConf> getWageTypeByType(String type) {
		
		List<WageTypeConf> wList = new ArrayList<WageTypeConf>();
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Criteria criteria = session.createCriteria(WageTypeConf.class);
			criteria.add(Restrictions.eq("type", type.charAt(0)));
			
			wList = criteria.list();
			session.flush();
			t.commit();
		}catch(HibernateException e){
			t.rollback();
		}
		return wList;
	}
	
	/**
	 * 
	 * @param search
	 * @return
	 */
	public List<WageTypeConf> searchWageTypeConf(WageTypeSearch search) {
		List<WageTypeConf> wList = new ArrayList<WageTypeConf>();
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Criteria criteria = session.createCriteria(WageTypeConf.class);
			if(!StringUtils.isEmpty(search.getWagetypeId())){
				criteria.add(Restrictions.eq("wagetypeId", search.getWagetypeId()));
			}
			if(!StringUtils.isEmpty(search.getDescr())){
				criteria.add(Restrictions.like("description", "%"+search.getDescr()+"%"));
			}
			
			if(!StringUtils.isEmpty(search.getType())){
				criteria.add(Restrictions.eq("type", search.getType().charAt(0)));
			}
			
			wList = criteria.list();
			session.flush();
			t.commit();
		}catch(HibernateException e){
			t.rollback();
		}
		return wList;
		
	}

	public List<WageTypeConfVO> getWageTypeConfByIds(List<String> wageTypeIdList) {
		List<WageTypeConf> wList = new ArrayList<WageTypeConf>();
		Session session = this.getSession();
		Transaction t = session.beginTransaction();
		
		try{
			Criteria criteria = session.createCriteria(WageTypeConf.class);
			criteria.add(Restrictions.in("wagetypeId", wageTypeIdList));
			wList = criteria.list();
			session.flush();
			t.commit();
		}catch(HibernateException e){
			t.rollback();
		}
		return this.convertFromDBtoUI(wList);
		
	}
	
	public List<WageTypeConfVO> convertFromDBtoUI(List<WageTypeConf> dbList){
		
		List<WageTypeConfVO> wageTypeVOList = new ArrayList<WageTypeConfVO>();
		
		for (Iterator<?> iterator = dbList.iterator(); iterator.hasNext();) {
			WageTypeConf wagetypeEO = (WageTypeConf) iterator.next();
			WageTypeConfVO confVO = new WageTypeConfVO();
			try {
				BeanUtils.copyProperties(confVO, wagetypeEO);
			} catch (Exception e) {
				//do nothing
			}
			if(wagetypeEO.getType() == 'P'){
				confVO.setTypeText("Primary");
			}else{
				confVO.setTypeText("Secondary");
			}
			wageTypeVOList.add(confVO);
		}
		
		return wageTypeVOList;
	}
	
	
}
