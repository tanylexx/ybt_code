package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LmDutyGetAliveSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LmDutyGetAlive;

@Repository
public class LmDutyGetAliveSpecDAOImpl extends SurrenderBaseDAOImpl implements LmDutyGetAliveSpecDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<LmDutyGetAlive> getLmDutyGetAliveInfos(String getDutyCode) {

		List<LmDutyGetAlive> lmDutyGetAliveList = this.queryForList("LMDUTYGETALIVESPECDAO.getLmDutyGetAliveInfos",
				getDutyCode);
		return lmDutyGetAliveList;
	}

}
