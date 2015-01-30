package com.prometheus.hoc.payroll.function.impl;

import java.util.List;

import com.bubuwork.bukki.function.Function;
import com.bubuwork.bukki.function.FunctionExecutor;
import com.bubuwork.bukki.function.FunctionParam;
import com.bubuwork.bukki.function.ParamTypeEnum;
import com.bubuwork.bukki.util.CalculationMemory;
import com.prometheus.hoc.common.context.SpringContextUtil;
import com.prometheus.hoc.payroll.engine.util.GlobalVariableConstants;
import com.prometheus.hoc.payroll.variable.dao.PayrollVariableDao;
import com.prometheus.hoc.payroll.variable.vo.PayrollVariable;

/**
 * 
 * Param List
 * 0   configType
 * 1   eegroupId
 * 2   SEQUENCE
 * 4   'AMOUNT1'|'AMOUNT2'|'AMOUNT3'|'AMOUNT4'
 */
@Function(name="READ_CONF", params={@FunctionParam(name="configType",type=ParamTypeEnum.STRING),
									@FunctionParam(name="configGrpId",type=ParamTypeEnum.VAR),
									@FunctionParam(name="sequence",type=ParamTypeEnum.NUMBER),
									@FunctionParam(name="columnName",type=ParamTypeEnum.STRING)},
		  description="Read payroll variable configuration")
public class VariableConfigurationReader implements FunctionExecutor{

	public Object execute(List paramList, CalculationMemory memory) {
		
		String configTypeId = (String)paramList.get(0);
		Long configgrpId = (Long) paramList.get(1);
		Long sequence = (Long) paramList.get(2);
		String columnName = (String) paramList.get(3);
		
		return this.getValueFromDataBase(configTypeId, configgrpId, sequence, columnName);
	}

	public String getName() {
		return "READ_CONF";
	}
	
	private Double getValueFromDataBase(String configTypeId, Long configgrpId, Long sequence, String columnName){
		
		PayrollVariableDao configDao = SpringContextUtil.getApplicationContext().getBean(PayrollVariableDao.class);
		PayrollVariable variable = new PayrollVariable();
		variable.setConfigTypeId(configTypeId);
		variable.setConfigGrpId(configgrpId);
		variable.setSequence(sequence.intValue());
		variable.setColumnName(columnName);
		variable = configDao.readVariable(variable);
		
		return variable.getAmount();
	}
}
