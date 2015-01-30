package com.prometheus.hoc.payroll.period.rest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.common.bean.SimpleJsonResponse;
import com.prometheus.hoc.common.dao.BaseHibernateDao;
import com.prometheus.hoc.payroll.period.eo.PayrollPeriodEO;
import com.prometheus.hoc.payroll.period.vo.PayrollPeriodVO;

@Component
@Path("payrollperiod")
public class PayrollPeriodService {
	
	@Autowired
	private BaseHibernateDao baseHibernateDao;
	
	
	@GET
	@Path("list")
	@SuppressWarnings("unchecked")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PayrollPeriodVO> list(){
		List<PayrollPeriodEO> resultList = new ArrayList<PayrollPeriodEO>();
		List<PayrollPeriodVO> resultVOList = new ArrayList<PayrollPeriodVO>();
		resultList = baseHibernateDao.findAll(PayrollPeriodEO.class);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (PayrollPeriodEO eo : resultList) {
			PayrollPeriodVO vo = new PayrollPeriodVO();
			try {
				BeanUtils.copyProperties(vo, eo);
			} catch (Exception e) {
				//do nothing
			}
			//date text
			
			if(eo.getBegda() != null){
				vo.setBegdaTxt(sdf.format(eo.getBegda()));
			}
			if(eo.getEndda() != null){
				vo.setEnddaTxt(sdf.format(eo.getEndda()));
			}
			if(eo.getPayDate() != null){
				vo.setPayDateTxt(sdf.format(eo.getPayDate()));
			}
			resultVOList.add(vo);
		}
		return resultVOList;
	}
	
	@GET
	@Path("single")
	@Produces({ MediaType.APPLICATION_JSON })
	public PayrollPeriodVO getSinglePayrollPeriod(@PathParam("year") int year, 
			@PathParam("sequence") int sequence, @PathParam("type") int type){
		
		PayrollPeriodVO vo = new PayrollPeriodVO();
		
		Session session  = this.baseHibernateDao.getSessionFactory().getCurrentSession();
		org.hibernate.Transaction ts = session.beginTransaction();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		try{
			Criteria criteria = session.createCriteria(PayrollPeriodEO.class);
			criteria.add(Restrictions.eq("pyYear", year));
			criteria.add(Restrictions.eq("paySeq", sequence));
			criteria.add(Restrictions.eq("payType", type));
			
			PayrollPeriodEO eo = (PayrollPeriodEO) criteria.uniqueResult();
			
			try {
				BeanUtils.copyProperties(vo, eo);
			} catch (Exception e) {
				//do nothing
			}
			
			if(eo.getBegda() != null){
				vo.setBegdaTxt(sdf.format(eo.getBegda()));
			}
			if(eo.getEndda() != null){
				vo.setEnddaTxt(sdf.format(eo.getEndda()));
			}
			if(eo.getPayDate() != null){
				vo.setPayDateTxt(sdf.format(eo.getPayDate()));
			}
			
			session.flush();
			ts.commit();
		}catch(HibernateException e){
			ts.rollback();
		}
		
		return vo;
	}
	
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<PayrollPeriodVO> save(List<PayrollPeriodVO> ppList){
		List<PayrollPeriodEO> eoList = new ArrayList<PayrollPeriodEO>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (PayrollPeriodVO vo : ppList) {
			PayrollPeriodEO eo = new PayrollPeriodEO();
			try {
				vo.setBegda(sdf.parse(vo.getBegdaTxt()));
				vo.setEndda(sdf.parse(vo.getEnddaTxt()));
				vo.setPayDate(sdf.parse(vo.getPayDateTxt()));
			} catch (ParseException e) {
				//convert data
			}
			try {
				BeanUtils.copyProperties(eo, vo);
			} catch (Exception e) {
				//do nothing
			}
			eoList.add(eo);
			
		}
		int res = baseHibernateDao.saveAll(eoList);
		if(res > 0){
			return ppList;
		}else{
			return Collections.emptyList();
		}
	}
	
	@GET
	@Path("delete")
	@Produces({ MediaType.APPLICATION_JSON })
	public SimpleJsonResponse deletePayrollPeriod(@PathParam("year") String year, 
			@PathParam("sequence") String sequence, @PathParam("type") String type){
		
		SimpleJsonResponse rsp = new SimpleJsonResponse();
		Session session  = this.baseHibernateDao.getSessionFactory().getCurrentSession();
		org.hibernate.Transaction ts = session.beginTransaction();
		
		try{
			Criteria criteria = session.createCriteria(PayrollPeriodEO.class);
			criteria.add(Restrictions.eq("pyYear", Integer.parseInt(year)));
			if(StringUtils.isNotEmpty(sequence)){
				criteria.add(Restrictions.eq("paySeq", Integer.parseInt(sequence)));
			}
			criteria.add(Restrictions.eq("payType", Integer.parseInt(type)));
			
			List<PayrollPeriodEO> ppList = criteria.list();
			if(ppList != null && ppList.size() > 0){
				for (PayrollPeriodEO payrollPeriodEO : ppList) {
					session.delete(payrollPeriodEO);
				}
			}
			session.flush();
			ts.commit();
			
			rsp.setCode(0);
			rsp.setMsg("success");
		}catch(NumberFormatException e1){
			ts.rollback();
			rsp.setCode(14);
			rsp.setMsg("number format is invalid");
		}catch(HibernateException e){
			ts.rollback();
			rsp.setCode(99);
			rsp.setMsg(e.getMessage());
		}
		return rsp;
	}
		
}
