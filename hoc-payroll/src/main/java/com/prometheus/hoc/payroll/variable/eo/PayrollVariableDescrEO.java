package com.prometheus.hoc.payroll.variable.eo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="py_variable_descr")
public class PayrollVariableDescrEO implements Serializable{
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3169832727141303779L;
	
	@Id
	@Column(name="CONF_TYPE", length=20)
	private String configType;
	
	@Column(name="LOCALE",length=5)
	private String locale;
	
	@Column(name="AMT1_DESCR", columnDefinition="text")
	private String amount1Descr;
	
	@Column(name="AMT2_DESCR", columnDefinition="text")
	private String amount2Descr;
	
	@Column(name="AMT3_DESCR", columnDefinition="text")
	private String amount3Descr;
	
	@Column(name="AMT4_DESCR", columnDefinition="text")
	private String amount4Descr;
	
	
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
	
}
