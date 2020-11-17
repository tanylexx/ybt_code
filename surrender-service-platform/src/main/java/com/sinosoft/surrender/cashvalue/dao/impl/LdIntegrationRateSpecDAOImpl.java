package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LdIntegrationRateSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LdIntegrationRate;

@Repository
public class LdIntegrationRateSpecDAOImpl extends SurrenderBaseDAOImpl implements LdIntegrationRateSpecDAO {

	@Override
	public List<LdIntegrationRate> getLdIntegrationRateInfos(Date startDate, Date endDate) {

		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("startdate", startDate);
		mapParam.put("enddate", endDate);

		@SuppressWarnings("unchecked")
		List<LdIntegrationRate> ldIntegrationRateList = this.queryForList(
				"LDINTEGRATIONRATESPECDAO.getLdIntegrationRateInfos", mapParam);
		return ldIntegrationRateList;
	}

}
