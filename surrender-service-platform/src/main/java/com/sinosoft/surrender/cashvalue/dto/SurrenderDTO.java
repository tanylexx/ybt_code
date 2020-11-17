package com.sinosoft.surrender.cashvalue.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.sinosoft.surrender.db.model.LcDuty;
import com.sinosoft.surrender.db.model.LcGet;
import com.sinosoft.surrender.db.model.LcPol;
import com.sinosoft.surrender.db.model.LcPrem;
import com.sinosoft.surrender.db.model.LmEdorZT1;

/**
 * 
 * 退保中间参数
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-13-下午1:48:12
 * @version:
 */
public class SurrenderDTO {
	/**
	 * 保单号
	 */
	private String contNo;

	/**
	 * 保全生效日期
	 */
	private Date edorValiDate;

	/**
	 * 保单生效日
	 */
	private Date cValiDate;

	/**
	 * 退保点
	 */
	private Date zTPoint;
	/**
	 * 交至日期
	 */
	private Date payToDate;

	/**
	 * 领至日期
	 */
	private Date getToDate;
	/**
	 * 本年度末
	 */
	private Date endDate;

	/**
	 * 保全类型
	 */
	private String edorType;

	/**
	 * 退保年度
	 */
	private BigDecimal ztYear;
	/**
	 * 计算编码
	 */
	private String calCode;
	/**
	 * 计算类型
	 */
	private String calType;
	/**
	 * 补/退费财务类型
	 */
	private String feeType;
	/**
	 * 生存退保计算描述表1
	 */
	private LmEdorZT1 lmEdorZT1;

	private LcPrem lcPrem;

	private LcDuty lcDuty;

	private LcPol lcPol;

	private LcGet lcGet;

	

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public Date getEdorValiDate() {
		return edorValiDate;
	}

	public void setEdorValiDate(Date edorValiDate) {
		this.edorValiDate = edorValiDate;
	}

	public Date getcValiDate() {
		return cValiDate;
	}

	public void setcValiDate(Date cValiDate) {
		this.cValiDate = cValiDate;
	}

	public String getEdorType() {
		return edorType;
	}

	public void setEdorType(String edorType) {
		this.edorType = edorType;
	}

	public LmEdorZT1 getLmEdorZT1() {
		return lmEdorZT1;
	}

	public void setLmEdorZT1(LmEdorZT1 lmEdorZT1) {
		this.lmEdorZT1 = lmEdorZT1;
	}

	public String getCalCode() {
		return calCode;
	}

	public void setCalCode(String calCode) {
		this.calCode = calCode;
	}

	public LcPrem getLcPrem() {
		return lcPrem;
	}

	public void setLcPrem(LcPrem lcPrem) {
		this.lcPrem = lcPrem;
	}

	public LcDuty getLcDuty() {
		return lcDuty;
	}

	public void setLcDuty(LcDuty lcDuty) {
		this.lcDuty = lcDuty;
	}

	public LcPol getLcPol() {
		return lcPol;
	}

	public void setLcPol(LcPol lcPol) {
		this.lcPol = lcPol;
	}

	public Date getPayToDate() {
		return payToDate;
	}

	public void setPayToDate(Date payToDate) {
		this.payToDate = payToDate;
	}

	public BigDecimal getZtYear() {
		return ztYear;
	}

	public void setZtYear(BigDecimal ztYear) {
		this.ztYear = ztYear;
	}

	public String getCalType() {
		return calType;
	}

	public void setCalType(String calType) {
		this.calType = calType;
	}

	public Date getzTPoint() {
		return zTPoint;
	}

	public void setzTPoint(Date zTPoint) {
		this.zTPoint = zTPoint;
	}

	public LcGet getLcGet() {
		return lcGet;
	}

	public void setLcGet(LcGet lcGet) {
		this.lcGet = lcGet;
	}

	public Date getGetToDate() {
		return getToDate;
	}

	public void setGetToDate(Date getToDate) {
		this.getToDate = getToDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

}
