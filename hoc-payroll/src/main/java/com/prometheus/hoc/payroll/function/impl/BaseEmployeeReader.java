package com.prometheus.hoc.payroll.function.impl;

/**
 * base class to read employee information
 * @author ibukki
 *
 */
public class BaseEmployeeReader {
	
	private Long employeeId;
	
	public BaseEmployeeReader(Long employeeId){
		this.employeeId = employeeId;
	}
	
	/**
	 * get employee type
	 * monthly pay
	 * hourly pay
	 * @return
	 */
	public int getEmployeeType(){
		return 1;
	}
	
	/**
	 * get employe group
	 * @return
	 */
	public Long getEmployeeGroup(){
		return 1L;
	}
	 
}
