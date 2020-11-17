package com.sinosoft.surrender.cashvalue.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.LcDuty;

public interface LcDutySpecDAO {

	List<LcDuty> getLcDutyList(String polNo); 
}
