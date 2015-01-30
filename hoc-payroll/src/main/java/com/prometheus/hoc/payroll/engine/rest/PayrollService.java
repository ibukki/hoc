package com.prometheus.hoc.payroll.engine.rest;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.prometheus.hoc.payroll.engine.PayrollExecutor;
import com.prometheus.hoc.payroll.engine.bean.ExecuteParam;
import com.prometheus.hoc.payroll.engine.bean.ExecutionResult;
import com.prometheus.hoc.payroll.engine.exception.SchemaNotFoundException;
import com.prometheus.hoc.payroll.engine.impl.PayrollExecutorImpl;
import com.prometheus.hoc.payroll.schema.rest.SchemaConfService;

@Component
@Path("py")
public class PayrollService {
	
	@Autowired
	private SchemaConfService confService;
	
	@POST
	@Path("run")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public ExecutionResult run(ExecuteParam param){
		ExecutionResult result = null;
		PayrollExecutor executor = new PayrollExecutorImpl(confService);
		try {
			result = executor.execute(param.getConfigId(), param.getEmployeeIdList().get(0));
		} catch (SchemaNotFoundException e) {
			//to do
		} catch (IOException e) {
			//to do
		}
		return result;
	}
}
