package com.prometheus.hoc.payroll.function.impl;

import java.util.List;

import com.bubuwork.bukki.function.Function;
import com.bubuwork.bukki.function.FunctionExecutor;
import com.bubuwork.bukki.util.CalculationMemory;
import com.prometheus.hoc.payroll.engine.util.GlobalVariableConstants;

@Function(name=EmployeeGroupReader.FUNC_NAME, description = "read employee's group")
public class EmployeeGroupReader implements FunctionExecutor{
	
	public static final String FUNC_NAME = "READ_EMP_GROUP";
	
	public Object execute(List paramList, CalculationMemory memory) {
		
		Long employeeId = (Long) memory.getVariable(GlobalVariableConstants.G_VAR_EMPLOYEE_ID);
		BaseEmployeeReader baseEmpReader = new BaseEmployeeReader(employeeId);
		return baseEmpReader.getEmployeeGroup();
		
	}

	public String getName() {
		return FUNC_NAME;
	}
	
}
