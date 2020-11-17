package com.sinosoft.surrender.cashvalue.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.service.CalPolicyCashValueService;
import com.sinosoft.surrender.common.util.DateUtil;

public class CalPolicyCashValueServiceImplTest extends BaseTester{

	@Autowired
	private CalPolicyCashValueService calPolicyCashValueService;
	@Test
	public void testCalPolicyCashValue(){
		
		try {
			PolicyCashValueReqDTO req = new PolicyCashValueReqDTO();
			req.setApalyPlatform("YBT");
			req.setApplyDate(DateUtil.getCurrentDate());
			req.setApplyTime("00:00:00");
			req.setBankCode("05");
			req.setBrNo("37005038");
			req.setZoneNo("3700");
			req.setContNo("3101001690797668");
			req.setEdorType("CT");
			req.setTellerNo("18030502");
			req.setSourceType("4");
			req.setTransNo("132546546");
			logger.debug(XmlUtil.toXml(calPolicyCashValueService.calPolicyCashValue(req)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
