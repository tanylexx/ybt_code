package com.sinosoft.surrender.cashvalue.dto;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * 核心表算法要素
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-13-下午4:21:30
 * @version:
 */
public class AlgorithElementDTO {
	/** 保费 */
	private BigDecimal prem;

	/** 保额 */
	private BigDecimal get;

	/** 缴费间隔 */
	private BigDecimal payIntv;

	/** 保险期间 */
	private BigDecimal years;

	/** 被保人投保年龄 */
	private BigDecimal appAge;

	/** 被保人性别 */
	private String sex;
	/** 时间间隔 */
	private BigDecimal interval;

	/** 缴费终止年期或年龄 */
	private BigDecimal payEndYear;

	/** 递增率 */
	private double addRate;

	/** 领取间隔 */
	private BigDecimal getIntv;

	private BigDecimal mult;

	private BigDecimal sumPrem;

	private String job;
	/** 交费止期年龄年期标志 */
	private String payEndYearFlag;

	private Date getStartDate;

	/** 领取时领取年期 */
	private BigDecimal getYear;
	/** 领取时年龄 */
	private BigDecimal getAge;

	/** 领取次数 */
	private BigDecimal getTimes;

	/** 投保人数 */
	private BigDecimal count;

	/** 责任给付编码 */
	private String gDuty;

	/** 险种保单号 */
	private String polNo;
	/** 生存金领至日期 8100险种的年龄生存金计算时用到 */
	private Date getToDate;

	private BigDecimal fRate;

	/** 交退费金额 */
	private BigDecimal GetMoney;

	private String grp;

	private String getFlag;

	private Date valiDate;

	private Date firstPayDate;

	private BigDecimal insuYear;

	private String contNo;

	private String insuredNo;

	private String saleChnlDetail;

	private String bankCode;

	private String manageCom;

	private BigDecimal maxamnt;

	private BigDecimal maxillamnt;

	private BigDecimal maxdieamnt;

	private BigDecimal maxDriveramnt;

	private BigDecimal maxAviationAccidentAmnt;

	public String getSaleChnlDetail() {
		return saleChnlDetail;
	}

	public void setSaleChnlDetail(String saleChnlDetail) {
		this.saleChnlDetail = saleChnlDetail;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getManageCom() {
		return manageCom;
	}

	public void setManageCom(String manageCom) {
		this.manageCom = manageCom;
	}

	public BigDecimal getMaxamnt() {
		return maxamnt;
	}

	public void setMaxamnt(BigDecimal maxamnt) {
		this.maxamnt = maxamnt;
	}

	public BigDecimal getMaxillamnt() {
		return maxillamnt;
	}

	public void setMaxillamnt(BigDecimal maxillamnt) {
		this.maxillamnt = maxillamnt;
	}

	public BigDecimal getMaxdieamnt() {
		return maxdieamnt;
	}

	public void setMaxdieamnt(BigDecimal maxdieamnt) {
		this.maxdieamnt = maxdieamnt;
	}

	public BigDecimal getMaxDriveramnt() {
		return maxDriveramnt;
	}

	public void setMaxDriveramnt(BigDecimal maxDriveramnt) {
		this.maxDriveramnt = maxDriveramnt;
	}

	public BigDecimal getMaxAviationAccidentAmnt() {
		return maxAviationAccidentAmnt;
	}

	public void setMaxAviationAccidentAmnt(BigDecimal maxAviationAccidentAmnt) {
		this.maxAviationAccidentAmnt = maxAviationAccidentAmnt;
	}

	public String getInsuredNo() {
		return insuredNo;
	}

	public void setInsuredNo(String insuredNo) {
		this.insuredNo = insuredNo;
	}

	public String getContNo() {
		return contNo;
	}

	public void setContNo(String contNo) {
		this.contNo = contNo;
	}

	public BigDecimal getInsuYear() {
		return insuYear;
	}

	public void setInsuYear(BigDecimal insuYear) {
		this.insuYear = insuYear;
	}

	public BigDecimal getPrem() {
		return prem;
	}

	public void setPrem(BigDecimal prem) {
		this.prem = prem;
	}

	public BigDecimal getGet() {
		return get;
	}

	public void setGet(BigDecimal get) {
		this.get = get;
	}

	public BigDecimal getPayIntv() {
		return payIntv;
	}

	public void setPayIntv(BigDecimal payIntv) {
		this.payIntv = payIntv;
	}
	
	public BigDecimal getYears() {
		return years;
	}

	public void setYears(BigDecimal years) {
		this.years = years;
	}

	public BigDecimal getAppAge() {
		return appAge;
	}

	public void setAppAge(BigDecimal appAge) {
		this.appAge = appAge;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public BigDecimal getInterval() {
		return interval;
	}

	public void setInterval(BigDecimal interval) {
		this.interval = interval;
	}

	public BigDecimal getPayEndYear() {
		return payEndYear;
	}

	public void setPayEndYear(BigDecimal payEndYear) {
		this.payEndYear = payEndYear;
	}

	public double getAddRate() {
		return addRate;
	}

	public void setAddRate(double addRate) {
		this.addRate = addRate;
	}

	public BigDecimal getGetIntv() {
		return getIntv;
	}

	public void setGetIntv(BigDecimal getIntv) {
		this.getIntv = getIntv;
	}

	public BigDecimal getMult() {
		return mult;
	}

	public void setMult(BigDecimal mult) {
		this.mult = mult;
	}

	public BigDecimal getSumPrem() {
		return sumPrem;
	}

	public void setSumPrem(BigDecimal sumPrem) {
		this.sumPrem = sumPrem;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getPayEndYearFlag() {
		return payEndYearFlag;
	}

	public void setPayEndYearFlag(String payEndYearFlag) {
		this.payEndYearFlag = payEndYearFlag;
	}

	public Date getGetStartDate() {
		return getStartDate;
	}

	public void setGetStartDate(Date getStartDate) {
		this.getStartDate = getStartDate;
	}

	public BigDecimal getGetYear() {
		return getYear;
	}

	public void setGetYear(BigDecimal getYear) {
		this.getYear = getYear;
	}

	public BigDecimal getGetAge() {
		return getAge;
	}

	public void setGetAge(BigDecimal getAge) {
		this.getAge = getAge;
	}

	public BigDecimal getGetTimes() {
		return getTimes;
	}

	public void setGetTimes(BigDecimal getTimes) {
		this.getTimes = getTimes;
	}

	public BigDecimal getCount() {
		return count;
	}

	public void setCount(BigDecimal count) {
		this.count = count;
	}

	public String getgDuty() {
		return gDuty;
	}

	public void setgDuty(String gDuty) {
		this.gDuty = gDuty;
	}

	public String getPolNo() {
		return polNo;
	}

	public void setPolNo(String polNo) {
		this.polNo = polNo;
	}

	public Date getGetToDate() {
		return getToDate;
	}

	public void setGetToDate(Date getToDate) {
		this.getToDate = getToDate;
	}

	public BigDecimal getfRate() {
		return fRate;
	}

	public void setfRate(BigDecimal fRate) {
		this.fRate = fRate;
	}

	public BigDecimal getGetMoney() {
		return GetMoney;
	}

	public void setGetMoney(BigDecimal getMoney) {
		GetMoney = getMoney;
	}

	public String getGrp() {
		return grp;
	}

	public void setGrp(String grp) {
		this.grp = grp;
	}

	public String getGetFlag() {
		return getFlag;
	}

	public void setGetFlag(String getFlag) {
		this.getFlag = getFlag;
	}

	public Date getValiDate() {
		return valiDate;
	}

	public void setValiDate(Date valiDate) {
		this.valiDate = valiDate;
	}

	public Date getFirstPayDate() {
		return firstPayDate;
	}

	public void setFirstPayDate(Date firstPayDate) {
		this.firstPayDate = firstPayDate;
	}

}
