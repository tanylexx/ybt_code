package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * <p>Description: 保全撤销原因编码</p>
 * 
 * @author zhangfeng_sinosoft
 * @Date 2016年12月2日 上午10:03:27
 * @Version 1.0.0
 */
public enum ENUM_LIS_EDOR_REASONCODE {
	/** 7-网站受理，原因不详*/
	REASONUNKNOW("7", "网站受理，原因不详");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_LIS_EDOR_REASONCODE(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_EDOR_REASONCODE obj : ENUM_LIS_EDOR_REASONCODE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
