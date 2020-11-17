package com.sinosoft.surrender.common.contant.surrenderenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * <p>Description: 是否标志枚举 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月8日 上午9:45:46
 * @Version 1.0.0
 */
public enum ENUM_YES_NO_STATUS {
	
	/** 1-是 */
	YES("1","是"),
	
	/** 0-否 */
	NO("0","否");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_YES_NO_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_YES_NO_STATUS obj : ENUM_YES_NO_STATUS.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}

}
