package com.prometheus.hoc.payroll.function.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.stereotype.Component;

import com.prometheus.hoc.payroll.function.ConfFunction;
import com.prometheus.hoc.payroll.function.FunctionImplScanner;


@Component
@Path("function")
public class FunctionService {
	
	@GET
	@Path("list")
	@SuppressWarnings("unchecked")
	@Produces({ MediaType.APPLICATION_JSON })
	public List<ConfFunction> list(){
		List<ConfFunction> flist = new ArrayList<ConfFunction>();
		FunctionImplScanner scanner = new FunctionImplScanner();
		scanner.addPackageToScanList("com.prometheus.hoc.payroll.function.impl");
		
		flist = scanner.scan();
		return flist;
	}
}
