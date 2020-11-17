package com.sinosoft.surrender.cashvalue.dto;
/**
 * 
 * @author yangyb
 *新短信--配置中心的三个维度封装为对象  
 */
public class NewMSMDto {
	private String bankcode;//银行码
	private String managecom;//机构码
	private String saleChnlDetail;//渠道
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getManagecom() {
		return managecom;
	}
	public void setManagecom(String managecom) {
		this.managecom = managecom;
	}
	public String getSaleChnlDetail() {
		return saleChnlDetail;
	}
	public void setSaleChnlDetail(String saleChnlDetail) {
		this.saleChnlDetail = saleChnlDetail;
	}
}

