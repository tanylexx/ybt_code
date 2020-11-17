package com.sinosoft.surrender.common.contant.surrenderenum;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * 保单账户类型
 *  
 * @author:  wangwl_sinosoft
 * @date: 2018-4-22-下午3:18:54
 */

public enum ENUM_EDOR_ACC_TYPE {
	
	/** 008-账户 */
	LIVEACC("008","生存金账户");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	
	ENUM_EDOR_ACC_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_EDOR_ACC_TYPE obj : ENUM_EDOR_ACC_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
