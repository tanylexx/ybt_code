package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.sinosoft.surrender.cashvalue.dao.LcGetSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcGet;

@Repository
public class LcGetSpecDAOImpl extends SurrenderBaseDAOImpl implements LcGetSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LcGet> getLcGetInfos(String polNo, Date dateStart, Date dateEnd) {
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("polno", polNo);
		paramMap.put("datestart", dateStart);
		paramMap.put("dateend", dateEnd);
		List<LcGet> lcGets = this.queryForList("LCGETSPECDAO.getLcGetInfos", paramMap);
		return lcGets;
	}
}
