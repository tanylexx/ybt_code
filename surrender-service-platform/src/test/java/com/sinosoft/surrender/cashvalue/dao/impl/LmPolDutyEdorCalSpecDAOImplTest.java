package com.sinosoft.surrender.cashvalue.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LmPolDutyEdorCalSpecDAO;

public class LmPolDutyEdorCalSpecDAOImplTest extends BaseTester {
	@Autowired
	private LmPolDutyEdorCalSpecDAO lmPolDutyEdorCalSpecDAO;

	@Test
	public void testGetLmPolDutyEdorCalInfos() {
		logger.info(XmlUtil.toXml(lmPolDutyEdorCalSpecDAO.getLmPolDutyEdorCalInfos("3159", "864001")));

	}
}
