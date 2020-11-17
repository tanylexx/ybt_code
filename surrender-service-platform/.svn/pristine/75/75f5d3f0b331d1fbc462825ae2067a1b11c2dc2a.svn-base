package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LcGetSpecDAO;
import com.sinosoft.surrender.common.util.DateUtil;

public class LcGetSpecDAOTest extends BaseTester {
	@Autowired
	private LcGetSpecDAO lcGetSpecDAO;

	@Test
	public void testGetLcGetInfos() {
		try {
			Date startDate = DateUtil.getDate("2009-08-01","yyyy-mm-dd");
			Date endDate = DateUtil.getDate("2009-08-01","yyyy-mm-dd");
			logger.info(XmlUtil.toXml(lcGetSpecDAO.getLcGetInfos("9021000000003158", startDate, endDate)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
