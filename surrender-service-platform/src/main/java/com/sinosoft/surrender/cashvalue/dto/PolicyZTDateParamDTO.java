package com.sinosoft.surrender.cashvalue.dto;

import java.util.Date;

/**
 * 退保日期参数
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-上午11:10:51
 * @version:
 */
public class PolicyZTDateParamDTO {

	/**
	 * 退保点
	 */
	private Date zTPoint;

	/**
	 * 退保年度
	 */
	private int zTYear;

	/**
	 * 保单年度
	 */
	private int policyYear;

	/**
	 * 退保点距保单生效对应日的天数
	 */
	private int zTDays;

	/**
	 * 退保时所在交费期间经过的天数
	 */
	private int currentDuringDays;

	/**
	 * 可垫付天数
	 */
	private int paymentDays;

	/**
	 * 退保所在保单年度的天数
	 */
	private int zTYearDays;

	/**
	 * 交费间隔
	 */
	private int payIntv;
	/**
	 * 交至日期
	 */
	private Date payToDate;
	/**
	 * 自垫交至日期
	 */
	private Date zDPayToDate;

	public Date getzTPoint() {
		return zTPoint;
	}

	public void setzTPoint(Date zTPoint) {
		this.zTPoint = zTPoint;
	}

	public int getzTYear() {
		return zTYear;
	}

	public void setzTYear(int zTYear) {
		this.zTYear = zTYear;
	}

	public int getPolicyYear() {
		return policyYear;
	}

	public void setPolicyYear(int policyYear) {
		this.policyYear = policyYear;
	}

	public int getzTDays() {
		return zTDays;
	}

	public void setzTDays(int zTDays) {
		this.zTDays = zTDays;
	}

	public int getCurrentDuringDays() {
		return currentDuringDays;
	}

	public void setCurrentDuringDays(int currentDuringDays) {
		this.currentDuringDays = currentDuringDays;
	}

	public int getPaymentDays() {
		return paymentDays;
	}

	public void setPaymentDays(int paymentDays) {
		this.paymentDays = paymentDays;
	}

	public int getzTYearDays() {
		return zTYearDays;
	}

	public void setzTYearDays(int zTYearDays) {
		this.zTYearDays = zTYearDays;
	}

	public int getPayIntv() {
		return payIntv;
	}

	public void setPayIntv(int payIntv) {
		this.payIntv = payIntv;
	}

	public Date getPayToDate() {
		return payToDate;
	}

	public void setPayToDate(Date payToDate) {
		this.payToDate = payToDate;
	}

	public Date getzDPayToDate() {
		return zDPayToDate;
	}

	public void setzDPayToDate(Date zDPayToDate) {
		this.zDPayToDate = zDPayToDate;
	}

}
