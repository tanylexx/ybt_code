package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * <p>Description: 编码类型</p>
 * 
 * @author zhangfeng_sinosoft
 * @Date 2016年12月5日 上午11:33:05
 * @Version 1.0.0
 */
public enum ENUM_LIS_CODE_TYPE {
	/** risk3151sub-3151险种支持的万能附加险*/
	RISK3151SUB("risk3151sub", "3151险种支持的万能附加险"),
	
	/** tlwtcombincode-投连多账户犹豫期*/
	TLWTCOMBINCODE("tlwtcombincode", "投连多账户犹豫期"),
	
	/** smsmobile-电话号码校验*/
	SMSMOBILE("smsmobile", "电话号码校验"),
	
	/** shortname-简称*/
	SHORTNAME("shortname", "简称"),
	
	/** underlineapproveflag-身份证影像待审核*/
	UNDERLINEAPPROVEFLAG("underlineapproveflag", "身份证影像待审核"),
	/** dzswmark-电子商务投连险*/
	DZSWMARK("dzswmark", "电子商务投连险");

	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_LIS_CODE_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_CODE_TYPE obj : ENUM_LIS_CODE_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
