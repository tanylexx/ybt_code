package com.sinosoft.surrender.common.contant.surrenderenum;

/**
 * 
 * 生存金领取类型
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-1-下午1:42:27
 * @version:
 */
public enum ENUM_LIVE_GET_TYPE {
	LIVEINTEREST("1", "累计生息"),

	GETCASH("2", "领取现金"),
	
	OFFSETPREM("3","抵交保费"),
	
	OTHER("4","其他"),
	
	INCREASEPAID("5","增额交清");

	private final String value;

	private final String name;

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_LIVE_GET_TYPE(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public static String getNameByValue(String value) {
		if (!"".equals(value) && value != null) {
			for (ENUM_LIVE_GET_TYPE obj : ENUM_LIVE_GET_TYPE.values()) {
				if (obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
}
