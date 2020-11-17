package com.sinosoft.surrender.surrconfirm.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.LpEdorItem;

public interface LpEdorItemSpecDAO {

	/**
	 * 	
	 * 根据保全受理号查询 
	 *
	 * @history: 2018年5月14日
	 * @author: HASEE
	 * @param edorAcceptno
	 * @return
	 */
	List<LpEdorItem> getLpEdorItemInfo(String edorAcceptno);
}
