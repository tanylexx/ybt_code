package com.sinosoft.surrender.surrconfirm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Component;

import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.LpEdorItem;
import com.sinosoft.surrender.surrconfirm.dao.LpEdorItemSpecDAO;

@Component
public class LpEdorItemSpecDAOImpl extends SurrenderBaseDAOImpl implements LpEdorItemSpecDAO {
	@SuppressWarnings("unchecked")
	@Override
	public List<LpEdorItem> getLpEdorItemInfo(String edorAcceptno) {

		List<LpEdorItem> lpEdorItemList = this.queryForList("LPEDORITEMSPECDAO.getLpEdorItemInfo", edorAcceptno);

		return lpEdorItemList;
	}

}
