package com.sinosoft.surrender.common.notype.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.common.notype.dao.GetMaxNoDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
@Repository
public class GetMaxNoDAOImpl extends SurrenderBaseDAOImpl implements GetMaxNoDAO{

	@Override
	public int getMaxNo(String edorNo, String limitStr) {
		Map<String, String> params = new HashMap<String, String>();
		params.put("edorNo", edorNo);
		params.put("limitType", limitStr);
		int maxNo = (Integer) this.queryForObject("GETMAXNO.getMaxNo", params);
		return maxNo;
	}

}
