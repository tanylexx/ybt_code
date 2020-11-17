package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.LpEdorAppSpecDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LpEdorApp;
@Repository
public class LpEdorAppSpecDAOImpl extends SurrenderBaseDAOImpl implements LpEdorAppSpecDAO {

	@Override
	public List<LpEdorApp> getLpEdorAppByContNo(String contNo) {
		
		@SuppressWarnings({ "unchecked"})
		List<LpEdorApp> lpEdorAppList = this.queryForList("LPEDORAPPSPECDAO.getLpEdorAppByContNo", contNo);
		return lpEdorAppList;
	}

}
