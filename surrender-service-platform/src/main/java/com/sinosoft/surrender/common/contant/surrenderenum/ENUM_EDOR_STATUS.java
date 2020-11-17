package com.sinosoft.surrender.common.contant.surrenderenum;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * 
 * 审批状态
 *
 * @author: wangwl
 * 
 * @date: 2018年5月14日-下午4:16:42
 * @version:
 */
public enum ENUM_EDOR_STATUS {

	EDORCONFIRM("0", "保全确认"),

	INPUTSUCEE("1", "录入完成"),

	APPLYCONFIRM("2", "保全申请确认"),

	WAITINPUT("3", "待录入"),

	TRYSUCEE("4", "试算完成"),

	WAITEDOR("5", "待审批"),

	EDOR_REFUSE("6", "审批未通过");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_EDOR_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (StringUtil.isNotBlank(value)) {
			for (ENUM_EDOR_STATUS obj : ENUM_EDOR_STATUS.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}

}
