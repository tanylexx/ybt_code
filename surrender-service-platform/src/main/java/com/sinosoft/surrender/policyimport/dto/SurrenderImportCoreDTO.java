package com.sinosoft.surrender.policyimport.dto;

import java.math.BigDecimal;
import java.util.Date;

public class SurrenderImportCoreDTO {
	/**
	 * 交易日期
	 */
	private Date bankDate;
	/**
	 * 交易时间
	 */
	private String bankTime;
	
	/**
	 * 银行编码
	 */
	private String bankCode;
	
	/**
	 * 地区码
	 */
	private String zoneNo;
	
	/**
	 * 网点码
	 */
	private String brNo;
	
	/**
	 * 操作人员
	 */
	private String tellerNo;
	
	/**
	 * 交易流水号
	 */
	private String transNo;
	
	/**
	 * 交易类型
	 */
	private String functionFlag;
	
	/**
	 * 保全印刷号
	 */
	private  String edorPrtNo;
	
	/**
	 * 渠道编码
	 */
	private String sourceType;
	
	/**
	 * 保单号
	 */
	private String contNo;
	
	/**
	 * 险种编码
	 */
	private String riskCode;
	
	/**
	 * 银行账号
	 */
	private String bankAccNo;
	/**
	 * 银行账户名
	 */
	private String bankAccName;
	
	/**
	 * 申请金额
	 */
	private BigDecimal applyMoney;
	/**
	 * 保全受理号
	 */
	private String edorAcceptNo;
	
	public BigDecimal getApplyMoney() {
		return applyMoney;
	}
	public void setApplyMoney(BigDecimal applyMoney) {
		this.applyMoney = applyMoney;
	}
	public String getEdorAcceptNo() {
		return edorAcceptNo;
	}
	public void setEdorAcceptNo(String edorAcceptNo) {
		this.edorAcceptNo = edorAcceptNo;
	}
	public String getEdorPrtNo() {
		return edorPrtNo;
	}
	public void setEdorPrtNo(String edorPrtNo) {
		this.edorPrtNo = edorPrtNo;
	}
	
	public Date getBankDate() {
		return bankDate;
	}
	public void setBankDate(Date bankDate) {
		this.bankDate = bankDate;
	}
	public String getBankTime() {
		return bankTime;
	}
	public void setBankTime(String bankTime) {
		this.bankTime = bankTime;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getZoneNo() {
		return zoneNo;
	}
	public void setZoneNo(String zoneNo) {
		this.zoneNo = zoneNo;
	}
	public String getBrNo() {
		return brNo;
	}
	public void setBrNo(String brNo) {
		this.brNo = brNo;
	}
	public String getTellerNo() {
		return tellerNo;
	}
	public void setTellerNo(String tellerNo) {
		this.tellerNo = tellerNo;
	}
	public String getTransNo() {
		return transNo;
	}
	public void setTransNo(String transNo) {
		this.transNo = transNo;
	}
	public String getFunctionFlag() {
		return functionFlag;
	}
	public void setFunctionFlag(String functionFlag) {
		this.functionFlag = functionFlag;
	}
	public String getSourceType() {
		return sourceType;
	}
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}
	public String getContNo() {
		return contNo;
	}
	public void setContNo(String contNo) {
		this.contNo = contNo;
	}
	public String getRiskCode() {
		return riskCode;
	}
	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}
	public String getBankAccNo() {
		return bankAccNo;
	}
	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}
	public String getBankAccName() {
		return bankAccName;
	}
	public void setBankAccName(String bankAccName) {
		this.bankAccName = bankAccName;
	}
	
	
	
	
	
	
	
}
