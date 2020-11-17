package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 领取间隔
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_GETINTV_TYPE {
	SINGLEGET("0", "趸领"),

	MONTHGET("1", "月领"),

	YEARGET("12", "年领"),

	SEASONGET("3", "季领"),

	HALFYEARGET("6", "半年领");
	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_GETINTV_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_GETINTV_TYPE obj : ENUM_GETINTV_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
