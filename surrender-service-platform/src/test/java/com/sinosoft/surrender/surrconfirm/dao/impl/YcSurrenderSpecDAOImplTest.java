package com.sinosoft.surrender.surrconfirm.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.surrconfirm.dao.YcSurrenderSpecDAO;

public class YcSurrenderSpecDAOImplTest extends BaseTester {
	@Autowired
	private YcSurrenderSpecDAO ycSurrenderSpecDAO;
	@Test
	public void testFindSurrenderByContNoAndDate() {
		
		try {
			logger.info("开始查询");
			logger.info("c查询结果：{}",XmlUtil.toXml(ycSurrenderSpecDAO.findSurrenderByContNo("3101001890264688")));
			logger.info("开始成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
