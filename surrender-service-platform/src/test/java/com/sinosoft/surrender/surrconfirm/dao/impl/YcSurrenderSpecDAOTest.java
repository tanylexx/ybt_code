package com.sinosoft.surrender.surrconfirm.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.db.model.YcSurrender;
import com.sinosoft.surrender.surrconfirm.dao.YcSurrenderSpecDAO;

public class YcSurrenderSpecDAOTest extends BaseTester {

	@Autowired
	private YcSurrenderSpecDAO ycSurrenderSpecDAO;

	@Test
	public void testUpdateYcSurrenderByKey() {
		YcSurrender ycSurrender = ycSurrenderSpecDAO.findSurrenderByContNo("3101001690797668");
		long current = System.currentTimeMillis();
		
		try {
			ycSurrenderSpecDAO.updateYcSurrenderByKey(ycSurrender);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("校验结果:{}耗时:{}毫秒", "1", System.currentTimeMillis() - current);

	}

}
