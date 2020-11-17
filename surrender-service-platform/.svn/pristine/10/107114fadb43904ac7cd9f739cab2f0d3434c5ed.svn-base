package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LpEdorAppSpecDAO;
import com.sinosoft.surrender.db.model.LpEdorApp;

public class LpEdorAppSpecDAOTest extends BaseTester {
	@Autowired
	private LpEdorAppSpecDAO lpEdorAppSpecDAO;

	@Test
	public void testGetLpEdorAppByContNo() {
		List<LpEdorApp> lpEdorAppByContNo = lpEdorAppSpecDAO.getLpEdorAppByContNo("3101001790829088");
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(lpEdorAppByContNo));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
