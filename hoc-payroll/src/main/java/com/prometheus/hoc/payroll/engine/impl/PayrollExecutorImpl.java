package com.prometheus.hoc.payroll.engine.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.util.StringUtils;

import com.bubuwork.bukki.function.FunctionExecutor;
import com.bubuwork.bukki.intro.CalculationRunner;
import com.prometheus.hoc.payroll.engine.PayrollExecutor;
import com.prometheus.hoc.payroll.engine.bean.ExecutionResult;
import com.prometheus.hoc.payroll.engine.exception.SchemaNotFoundException;
import com.prometheus.hoc.payroll.engine.util.GlobalVariableConstants;
import com.prometheus.hoc.payroll.function.ConfFunction;
import com.prometheus.hoc.payroll.function.rest.FunctionService;
import com.prometheus.hoc.payroll.schema.rest.SchemaConfService;
import com.prometheus.hoc.payroll.schema.vo.PayrollSchema;
import com.prometheus.hoc.payroll.schema.vo.PayrollSchemaParam;
import com.prometheus.hoc.payroll.schema.vo.PayrollSchemaStep;

public class PayrollExecutorImpl implements PayrollExecutor{
	
	/**
	 * schema configuration service
	 */
	private SchemaConfService configService;
	
	private static Logger logger = Logger.getLogger(PayrollExecutorImpl.class);
	
	public PayrollExecutorImpl(SchemaConfService configService){
		this.configService = configService;
	}
	
	public ExecutionResult execute(String schemaConfigId, Long employeeId) throws SchemaNotFoundException, IOException {
		
		ExecutionResult executionResult = new ExecutionResult();
		PayrollSchema schema = this.configService.getSchema(schemaConfigId);
		if(schema == null){
			throw new SchemaNotFoundException("schema id: " + schemaConfigId + " is not found");
		}
		
		CalculationRunner calculationRunner = new CalculationRunner();
		this.addFunctionImplToRunner(calculationRunner);
		calculationRunner.addGloabalVariable(GlobalVariableConstants.G_VAR_SCHEMA_CONFIG_ID, schemaConfigId);
		calculationRunner.addGloabalVariable(GlobalVariableConstants.G_VAR_EMPLOYEE_ID, employeeId);
		
		StringBuffer schemaFormula = new StringBuffer();
		List<PayrollSchemaStep> steps = schema.getStepList();
		List<String> globalOParamList = new ArrayList<String>();
		if(steps != null && steps.size() > 0){
			for (PayrollSchemaStep step : steps) {
				//List<PayrollSchemaParam> inputParams = step.getStepInput();
				List<PayrollSchemaParam> outputParams = step.getStepOutput();
				if(outputParams != null && outputParams.size() > 0){
					for (PayrollSchemaParam payrollSchemaParam : outputParams) {
						globalOParamList.add(payrollSchemaParam.getParamName());
						
						String formula = payrollSchemaParam.getFormula();
						if(!StringUtils.isEmpty(formula)){
							schemaFormula.append(formula);
						}
					}
				}
				
			}
		}
		if(!StringUtils.isEmpty(schemaFormula.toString())){
			Date startDate = new Date();
			Map calculationResult = calculationRunner.executeCalculation(new ByteArrayInputStream(schemaFormula.toString().getBytes()));
			Date endDate = new Date();
			Map valueMap = new HashMap();
			if(calculationResult != null){
				Set<?> keySet = calculationResult.keySet();
				for (Object key : keySet) {
					if(globalOParamList.contains(key)){
						valueMap.put(key, calculationResult.get(key));
					}
				}
			}
			
			executionResult.setEmployeeId(employeeId);
			executionResult.setExecuteStartDate(startDate);
			executionResult.setExecuteEndDate(endDate);
			executionResult.setOpParams(globalOParamList);
			executionResult.setItemMap(valueMap);
		}
		
		return executionResult;
	}
	
	/**
	 * Add function implementation to the runner
	 * @param calculationRunner
	 */
	private void addFunctionImplToRunner(CalculationRunner calculationRunner) {
		FunctionService functionService = new FunctionService();
		List<ConfFunction> funcList = functionService.list();
		if(funcList != null && funcList.size() > 0){
			for (ConfFunction confFunction : funcList) {
				String fName = confFunction.getClassName();
				try {
					Class<?> functionClaz = Class.forName(fName);
					FunctionExecutor executor = (FunctionExecutor) functionClaz.newInstance();
					calculationRunner.addFunctionImpl(executor);
				} catch (Exception e) {
					logger.error("failed to add the function with name :" + fName + " to the runner", e);
				}
			}
		}
	}
	
	
	
}
