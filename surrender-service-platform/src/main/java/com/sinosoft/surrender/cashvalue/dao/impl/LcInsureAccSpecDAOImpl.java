package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LcInsureAccSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcInsureAcc;
@Repository
public class LcInsureAccSpecDAOImpl extends SurrenderBaseDAOImpl implements LcInsureAccSpecDAO {

	@Override
	public List<LcInsureAcc> getLcInsureAccByNoAndType(String polNo,String accType) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("polno", polNo);
		param.put("acctype", accType);
		@SuppressWarnings("unchecked")
		List<LcInsureAcc> lcInsureAcclist = this.queryForList("LCINSUREACCSPECDAO.getLcInsureAccByNoAndType", param);
		return lcInsureAcclist;
	}

	
}
