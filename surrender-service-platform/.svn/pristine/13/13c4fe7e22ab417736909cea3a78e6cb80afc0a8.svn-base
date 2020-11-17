package com.sinosoft.surrender.cashvalue.bo;

import java.util.Date;

/**
 * 处理利率表中最大的结束日期
 * @author zhongdw_sinosoft
 *
 */
public interface DealInterestMaxEndDateBO {
	
	/**
	 * 获取到最大的利率结束日期，并保存到现价表和redis缓存中
	 * @param contno
	 */
	public void dealInterestMaxEndDate(String contno);
	
	/**
	 * 校验是否维护新的利率
	 * @param contno
	 * @param applyDate
	 * @return
	 */
	public boolean checkIsNewinterest(String contno, Date applyDate);
	
	
}
