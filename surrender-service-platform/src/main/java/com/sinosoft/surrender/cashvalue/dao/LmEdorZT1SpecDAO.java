package com.sinosoft.surrender.cashvalue.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.LmEdorZT1;

public interface LmEdorZT1SpecDAO {

	 /**
	  * 	
	  * 根据riskcode和责任编码查询
	  *
	  * @history: 2018-3-13
	  * @author: wangwl_sinosoft
	  * @param riskCode
	  * @param dutyCode
	  * @return
	  */
	List<LmEdorZT1> getLmEdorZT1Infos(String riskCode,String dutyCode);
}
