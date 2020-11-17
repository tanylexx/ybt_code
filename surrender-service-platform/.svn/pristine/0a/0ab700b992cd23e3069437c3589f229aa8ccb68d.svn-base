package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 短信编码枚举
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_MESSAGE_TYPE {

	WTMSG("WT", "0127"),

	CTMSG("CT", "0126"),
	
	NEWMSG("NEWMSG","310");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_MESSAGE_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_MESSAGE_TYPE obj : ENUM_MESSAGE_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
