package com.sinosoft.surrender.cashvalue.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LmDutyGetAliveSpecDAO;

public class LmDutyGetAliveSpecDAOTest extends BaseTester {
	@Autowired
	private LmDutyGetAliveSpecDAO lmDutyGetAliveSpecDAO;

	@Test
	public void testGetLmDutyGetAliveInfos(){
		try {
			String getDutyCode ="641201";
			logger.info(XmlUtil.toXml(lmDutyGetAliveSpecDAO.getLmDutyGetAliveInfos(getDutyCode)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
