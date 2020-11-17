package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;
public enum ENUM_CONT_EDOR_TYPE {
	/** 3-保单挂失 */
	CONTLOSS("3", "保单挂失", "GS"),
	
	/** 4-保单解除 */
	CONTREMOVE("4", "保单解除", "JC"),
	
	/** 5-未特别定义的保全项目*/
	COMMONEDOR("5", "未特别定义的保全项目", "OO");
	
	private final String value;
	
	private final String name;
	
	private final String shortName;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public String getShortName() {
		return shortName;
	}

	ENUM_CONT_EDOR_TYPE(String value, String name, String shortName) {
		this.value = value;
		this.name = name;
		this.shortName = shortName;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_CONT_EDOR_TYPE obj : ENUM_CONT_EDOR_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
	public static String getShortNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_CONT_EDOR_TYPE obj : ENUM_CONT_EDOR_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getShortName();
				}
			}
		}
		return null;
	}
	
}
