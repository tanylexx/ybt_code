package com.sinosoft.surrender.cashvalue.dto;

import java.math.BigDecimal;

/**
 * 计算现价要素
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-上午11:11:09
 * @version:
 */
public class CalCashValueParamDTO {

	/**
	 * 当前保单年度末现价
	 */
	private BigDecimal curPolicyYearEndCashValue;
	/**
	 * 上一个保单年度末现价
	 */
	private BigDecimal lastPolicyYearEndCashValue;

	/**
	 * 保单年度末生存金给付
	 */
	private BigDecimal policyYearEndAlivePay;
	/**
	 * 评估净保费
	 */
	private BigDecimal prem_cv;
	/**
	 * 基本保额
	 */
	private BigDecimal baseAmnt;

	/**
	 * 有效保额
	 */
	private BigDecimal validAmnt;

	/**
	 * 退保时所在交费期间经过的天数
	 */
	private BigDecimal currentDuringDays;

	/**
	 * 可垫付天数
	 */
	private BigDecimal paymentDays;

	/**
	 * 退保所在保单年度的天数
	 */
	private BigDecimal zTYearDays;
	/**
	 * 计算用 退保点距保单生效对应日的天数
	 */
	private BigDecimal zTDays;
	/**
	 * 交费间隔
	 * 
	 */
	private BigDecimal payIntv;

	/**
	 * 年初生存金，如险种不为年初领取生存金类型，此值为零
	 */
	private BigDecimal getAliveBegin;

	public BigDecimal getCurPolicyYearEndCashValue() {
		return curPolicyYearEndCashValue;
	}

	public void setCurPolicyYearEndCashValue(BigDecimal curPolicyYearEndCashValue) {
		this.curPolicyYearEndCashValue = curPolicyYearEndCashValue;
	}

	public BigDecimal getLastPolicyYearEndCashValue() {
		return lastPolicyYearEndCashValue;
	}

	public void setLastPolicyYearEndCashValue(BigDecimal lastPolicyYearEndCashValue) {
		this.lastPolicyYearEndCashValue = lastPolicyYearEndCashValue;
	}

	public BigDecimal getPolicyYearEndAlivePay() {
		return policyYearEndAlivePay;
	}

	public void setPolicyYearEndAlivePay(BigDecimal policyYearEndAlivePay) {
		this.policyYearEndAlivePay = policyYearEndAlivePay;
	}

	public BigDecimal getPrem_cv() {
		return prem_cv;
	}

	public void setPrem_cv(BigDecimal prem_cv) {
		this.prem_cv = prem_cv;
	}

	public BigDecimal getBaseAmnt() {
		return baseAmnt;
	}

	public void setBaseAmnt(BigDecimal baseAmnt) {
		this.baseAmnt = baseAmnt;
	}

	public BigDecimal getValidAmnt() {
		return validAmnt;
	}

	public void setValidAmnt(BigDecimal validAmnt) {
		this.validAmnt = validAmnt;
	}

	public BigDecimal getCurrentDuringDays() {
		return currentDuringDays;
	}

	public void setCurrentDuringDays(BigDecimal currentDuringDays) {
		this.currentDuringDays = currentDuringDays;
	}

	public BigDecimal getPaymentDays() {
		return paymentDays;
	}

	public void setPaymentDays(BigDecimal paymentDays) {
		this.paymentDays = paymentDays;
	}

	public BigDecimal getzTYearDays() {
		return zTYearDays;
	}

	public void setzTYearDays(BigDecimal zTYearDays) {
		this.zTYearDays = zTYearDays;
	}

	public BigDecimal getzTDays() {
		return zTDays;
	}

	public void setzTDays(BigDecimal zTDays) {
		this.zTDays = zTDays;
	}

	public BigDecimal getPayIntv() {
		return payIntv;
	}

	public void setPayIntv(BigDecimal payIntv) {
		this.payIntv = payIntv;
	}

	public BigDecimal getGetAliveBegin() {
		return getAliveBegin;
	}

	public void setGetAliveBegin(BigDecimal getAliveBegin) {
		this.getAliveBegin = getAliveBegin;
	}



}
