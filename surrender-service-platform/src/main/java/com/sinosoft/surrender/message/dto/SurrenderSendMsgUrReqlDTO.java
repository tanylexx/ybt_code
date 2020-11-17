package com.sinosoft.surrender.message.dto;

import java.util.Date;

import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;
//退保发短信
public class SurrenderSendMsgUrReqlDTO extends SurrenderConfirmReqDTO{
	private Date BankDate;
	
	private String InsuID;
	
	private String edoracceptno;
	
	private String EdorAppDate;
	
	private String EdorAppTime;
	
	private String phone;

	public Date getBankDate() {
		return BankDate;
	}

	public void setBankDate(Date bankDate) {
		BankDate = bankDate;
	}

	public String getInsuID() {
		return InsuID;
	}

	public void setInsuID(String insuID) {
		InsuID = insuID;
	}

	public String getEdoracceptno() {
		return edoracceptno;
	}

	public void setEdoracceptno(String edoracceptno) {
		this.edoracceptno = edoracceptno;
	}

	public String getEdorAppDate() {
		return EdorAppDate;
	}

	public void setEdorAppDate(String edorAppDate) {
		EdorAppDate = edorAppDate;
	}

	public String getEdorAppTime() {
		return EdorAppTime;
	}

	public void setEdorAppTime(String edorAppTime) {
		EdorAppTime = edorAppTime;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	
}
