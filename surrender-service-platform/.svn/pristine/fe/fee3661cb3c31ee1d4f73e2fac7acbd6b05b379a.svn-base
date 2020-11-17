package com.sinosoft.surrender.common.contant.surrenderenum;

import com.guohualife.platform.common.api.util.StringUtil;


/**
 * <p>Description: 保全流水号类型 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月23日 上午9:44:43
 * @Version 1.0.0
 */
public enum ENUM_LIS_EDORNO_TYPE {
	
	/** 90488-保全受理号 */
	EDORACCEPTNO("ECEDORACCEPTNO", "保全受理号", "90488"),
	
	/** 9090-流水号 */
	SERIALNO("SERIALNO", "流水号", "9090"),
	
	/** 9041-保全批单号*/
	//EDORAPPNO("EDORAPPNO", "保全批单号", "9041"), // 不用了
	
	/** 102101-给付通知书号码*/
	PAYNOTICENO("PAYNOTICENO", "给付通知书号码批单号", "102101"),
	
	/** 9038-实付号码*/
	GETNO("GETNO", "实付号码", "9038"),
	
	/** 9046-新批单号*/
	EDORNO("EDORNO", "新批单号", "9046"),
	
	/** 交费收据号码 */
	PAYNO("PAYNO", "交费收据号码", "9032");
	
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
