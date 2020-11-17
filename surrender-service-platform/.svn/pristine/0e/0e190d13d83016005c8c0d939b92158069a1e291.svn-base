package com.sinosoft.surrender.common.contant.surrenderenum;


/**
 * 
 * 被保人、投保人死亡标志
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_DEAD_FLAG {
	INSUREDFLAG("1", "被保人死亡标志"),

	APPNTFLAG("2", "投保人死亡标志");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_DEAD_FLAG(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_DEAD_FLAG obj : ENUM_DEAD_FLAG.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
