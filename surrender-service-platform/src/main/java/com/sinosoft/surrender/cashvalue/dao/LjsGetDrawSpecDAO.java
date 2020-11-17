package com.sinosoft.surrender.cashvalue.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.LcGet;
import com.sinosoft.surrender.db.model.LjsGetDraw;

public interface LjsGetDrawSpecDAO {

	/**
	 * 
	 * 根据lcget中polno，责任编码(dutyCode)，给付责任编码，给付责任类型,查询给付表
	 *
	 * @history: 2018年3月26日
	 * @author: wangwl_sinosoft
	 * @param lcget
	 * @return
	 */
	List<LjsGetDraw> getLjsGetDrawInfos(LcGet lcget);
}
