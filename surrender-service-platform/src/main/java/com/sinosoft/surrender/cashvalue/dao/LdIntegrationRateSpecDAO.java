package com.sinosoft.surrender.cashvalue.dao;

import java.util.Date;
import java.util.List;

import com.sinosoft.surrender.db.model.LdIntegrationRate;

public interface LdIntegrationRateSpecDAO {
	/**
	 * 根据开始日期和结束日期查询综合利率表
	 * 
	 * 
	 * @history: 2018-4-22 wangwl_sinosoft
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<LdIntegrationRate> getLdIntegrationRateInfos(Date startDate, Date endDate);

}
