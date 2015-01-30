package com.prometheus.hoc.payroll.variable.vo;

import java.io.Serializable;
import java.util.List;

public class PYVariableEntry implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -4123030598968686169L;

	private String configType;
	
	private Long configGrpId;
	
	private String configTypeDescr;
	
	private String locale;
	
	private String amount1Descr;
	
	private String amount2Descr;
	
	private String amount3Descr;
	
	private String amount4Descr;
	
	private List<PYVariableEntryItem> items;

	/**
	 * @return the configType
	 */
	public String getConfigType() {
		return configType;
	}

	/**
	 * @param configType the configType to set
	 */
	public void setConfigType(String configType) {
		this.configType = configType;
	}

	/**
	 * @return the configGrpId
	 */
	public Long getConfigGrpId() {
		return configGrpId;
	}

	/**
	 * @param configGrpId the configGrpId to set
	 */
	public void setConfigGrpId(Long configGrpId) {
		this.configGrpId = configGrpId;
	}

	/**
	 * @return the configTypeDescr
	 */
	public String getConfigTypeDescr() {
		return configTypeDescr;
	}

	/**
	 * @param configTypeDescr the configTypeDescr to set
	 */
	public void setConfigTypeDescr(String configTypeDescr) {
		this.configTypeDescr = configTypeDescr;
	}

	/**
	 * @return the locale
	 */
	public String getLocale() {
		return locale;
	}

	/**
	 * @param locale the locale to set
	 */
	public void setLocale(String locale) {
		this.locale = locale;
	}

	/**
	 * @return the amount1Descr
	 */
	public String getAmount1Descr() {
		return amount1Descr;
	}

	/**
	 * @param amount1Descr the amount1Descr to set
	 */
	public void setAmount1Descr(String amount1Descr) {
		this.amount1Descr = amount1Descr;
	}

	/**
	 * @return the amount2Descr
	 */
	public String getAmount2Descr() {
		return amount2Descr;
	}

	/**
	 * @param amount2Descr the amount2Descr to set
	 */
	public void setAmount2Descr(String amount2Descr) {
		this.amount2Descr = amount2Descr;
	}

	/**
	 * @return the amount3Descr
	 */
	public String getAmount3Descr() {
		return amount3Descr;
	}

	/**
	 * @param amount3Descr the amount3Descr to set
	 */
	public void setAmount3Descr(String amount3Descr) {
		this.amount3Descr = amount3Descr;
	}

	/**
	 * @return the amount4Descr
	 */
	public String getAmount4Descr() {
		return amount4Descr;
	}

	/**
	 * @param amount4Descr the amount4Descr to set
	 */
	public void setAmount4Descr(String amount4Descr) {
		this.amount4Descr = amount4Descr;
	}

	/**
	 * @return the items
	 */
	public List<PYVariableEntryItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<PYVariableEntryItem> items) {
		this.items = items;
	}
}
