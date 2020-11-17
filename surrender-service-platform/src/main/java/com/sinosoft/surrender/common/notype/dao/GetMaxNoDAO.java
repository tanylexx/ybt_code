package com.sinosoft.surrender.common.notype.dao;

public interface GetMaxNoDAO {
	/**
	 * 
	 * 根据流水号编码和机构编码获取最大流水号
	 * 
	 * @history: 2018-4-4
	 * @author: wangwl_sinosoft
	 * @param edorNo
	 * @param limitStr
	 * @return
	 */
	int getMaxNo(String edorNo, String limitStr);
}
