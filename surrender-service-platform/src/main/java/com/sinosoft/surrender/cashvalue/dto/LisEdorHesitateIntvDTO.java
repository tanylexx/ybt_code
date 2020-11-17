package com.sinosoft.surrender.cashvalue.dto;

import java.util.Date;

/**
 * <p>Description: 犹豫期判断数据</p>
 * @author yangzf_sinosoft
 * @date 2017年7月27日 下午7:28:52
 *
 */
public class LisEdorHesitateIntvDTO {

	/** 客户回执日期 */
	private Date customgetpoldate;
	
	/** 犹豫期间隔 */
	private Integer hesitateend;
	
	/** 犹豫期间隔类型 */
	private String hesitatetype;

	public Date getCustomgetpoldate() {
		return customgetpoldate;
	}

	public void setCustomgetpoldate(Date customgetpoldate) {
		this.customgetpoldate = customgetpoldate;
	}

	public Integer getHesitateend() {
		return hesitateend;
	}

	public void setHesitateend(Integer hesitateend) {
		this.hesitateend = hesitateend;
	}

	public String getHesitatetype() {
		return hesitatetype;
	}

	public void setHesitatetype(String hesitatetype) {
		this.hesitatetype = hesitatetype;
	}
	
}
