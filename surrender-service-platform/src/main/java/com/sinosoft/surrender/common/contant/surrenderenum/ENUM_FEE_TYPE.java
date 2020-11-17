package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 补退费财务类型
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-4-下午7:09:03
 * @version:
 */
public enum ENUM_FEE_TYPE {
	TB("TB", "退保"),

	TF("TF", "退费");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_FEE_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_FEE_TYPE obj : ENUM_FEE_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
