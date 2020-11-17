package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LcInsureAccTraceSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcInsureAcc;
import com.sinosoft.surrender.db.model.LcInsureAccTrace;
@Repository
public class LcInsureAccTraceSpecDAOImpl extends SurrenderBaseDAOImpl implements LcInsureAccTraceSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LcInsureAccTrace> getLcInsureAccTraceInfos(LcInsureAcc lcInsureAcc) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("polno", lcInsureAcc.getPolno());
		mapParam.put("insuaccno", lcInsureAcc.getInsuaccno());
		List<LcInsureAccTrace> lcInsureAccTraceList = this.queryForList(
				"LCINSUREACCTRACESPECDAO.getLcInsureAccTraceInfos", mapParam);
		return lcInsureAccTraceList;
	}

}
