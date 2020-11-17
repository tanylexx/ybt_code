package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LjsGetDrawSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcGet;
import com.sinosoft.surrender.db.model.LjsGetDraw;

@Repository
public class LjsGetDrawSpecDAOImpl extends SurrenderBaseDAOImpl implements LjsGetDrawSpecDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<LjsGetDraw> getLjsGetDrawInfos(LcGet lcget) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("polno", lcget.getPolno());
		paramMap.put("dutycode", lcget.getDutycode());
		paramMap.put("getdutycode", lcget.getGetdutycode());
		paramMap.put("getdutykind", lcget.getGetdutykind());
		List<LjsGetDraw> ljsGetDrawList = this.queryForList("LJSGETDRAWSPCEDAO.getLjsGetDrawInfos", paramMap);
		return ljsGetDrawList;
	}

}
