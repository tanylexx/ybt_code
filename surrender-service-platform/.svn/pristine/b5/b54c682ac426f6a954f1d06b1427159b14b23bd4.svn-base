package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LcInsuredSpecDAO;
import com.sinosoft.surrender.db.model.LcInsured;

public class LcInsuredSpecDAOImplTest extends BaseTester {
	@Autowired
	private LcInsuredSpecDAO lcInsuredSpecDAO;

	@Test
	public void testGetLcinsuredByContNo() {

		List<LcInsured> lcinsuredByContNo = lcInsuredSpecDAO.getLcinsuredByContNo("8828000000183998");

		logger.info(XmlUtil.toXml(lcinsuredByContNo));

	}

}
