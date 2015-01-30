package com.prometheus.hoc.payroll.variable.rest;

import java.util.List;
import java.util.Locale;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.payroll.variable.dao.PayrollVariableDao;
import com.prometheus.hoc.payroll.variable.eo.PayrollVariableGrpEO;
import com.prometheus.hoc.payroll.variable.vo.PYVariableEntry;
import com.prometheus.hoc.payroll.variable.vo.PayrollVariable;
import com.prometheus.hoc.payroll.variable.vo.PayrollVariableVO;

@Component
@Path("variable")
public class PayrollVariableService {
	
	private static Logger logger = Logger.getLogger(PayrollVariableService.class);
	
	@Autowired
	private PayrollVariableDao confDao;
	
	@GET
	@Path("list")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<?> list(@QueryParam("locale") String locale){
		if(StringUtils.isEmpty(locale)){
			locale = Locale.US.toString();
		}
		return confDao.getConfiguration(locale);
	}
	
	@GET
	@Path("groups")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PayrollVariableGrpEO> getGroups(){
		return confDao.getConfigGroups();
	}
	
	@GET
	@Path("collection")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<PYVariableEntry> groupList(@QueryParam("locale") String locale){
		if(StringUtils.isEmpty(locale)){
			locale = Locale.US.toString();
		}
		return confDao.groupConfiguration(locale);
	}
	
	@POST
	@Path("read")
	@Produces({ MediaType.APPLICATION_JSON })
	public PayrollVariable readVariable(PayrollVariable variable){
		return confDao.readVariable(variable);
	}
	
	@POST
	@Path("save")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public int save(List<PayrollVariableVO> configList){
		return confDao.saveConfigruation(configList);
	}
	
}
