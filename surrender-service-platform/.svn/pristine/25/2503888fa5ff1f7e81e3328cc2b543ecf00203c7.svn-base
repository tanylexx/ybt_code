package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.YdCashValueRuleSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YdCashValueRule;

@Repository
public class YdCashValueRuleSpecDAOImpl extends SurrenderBaseDAOImpl implements YdCashValueRuleSpecDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<YdCashValueRule> getLdCashValueRule() {

		return this.queryForList("YDCASHVALUERULESPECDAO.getYdCashValueRule", null);
	}

	@Override
	public YdCashValueRule getByTraceContno(String contno) {
		
		return (YdCashValueRule) this.queryForObject("YDCASHVALUERULESPECDAO.getByTraceContno", contno);
	}

}
