package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 *申请方式 
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_APP_TYPE {
	/**
	 * 柜面
	 */
	GM("2", "Y"),
     /**
      * 网银
      */
	WY("4", "W"),
     /**
      * 终端
      */
	ZD("9", "Z");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_APP_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_APP_TYPE obj : ENUM_APP_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
