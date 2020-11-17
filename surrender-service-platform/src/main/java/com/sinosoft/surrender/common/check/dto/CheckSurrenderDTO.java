package com.sinosoft.surrender.common.check.dto;

import java.util.Date;

/**
 * 
 * 校验参数DTO
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-4-11-下午5:24:57
 * @version:
 */
public class CheckSurrenderDTO {
	/**
	 * 交易流水号
	 */
	private String transNo;
	/**
	 * 保单号
	 */
	private String contNo;
	/**
	 * 申请日期
	 */
	private Date applyDate;
	/**
	 * 保全类型
	 */
	private String edorType;

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public Date getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(Date applyDate) {
		this.applyDate = applyDate;
	}

	public String getEdorType() {
		return edorType;
	}

	public void setEdorType(String edorType) {
		this.edorType = edorType;
	}

	public String getTransNo() {
		return transNo;
	}

	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	

}
