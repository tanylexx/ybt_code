package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * <p>Description: 事故者状态枚举 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月9日 下午3:03:46
 * @Version 1.0.0
 */
public enum ENUM_CASE_CUST_STATUS {

	/** 01-治疗前 */
	PRECURE("01","治疗前"),
	/** 02-门诊 */
	OUTPATIENT("02","门诊"),
	/** 03-住院 */
	TOHOS("03","住院"),
	/** 04-出院 */
	LEAVEHOS("04","出院"),
	/** 05-痊愈 */
	CURE("05","痊愈"),
	/** 06-好转 */
	BETTER("06","好转"),
	/** 07-无效 */
	NULLITY("07","无效"),
	/** 08-死亡 */
	DEATH("08","死亡");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_CASE_CUST_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_CASE_CUST_STATUS obj : ENUM_CASE_CUST_STATUS.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
