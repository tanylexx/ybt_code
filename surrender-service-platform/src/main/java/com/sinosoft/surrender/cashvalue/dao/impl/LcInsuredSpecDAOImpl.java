package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LcInsuredSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcInsured;
@Repository
public class LcInsuredSpecDAOImpl extends SurrenderBaseDAOImpl implements LcInsuredSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LcInsured> getLcinsuredByContNo(String contNo) {

		return this.queryForList("LCINSUREDSPECDAO.getLcinsuredByContNo", contNo);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LcInsured> getLcinsuredInfos(String contNo, String insuredNo) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("contno", contNo);
		paramMap.put("insuredno", insuredNo);
		List<LcInsured> lcInsuredList = this.queryForList("LCINSUREDSPECDAO.getLcinsuredInfos", paramMap);
		return lcInsuredList;
	}

}
