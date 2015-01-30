package com.prometheus.hoc.payroll.wagetype.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.payroll.wagetype.dao.WageTypeConfDao;
import com.prometheus.hoc.payroll.wagetype.eo.WageTypeConf;
import com.prometheus.hoc.payroll.wagetype.vo.WageTypeConfVO;
import com.prometheus.hoc.payroll.wagetype.vo.WageTypeSearch;


@Component
@Path("wagetypeconf")
public class WageTypeConfService {
	
	private static Logger logger = Logger.getLogger(WageTypeConfService.class);
	
	@Autowired
	private WageTypeConfDao confDao;
	
	
	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<WageTypeConfVO> getWageTypeList(@QueryParam(value="type") String type){
		List<WageTypeConfVO> wageTypeVOList = new ArrayList<WageTypeConfVO>();
		List<WageTypeConf> confList = new ArrayList<WageTypeConf>();
		if(StringUtils.isEmpty(type)){
			confList = confDao.findAll(WageTypeConf.class);
		}else{
			confList = confDao.getWageTypeByType(type);
		}
		
		wageTypeVOList = confDao.convertFromDBtoUI(confList);
		return wageTypeVOList;
	}
	
	@POST
	@Path("search")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public List<WageTypeConfVO> searchWageTypeConfs(WageTypeSearch search){
		
		List<WageTypeConfVO> wageTypeVOList = new ArrayList<WageTypeConfVO>();
		
		List<WageTypeConf> confList = confDao.searchWageTypeConf(search);
		
		wageTypeVOList = confDao.convertFromDBtoUI(confList);
		return wageTypeVOList;
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int save(List<WageTypeConfVO> ppList){
		List<WageTypeConf> eoList = new ArrayList<WageTypeConf>();
		for (WageTypeConfVO vo : ppList) {
			WageTypeConf eo = new WageTypeConf();
			try {
				BeanUtils.copyProperties(eo, vo);
			} catch (Exception e) {
				//do nothing
			}
			eoList.add(eo);
			
		}
		int res = confDao.saveAll(eoList);
		return res;
	}
	
	
	@POST
	@Path("detail")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<WageTypeConfVO> readWageTypeList(List<WageTypeConfVO> ppList){
		List<String> wageTypeIdList = new ArrayList<String>();
		for (WageTypeConfVO wageTypeConfVO : ppList) {
			wageTypeIdList.add(wageTypeConfVO.getWagetypeId());
		}
		
		List<WageTypeConfVO> wageList = confDao.getWageTypeConfByIds(wageTypeIdList);
		return wageList;
	}
	
	
	@POST
	@Path("delete")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int delete(List<WageTypeConfVO> ppList){
		List wagetypeIdList = new ArrayList();
		for (WageTypeConfVO wageTypeConfVO : ppList) {
			wagetypeIdList.add(wageTypeConfVO.getWagetypeId());
		}
		return confDao.deleteAll(wagetypeIdList);
	}
}

