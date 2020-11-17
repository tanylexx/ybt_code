package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * <p>Description: 保全受理状态 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月7日 下午5:18:17
 * @Version 1.0.0
 */
public enum ENUM_LIS_RESULT_STATUS {
	
	/** 0-保全受理异常 */
	EDORECEPTION("0", "保全受理异常"),
	
	/** 1-保全受理成功 */
	EDORSUCC("1", "保全受理成功"),
	
	/** 2-保全受理失败 */
	EDORFAIL("2", "保全失败"),
	
	/** 3-保全处理中 */
	EDORING("3", "保全处理中");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_LIS_RESULT_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_RESULT_STATUS obj : ENUM_LIS_RESULT_STATUS.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
