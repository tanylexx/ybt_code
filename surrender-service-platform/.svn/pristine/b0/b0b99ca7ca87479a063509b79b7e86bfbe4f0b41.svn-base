package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LcDutySpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LlClaimDetailSpecDAO;
import com.sinosoft.surrender.db.model.LcDuty;

public class LlClaimDetailSpecDAOImplTest extends BaseTester {
	@Autowired
	private LlClaimDetailSpecDAO llClaimDetailSpecDAO;
	@Autowired
	private LcDutySpecDAO lcDutySpecDAO;
	@Test
	public void testGetSumRealPay(){
		
		List<LcDuty> lcDutyList = lcDutySpecDAO.getLcDutyList("9011000000006298"); 
		
		logger.info(XmlUtil.toXml(llClaimDetailSpecDAO.getSumRealPay(lcDutyList.get(0))));
		
	}
	

}
