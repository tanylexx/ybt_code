package com.sinosoft.surrender.cashvalue.dao;

import com.sinosoft.surrender.db.model.LcDuty;

public interface LlClaimDetailSpecDAO {

	/**
	 * 
	 * 根据contno,polno,以及责任编码计算总的赔付金额
	 * 
	 * @history: 2018-3-13
	 * @author: wangwl_sinosoft
	 * @param lcDuty
	 * @return
	 */
	String getSumRealPay(LcDuty lcDuty);
}
