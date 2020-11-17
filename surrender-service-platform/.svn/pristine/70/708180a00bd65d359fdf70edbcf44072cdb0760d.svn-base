package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * 
 *保全流水号类型
 *
 * @author:  HASEE
 * @date: 2018年5月29日-上午10:11:30
 * @version:
 */
public enum ENUM_LIS_EDORNO_TYPE {
	
	/** 90487-保全受理号 */
	EDORACCEPTNO("YBTEDORACCEPTNO", "保全受理号", "90487");

	private final String value;
	
	private final String name;
	
	private final String codeNo;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	public String getCodeNo() {
		return codeNo;
	}

	ENUM_LIS_EDORNO_TYPE(String value, String name, String codeNo) {
		this.value = value;
		this.name = name;
		this.codeNo = codeNo;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_EDORNO_TYPE obj : ENUM_LIS_EDORNO_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
	public static String getCodeNoByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_EDORNO_TYPE obj : ENUM_LIS_EDORNO_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getCodeNo();
				}
			}
		}
		return "";
	}
	
}
