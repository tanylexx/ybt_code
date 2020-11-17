package com.sinosoft.surrender.cashvalue.dao;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 帐户利率表
 * @author zhongdw_sinosoft
 *
 */
public interface InterestSpecDAO {
	/**
	 * 根据保单号查询
	 * @param contno
	 * @return
	 */
	public BigDecimal findByContno(String contno,Date date);
	
	/**
	 * 查询维护了的最大的结束日期
	 * @param contno
	 * @param riskcode
	 * @return
	 */
	public Date findMaxEndDateByContnoAndRikcode(String contno, String riskcode, Date cvalidate);
	
}
