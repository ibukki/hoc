package com.prometheus.hoc.payroll.variable.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.payroll.variable.eo.PayrollVariableDescrEO;
import com.prometheus.hoc.payroll.variable.eo.PayrollVariableEO;
import com.prometheus.hoc.payroll.variable.eo.PayrollVariableGrpEO;
import com.prometheus.hoc.payroll.variable.vo.PYVariableEntry;
import com.prometheus.hoc.payroll.variable.vo.PYVariableEntryItem;
import com.prometheus.hoc.payroll.variable.vo.PayrollVariable;
import com.prometheus.hoc.payroll.variable.vo.PayrollVariableVO;

/**
 * 
 * @author bubuwork
 * 
 */
@Repository
public class PayrollVariableDao extends BaseHibernateDao {

	@SuppressWarnings("unchecked")
	public List<PayrollVariableVO> getConfiguration(String locale) {
		List<PayrollVariableVO> voList = new ArrayList<PayrollVariableVO>();
		if (locale == null) {
			locale = "en";
		}
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		//try out join selection
		try{
			
			Query query = session.createQuery("select conf, confDesc, confGrp from PayrollVariableEO conf, PayrollVariableDescrEO confDesc"
					+ " ,PayrollVariableGrpEO confGrp where conf.configType = confDesc.configType and confDesc.locale = :locale and conf.configGrpId = confGrp.grpId");
			
			query.setString("locale", locale);
			
			List result = query.list();
			if(result != null){
				Iterator it = result.iterator();       
				while (it.hasNext()) {       
				   Object[] tuple = (Object[]) it.next();       
				   PayrollVariableEO confEO = (PayrollVariableEO) tuple[ 0 ];       
				   PayrollVariableDescrEO confDescrEO = (PayrollVariableDescrEO) tuple[ 1 ];
				   PayrollVariableGrpEO confGrpEO = (PayrollVariableGrpEO)tuple[2];
				   
				   PayrollVariableVO vo = new PayrollVariableVO();
					try {
						BeanUtils.copyProperties(vo, confEO);
					} catch (Exception e) {
						// to do
					}
					
					if (confDescrEO != null) {
						try {
							BeanUtils.copyProperties(vo, confDescrEO);
						} catch (Exception e) {
							// to do
						}
					}
					if( confGrpEO != null){
						vo.setConfigGrpDescr(confGrpEO.getDescription());
					}
					voList.add(vo);
				}
			}
			session.flush();
			ts.commit();
		}catch(HibernateException e){
			ts.rollback();
		}
		return voList;
	}

	public int saveConfigruation(List<PayrollVariableVO> confList) {
		int res = -1;

		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		try {
			if (confList != null) {
				for (PayrollVariableVO vo : confList) {
					PayrollVariableEO confEO = new PayrollVariableEO();
					try {
						BeanUtils.copyProperties(confEO, vo);
					} catch (Exception e) {
						// DO Nothing
					}

					PayrollVariableDescrEO descrEO = new PayrollVariableDescrEO();
					descrEO.setConfigType(vo.getConfigType());
					descrEO.setAmount1Descr(vo.getAmount1Descr());
					descrEO.setAmount2Descr(vo.getAmount2Descr());
					descrEO.setAmount3Descr(vo.getAmount3Descr());
					descrEO.setAmount4Descr(vo.getAmount4Descr());
					descrEO.setLocale(vo.getLocale());
					
					Long confGrpId = confEO.getConfigGrpId();
					PayrollVariableGrpEO grp = null;
					try{
						grp = (PayrollVariableGrpEO) session.load(PayrollVariableGrpEO.class, confGrpId);
						if(confGrpId != null && ( grp == null || grp.getGrpId() == null)){
							grp = new PayrollVariableGrpEO();
							grp.setGrpId(confGrpId);
							grp.setDescription(vo.getConfigGrpDescr());
							session.save(grp);
						}
					}catch(HibernateException e){
						//create a new one!
						grp = new PayrollVariableGrpEO();
						grp.setGrpId(confGrpId);
						grp.setDescription(vo.getConfigGrpDescr());
						session.save(grp);
					}
					
					session.saveOrUpdate(confEO);
					session.saveOrUpdate(descrEO);
				}
				session.flush();
				ts.commit();
				res = 1;
			}
		} catch (HibernateException e) {
			ts.rollback();
		} finally {
		}
		return res;

	}

	public PayrollVariable readVariable(PayrollVariable variable) {
		Session session = this.getSession();
		Transaction ts = session.beginTransaction();
		
		try{
			
			Criteria criteria = session.createCriteria(PayrollVariableEO.class);
			criteria.add(Restrictions.eq("configType", variable.getConfigTypeId()));
			criteria.add(Restrictions.eq("configGrpId", variable.getConfigGrpId()));
			criteria.add(Restrictions.eq("sequence", variable.getSequence()));
			criteria.setProjection(Projections.property(variable.getColumnName()));
			
			Double amount = (Double) criteria.uniqueResult();
			
			variable.setAmount(amount);
			
			session.flush();
			ts.commit();
		}catch(HibernateException e){
			ts.rollback();
		}
		
		return variable;
	}

	public List<PYVariableEntry> groupConfiguration(String locale) {
		
		List<PYVariableEntry> entryList = new ArrayList<PYVariableEntry>();
		Map<String, PYVariableEntry> entryMap = new HashMap<String, PYVariableEntry>();
		List<PayrollVariableVO> confList = this.getConfiguration(locale);
		for (PayrollVariableVO payrollConfVO : confList) {
			PYVariableEntry entry = (PYVariableEntry) entryMap.get(payrollConfVO.getConfigType());
			if(entry == null){
				entry = new PYVariableEntry();
				entry.setAmount1Descr(payrollConfVO.getAmount1Descr());
				entry.setAmount2Descr(payrollConfVO.getAmount2Descr());
				entry.setAmount3Descr(payrollConfVO.getAmount3Descr());
				entry.setAmount4Descr(payrollConfVO.getAmount4Descr());
				entry.setConfigTypeDescr(payrollConfVO.getConfigGrpDescr());
				entry.setConfigGrpId(payrollConfVO.getConfigGrpId());
				entry.setConfigType(payrollConfVO.getConfigType());
				
				List<PYVariableEntryItem> itemList = new ArrayList<PYVariableEntryItem>();
				entry.setItems(itemList);
			}
			PYVariableEntryItem item = new PYVariableEntryItem();
			item.setAmount1(payrollConfVO.getAmount1());
			item.setAmount2(payrollConfVO.getAmount2());
			item.setAmount3(payrollConfVO.getAmount3());
			item.setAmount4(payrollConfVO.getAmount4());
			item.setSequence(payrollConfVO.getSequence());
			entry.getItems().add(item);
			entryMap.put(payrollConfVO.getConfigType(), entry);
		}
		entryList.addAll(entryMap.values());
		
		return entryList;
	}
	
	/**
	 * find all config groups
	 * @return
	 */
	public List<PayrollVariableGrpEO> getConfigGroups() {
		return this.findAll(PayrollVariableGrpEO.class);
	}
}
