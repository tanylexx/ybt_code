package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.SurrenderTraceSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YkSurrenderTrace;

@Repository("surrenderTraceSpecDAOImpl")
public class SurrenderTraceSpecDAOImpl extends SurrenderBaseDAOImpl implements SurrenderTraceSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<YkSurrenderTrace> getYkSurrenderTraceInfo(PolicyCashValueReqDTO policyCashValueReqDTO) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("contno", policyCashValueReqDTO.getContNo());
		mapParam.put("applydate", policyCashValueReqDTO.getApplyDate());
		List<YkSurrenderTrace> ykSurrenderTraceList = this.queryForList(
				"SURENDERTRACESPECDAO.getYkSurrenderTraceInfo", mapParam);
		return ykSurrenderTraceList;
	}

}
