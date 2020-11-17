package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LmEdorZT1SpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LmEdorZT1;
@Repository
public class LmEdorZT1SpecDAOImpl extends SurrenderBaseDAOImpl implements LmEdorZT1SpecDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<LmEdorZT1> getLmEdorZT1Infos(String riskCode, String dutyCode) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("riskcode", riskCode);
		paramMap.put("dutycode", dutyCode);
		List<LmEdorZT1> lmEdorZT1List = this.queryForList("LMEDORZT1SPECDAO.getLmEdorZT1Infos", paramMap);
		return lmEdorZT1List;
	}

}
