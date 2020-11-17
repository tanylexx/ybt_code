package com.sinosoft.surrender.surrconfirm.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.db.model.LpEdorItem;
import com.sinosoft.surrender.surrconfirm.dao.LpEdorItemSpecDAO;

public class LpEdorItemSpecDAOImplTest extends BaseTester {
	@Autowired
	private LpEdorItemSpecDAO lpEdorItemSpecDAO;

	@Test
	public void testGetLpEdorItemInfo() {
		List<LpEdorItem> lpEdorItemInfo = lpEdorItemSpecDAO.getLpEdorItemInfo("9048000000480808");
		
		logger.info("c查询结果：{}",XmlUtil.toXml(lpEdorItemInfo));
	}
}
