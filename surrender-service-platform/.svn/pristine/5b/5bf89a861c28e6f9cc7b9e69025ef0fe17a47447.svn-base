package com.sinosoft.surrender.cashvalue.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.dao.InterestSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
@Component
public class InterestSpecDAOImpl extends SurrenderBaseDAOImpl implements InterestSpecDAO {

	@Override
	public BigDecimal findByContno(String contno, Date date) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contno", contno);
		map.put("date", date);
		return (BigDecimal) this.queryForObject("INTERESTSPEC.findByContno", map);
	}

	@Override
	public Date findMaxEndDateByContnoAndRikcode(String contno, String riskcode, Date cvalidate) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contno", contno);
		map.put("riskcode", riskcode);
		map.put("cvalidate", cvalidate);
		return (Date) this.queryForObject("INTERESTSPEC.findMaxEndDateByContnoAndRikcode", map);
	}

}
