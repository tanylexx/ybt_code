package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LmPolDutyEdorCalSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LmPolDutyEdorCal;
@Repository
public class LmPolDutyEdorCalSpecDAOImpl extends SurrenderBaseDAOImpl implements LmPolDutyEdorCalSpecDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<LmPolDutyEdorCal> getLmPolDutyEdorCalInfos(String riskCode, String dutyCode) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("riskcode", riskCode);
		paramMap.put("dutycode", dutyCode);
		List<LmPolDutyEdorCal> lmPolDutyEdorCalList = this.queryForList(
				"LMPOLDUTYEDORCALSPECDAO.getLmPolDutyEdorCalInfos", paramMap);
		return lmPolDutyEdorCalList;
	}

}
