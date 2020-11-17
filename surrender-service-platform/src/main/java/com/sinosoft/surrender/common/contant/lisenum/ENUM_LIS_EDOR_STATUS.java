package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;



/**
 * <p>Description: 保全批改状态 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月7日 下午5:18:17
 * @Version 1.0.0
 */
public enum ENUM_LIS_EDOR_STATUS {
	
	/** 0-保全确认 */
	EDORCONFIRM("0", "保全确认"), 
	
	/** 1-录入完成 */
	ENTERFINISH("1", "录入完成"), 
	
	/** 2-保全申请确认 */
	APPCONFIRM("2", "保全申请确认"), 
	
	/** 3-待录入 */
	TOENTER("3", "待录入"), 
	
	/** 4-试算完成 */
	CHARGEFINISH("4", "试算完成"), 
	
	/** 5-待审批 */
	TOAPPROVE("5", "待审批"), 
	
	/** 6-审批未通过 */
	APPROVEFAIL("6", "审批未通过"),
	
	/** 7-保全撤销 */
	EDORACNCEL("7", "保全撤销"),
	
	/** 8-核保终止 */
	EDOREND("8", "核保终止");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_LIS_EDOR_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_LIS_EDOR_STATUS obj : ENUM_LIS_EDOR_STATUS.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
