package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcPol;

@Repository
public class LcPolSpecDAOImpl extends SurrenderBaseDAOImpl implements LcPolSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LcPol> getLcPolByContNo(String contNo) {
		List<LcPol> lcPolList = this.queryForList("LCPOLSPECDAO.getLcPolByContNo", contNo);
		return lcPolList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PolicyCashValueReqDTO> queryPrecalculatedParam(Map<String, Object> map) {
		List<PolicyCashValueReqDTO> list = this.queryForList("LCPOLSPECDAO.queryPrecalculatedParam", map);
		return list;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PolicyCashValueReqDTO> queryCallCorePrecalculatedParam(Map<String, Object> map) {
		List<PolicyCashValueReqDTO> list = this.queryForList("LCPOLSPECDAO.queryCallCorePrecalculatedParam", map);
		return list;
	}

}
