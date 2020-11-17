package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 给付最大次数类型
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_MAXGETCOUNT_TYPE {
	UNMAXGETCOUNT("0", "无条件给付最大次数"),

	UNMAXGETCOUNTAGE("1", "无条件给付最大年龄数"),

	INSUREDMAXGETCOUNT("2", "被保人死亡给付最大次数"),

	INSUREDMAXGETCOUNTAGE("3", "被保人死亡给付最大年龄数"),

	APPNTMAXGETCOUNT("4", "投保人死亡给付最大次数"),

	APPNTMAXGETCOUNTAGE("5", "投保人死亡给付最大年龄数");
	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_MAXGETCOUNT_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_MAXGETCOUNT_TYPE obj : ENUM_MAXGETCOUNT_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
