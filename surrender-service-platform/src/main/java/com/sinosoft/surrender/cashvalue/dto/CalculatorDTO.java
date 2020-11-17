package com.sinosoft.surrender.cashvalue.dto;

public class CalculatorDTO {
	/**
	 * 算法编码,即LMCalMode表的CalCode字段
	 */
	private String calcode;

	/**
	 * 算法要素, 可以为Map<String,Object>-参数名、参数值 也可以为其他任意Object对象,该对象的属性名、属性值作为参数名、参数值
	 */
	private Object paramsObj;

	private boolean errFlag;

	public CalculatorDTO() {

	}

	public CalculatorDTO(String calcode, Object paramsObj) {
		this.calcode = calcode;
		this.paramsObj = paramsObj;
		this.errFlag = false;
	}

	public String getCalcode() {
		return calcode;
	}

	public void setCalcode(String calcode) {
		this.calcode = calcode;
	}

	public Object getParamsObj() {
		return paramsObj;
	}

	public void setParamsObj(Object paramsObj) {
		this.paramsObj = paramsObj;
	}

	public boolean isErrFlag() {
		return errFlag;
	}

	public void setErrFlag(boolean errFlag) {
		this.errFlag = errFlag;
	}
}
