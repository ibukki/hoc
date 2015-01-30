package com.prometheus.hoc.payroll.function.impl;

import java.util.List;

import com.bubuwork.bukki.function.Function;
import com.bubuwork.bukki.function.FunctionExecutor;
import com.bubuwork.bukki.function.FunctionParam;
import com.bubuwork.bukki.function.ParamTypeEnum;
import com.bubuwork.bukki.util.CalculationMemory;

/**
 * 
 * Param List
 * 0   wagetypeid
 */
@Function(name="READ_WAGETYPE", params={@FunctionParam(name="wagettypeId",type=ParamTypeEnum.VAR)},
		  description="Read wagetype value from employee master data")
public class PrimaryWageTypeReader implements FunctionExecutor{

	public Object execute(List paramList, CalculationMemory meomory) {
		return 500;
	}

	public String getName() {
		return "READ_WAGETYPE";
	}
	
}
