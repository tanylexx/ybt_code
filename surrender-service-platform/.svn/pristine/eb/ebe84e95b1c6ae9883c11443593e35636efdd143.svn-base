package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 递增标记
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_ADD_FLAG {
	NO("N", "不递增标志"),

	YES("2", "递增亡标志");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_ADD_FLAG(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_ADD_FLAG obj : ENUM_ADD_FLAG.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
