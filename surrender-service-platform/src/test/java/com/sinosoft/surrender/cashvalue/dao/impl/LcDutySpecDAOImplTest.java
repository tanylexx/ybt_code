package com.sinosoft.surrender.cashvalue.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LcDutySpecDAO;

public class LcDutySpecDAOImplTest extends BaseTester {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LcDutySpecDAO lcDutySpecDAO;
	@Test
	public void testGetLcDutyList() {
		long current = System.currentTimeMillis();
		try {
			logger.info("c查询结果：{}",XmlUtil.toXml(lcDutySpecDAO.getLcDutyList("9011000000006298")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("校验结果:{}耗时:{}毫秒", 
				"1", System.currentTimeMillis() - current);
	}
}
