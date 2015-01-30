package com.prometheus.hoc.payroll.engine.bean;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"executeStartDate","executeEndDate"})
public class ExecutionResult implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1175402559764484220L;

	private Date executeStartDate;
	
	private String exeStartDateString;
	
	private Long employeeId;
	
	private Date executeEndDate;
	
	private String exeEndDateString;
	
	private Map<String,ExecutionResultItem> itemMap;
	
	private List<String> opParams;

	/**
	 * @return the executeStartDate
	 */
	public Date getExecuteStartDate() {
		return executeStartDate;
	}

	/**
	 * @param executeStartDate the executeStartDate to set
	 */
	public void setExecuteStartDate(Date executeStartDate) {
		this.executeStartDate = executeStartDate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.exeStartDateString = sdf.format(this.executeStartDate);
	}

	/**
	 * @return the employeeId
	 */
	public Long getEmployeeId() {
		return employeeId;
	}

	/**
	 * @param employeeId the employeeId to set
	 */
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * @return the executeEndDate
	 */
	public Date getExecuteEndDate() {
		return executeEndDate;
	}

	/**
	 * @param executeEndDate the executeEndDate to set
	 */
	public void setExecuteEndDate(Date executeEndDate) {
		this.executeEndDate = executeEndDate;
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.exeEndDateString = sdf.format(this.executeEndDate);
	}

	/**
	 * @return the itemMap
	 */
	public Map<String, ExecutionResultItem> getItemMap() {
		return itemMap;
	}

	/**
	 * @param itemMap the itemMap to set
	 */
	public void setItemMap(Map<String, ExecutionResultItem> itemMap) {
		this.itemMap = itemMap;
	}

	/**
	 * @return the opParams
	 */
	public List<String> getOpParams() {
		return opParams;
	}

	/**
	 * @param opParams the opParams to set
	 */
	public void setOpParams(List<String> opParams) {
		this.opParams = opParams;
	}

	/**
	 * @return the exeStartDateString
	 */
	public String getExeStartDateString() {
		return exeStartDateString;
	}

	/**
	 * @return the exeEndDateString
	 */
	public String getExeEndDateString() {
		return exeEndDateString;
	}
	
}
