package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 *日志状态 
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_TRACE_STATUS {
	INIT("00", "初始状态"),

	SUCCESS("01", "交易成功"),

	REPEAT("02", "重复交易"),

	FAIL("F", "交易失败");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_TRACE_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_TRACE_STATUS obj : ENUM_TRACE_STATUS.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
