package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 差值公式参数枚举
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_DIFFERENCE_FORMULA {
	CV("CV(t)", "t保单年度末现价"),

	LCV("CV(t-1)", "t-1保单年度末现价"),
	
	SB("SB","t保单年度末生存给付"),
	
	S("S","退保时所在保单年度经过的天数"),
	
	DURING("duringDays","退保时所在交费期间经过的天数"),
	
	NP("NP(cv)","现金价值采纳的评估净保费"),
	
	SA0("SA0","基本保额"),
	
	SA("SA","有效保额"),
	
	X("x","可垫天数"),
	
	ZTYEARDAYS("zTYearDays","退保所在保单年度的天数");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_DIFFERENCE_FORMULA(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_DIFFERENCE_FORMULA obj : ENUM_DIFFERENCE_FORMULA.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
