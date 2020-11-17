package com.sinosoft.surrender.cashvalue.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LmEdorZT1SpecDAO;

public class LmEdorZT1SpecDAOImplTest extends BaseTester {
	@Autowired
	private LmEdorZT1SpecDAO lmEdorZT1SpecDAO;

	@Test
	public void testGetLmEdorZT1Infos() {

		try {
			logger.info(XmlUtil.toXml(lmEdorZT1SpecDAO.getLmEdorZT1Infos("8104", "866001")));
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}

	}
}
