package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * <p>Description: 险种状态开头</p>
 * 
 * @author zhangfeng_sinosoft
 * @Date 2016年12月7日 下午4:43:22
 * @Version 1.0.0
 */
public enum ENUM_CONT_POLSTART {
	
	/**以"0303"开头--保单满期状态*/
	FULLTIME("0303", "保单满期状态"),
	
	/**以"0302"开头--保单处于永久失效状态*/
	NEVERDO("0302", "保单处于永久失效状态"),
	
	/**以"0310"开头--保单处于理赔责任终止状态*/
	CLAIMSTOP("0310", "保单处于理赔责任终止状态"),
	
	/**以"0304"开头--保单处于解约终止状态*/
	RECOMMENCE("0304", "保单处于解约终止状态");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_CONT_POLSTART(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_CONT_POLSTART obj : ENUM_CONT_POLSTART.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
