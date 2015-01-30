package com.prometheus.hoc.payroll.schema.vo;

public enum FormulaItemEnum {
	
	VAR("variable", 1),
	OPER("operator", 2),
	FUNC("function", 3),
	CONS_NUM("constant_number", 4),
	CONS_STR("constant_string", 5),
	CONS_DATE("constant_date", 6);
	
	private String code;
	
	private int num;
	
	FormulaItemEnum(String code, int num){
		this.code = code;
		this.num = num;
	}

	/**
	 * @return the name
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param name the name to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	public int value(){
		return this.num;
	}

	/**
	 * @return the num
	 */
	public int getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(int num) {
		this.num = num;
	}
	
}
