package com.prometheus.hoc.payroll.engine;

import java.io.IOException;

import com.prometheus.hoc.payroll.engine.bean.ExecutionResult;
import com.prometheus.hoc.payroll.engine.exception.SchemaNotFoundException;

public interface PayrollExecutor {
	
	/**
	 * execute
	 * @param param
	 * @throws SchemaNotFoundException 
	 * @throws IOException 
	 */
	public ExecutionResult execute(String schemaConfigId, Long employeeId) throws SchemaNotFoundException, IOException;
	
}
