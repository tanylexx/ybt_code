package com.sinosoft.surrender.cashvalue.dto;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * 利息分段模型（用于利息计算过程中产生的日期分段)。
 */
public class InterestSubsectionDTO {
	/**	开始日期 */
	private Date startDate;
	/**	结束日期 */
	private Date endDate;
	/**	日期间隔 */
	private int days;
	/**	日利率分段 */
	private BigDecimal rate;
	/** 该分段的利息 */
	private BigDecimal subsectionRate;
	
	public InterestSubsectionDTO() {
		super();
	}
	public InterestSubsectionDTO(Date startDate, Date endDate, int days) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.days = days;
	}
	public InterestSubsectionDTO(Date startDate, Date endDate, int days, BigDecimal rate) {
		super();
		this.startDate = startDate;
		this.endDate = endDate;
		this.days = days;
		this.rate = rate;
	}
	
	public BigDecimal getSubsectionRate() {
		return subsectionRate;
	}
	public void setSubsectionRate(BigDecimal subsectionRate) {
		this.subsectionRate = subsectionRate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public int getDays() {
		return days;
	}
	public void setDays(int days) {
		this.days = days;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
	public static String ConverToString(Date date)  
    {  
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");  
        return df.format(date);  
    }

}
