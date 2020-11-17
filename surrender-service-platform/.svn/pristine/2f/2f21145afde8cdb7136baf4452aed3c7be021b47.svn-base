package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LlClaimDetailSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LcDuty;
@Repository
public class LlClaimDetailSpecDAOImpl extends SurrenderBaseDAOImpl implements LlClaimDetailSpecDAO {

	@Override
	public String getSumRealPay(LcDuty lcDuty) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("dutycode", lcDuty.getDutycode());
		paramMap.put("contno", lcDuty.getContno());
		paramMap.put("polno", lcDuty.getPolno());
		return (String) this.queryForObject("LLCLAIMDETAILSPECDAO.getSumRealPay", paramMap);
	}

}
