package com.prometheus.hoc.tenant.rest;

import java.util.Date;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.common.bean.SimpleJsonResponse;
import com.prometheus.hoc.tenant.TenantStatusEnum;
import com.prometheus.hoc.tenant.eo.TenantEO;
import com.prometheus.hoc.tenant.service.TenantService;
import com.prometheus.hoc.tenant.vo.TenantVO;
import com.prometheus.hoc.user.rest.UserRestService;


@Component
@Path("company")
public class TenantRest {
	
	@Autowired
	private TenantService tenantService;
	
	private UserRestService userRestService;
	
	@POST
	@Path("apply")
	@Produces({ MediaType.APPLICATION_JSON })
	public SimpleJsonResponse applyForCompanyInstance(TenantVO tenantVO){
		SimpleJsonResponse rsp = new SimpleJsonResponse();
		
		TenantEO tenant = new TenantEO();
		tenant.setCreateDate(new Date());
		tenant.setStatus(TenantStatusEnum.UNDER_REVIEW.value());
		BeanUtils.copyProperties(tenantVO, tenant);
		
		tenantService.saveTenant(tenant);
		
		return rsp;
		
	}
	
}
