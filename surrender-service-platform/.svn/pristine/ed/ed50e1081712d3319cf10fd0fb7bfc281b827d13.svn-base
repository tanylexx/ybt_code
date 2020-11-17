package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * <p>Description: 保单号类型枚举 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月8日 上午9:46:04
 * @Version 1.0.0
 */
public enum ENUM_OTHERNO_TYPE {
	
	/** 1－个人客户号 */
	PERSONALNO("1","个人客户号"),
	
	/** 2－团体客户号 */
	GROUPNO("2","团体客户号"),
	
	/** 3－个险保单号 */
	PERINSNO("3","个险保单号"),
	
	/** 4－团险保单号 */
	GROPINSNO("4","团险保单号"),
	
	/** 10-保全*/
	PRESERVATION("10", "保全"),
	
	/** 18-*/
	TEMPNO("18", "");
	
/*	11                  	个险暂交费收据号
	13                  	个险合同号
	21                  	团险暂交费收据号
	22                  	团险印刷号
	12                  	个险印刷号
	23                  	团险合同号*/

	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_OTHERNO_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_OTHERNO_TYPE obj : ENUM_OTHERNO_TYPE.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
