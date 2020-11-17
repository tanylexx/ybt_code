package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * <p>Description: 险种类型 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年12月15日 上午10:46:03
 * @Version 1.0.0
 */
public enum ENUM_LIS_RISK_TYPE {
	
	// 基本类型
	/** L-寿险 */
	LIFE("L","寿险"),
	
	/** A-意外险 */
	ACCIDENT("A","意外险"),
	
	/** H-健康险 */
	HEALTH("H","健康险"),
	
	// 基本类型1
	/** 1-帐户类年金 */
	ACCANNUITY("1","帐户类年金"),
	
	/** 2-传统一年期 */
	TRADITIONAL("2","传统一年期"),
	
	/** 3-传统年金险 */
	TRADPERINS("3","传统年金险"),
	
	/** 4-利差返还年金 */
	SPREADANNUITY("4","利差返还年金"),
	
	/** 5-综合意外伤害 */
	COMPACCIDENT("5","综合意外伤害"),
	
	/** 6-世纪泰康团体住院医疗 */
	GROUPHOSTREAT("6","世纪泰康团体住院医疗"),
	
	/** 7-世纪泰康团体综合医疗 */
	COMPMEDIGROUP("7","世纪泰康团体综合医疗");

	// 基本类型2
	// Y--打印年金领取年龄
	// N--不打印年金领取年龄
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_LIS_RISK_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_RISK_TYPE obj : ENUM_LIS_RISK_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}

}
