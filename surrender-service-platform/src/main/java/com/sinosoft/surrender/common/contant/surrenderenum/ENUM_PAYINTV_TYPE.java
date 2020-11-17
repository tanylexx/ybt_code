package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 交费间隔编码
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-16-下午6:05:20
 * @version:
 */
public enum ENUM_PAYINTV_TYPE {

	IRREGULAR("-1", "不定期交"),

	SINGLE("0", "趸交"),

	MONTHPAY("1", "月交"),

	YEARPAY("12", "年交"),

	SEASON("3", "季交"),

	HALFYEAR("6", "半年交");
	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_PAYINTV_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_PAYINTV_TYPE obj : ENUM_PAYINTV_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
