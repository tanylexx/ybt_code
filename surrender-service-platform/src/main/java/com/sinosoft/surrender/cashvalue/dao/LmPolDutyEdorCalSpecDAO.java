package com.sinosoft.surrender.cashvalue.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.LmPolDutyEdorCal;

public interface LmPolDutyEdorCalSpecDAO {

	/**
	 * 	
	 * 根据险种编码和责任编码查询险种责任保全计算表  
	 *
	 * @history: 2018-3-13
	 * @author: wangwl_sinosoft
	 * @param riskCode
	 * @param dutyCode
	 * @return
	 */
  List<LmPolDutyEdorCal> getLmPolDutyEdorCalInfos(String riskCode,String dutyCode);
}
