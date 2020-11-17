package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LcDutySpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcDuty;
@Repository
public class LcDutySpecDAOImpl extends SurrenderBaseDAOImpl implements LcDutySpecDAO{

	@SuppressWarnings("unchecked")
	@Override
	public List<LcDuty> getLcDutyList(String polNo) {
		return this.queryForList("LCDUTYSPECDAO.getLcDutyByPolNo", polNo);
	}

}
