package com.sinosoft.surrender.common.contant.lisenum;

import com.guohualife.platform.common.api.util.StringUtil;

/**
 * <p>Description: 理赔案件状态 </p>
 * 
 * @author zhuming_sinosoft
 * @Date 2016年11月8日 上午9:46:29
 * @Version 1.0.0
 */
public enum ENUM_CASE_RGT_STATUS {

	/** 00-报案状态 */
	REPORT("00","报案状态"),
	
	/** 01-受理状态 */
	ACCEPT("01","受理状态"),
	
	/** 02-扫描状态 */
	SCAN("02","扫描状态"),
	
	/** 03-录入状态 */
	REGIST("03","录入状态"),
	
	/** 04-理算状态 */
	ADJUST("04","理算状态"),
	
	/** 05-审批状态 */
	APPROVE("05","审批状态"),
	
	/** 06-审定状态 */
	JUDGE("06","审定状态"),
	
	/** 07-调查状态 */
	SURVEY("07","调查状态"),
	
	/** 08-查讫状态 */
	CHECKED("08","查讫状态"),
	
	/** 09-结案状态 */
	LAWSUIT("09","结案状态"),
	
	/** 10-抽检状态 */
	SPOTCHECK("10","抽检状态"),
	
	/** 11-通知状态 */
	INFORM("11","通知状态"),
	
	/** 12-给付状态 */
	DELIVERY("12","给付状态"),
	
	/** 13-延迟状态 */
	DELAY("13","延迟状态"),
	
	/** 14-撤件状态 */
	REPEAL("14","撤件状态"),
	
	/** 15-不予立案 */
	DENYRECORD("15","不予立案"),
	
	/** 16-预付申请 */
	PREPAYAPP("16","预付申请"),
	
	/** 17-预付审核 */
	PREPAYCHECK("17","预付审核"),
	
	/** 18-预付审批 */
	PREPAYAPPROVE("18","预付审批"),
	
	/** 19-预付签批 */
	PREPAYSIGN("19","预付签批"),
	
	/** 20-预付支付 */
	prepay("20","预付支付"),
	
	/** 21-不予预付 */
	DENYPREPAY("21","不予预付");
	
	private final String value;
	
	private final String name;
	
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	ENUM_CASE_RGT_STATUS(String value, String name) {
		this.value = value;
		this.name = name;
	}
	
	public static String getNameByValue(String value) {
		if(StringUtil.isNotBlank(value)) {
			for(ENUM_CASE_RGT_STATUS obj : ENUM_CASE_RGT_STATUS.values()) {
				if(obj.getValue().equals(value)) {
					return obj.getName();
				}
			}
		}
		return null;
	}
	
}
