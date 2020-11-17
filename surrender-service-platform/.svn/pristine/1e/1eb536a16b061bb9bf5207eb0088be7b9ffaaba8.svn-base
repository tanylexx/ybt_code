package com.sinosoft.surrender.cashvalue;

import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.cashvalue.service.CalPolicyCashValueService;
import com.sinosoft.surrender.common.util.DateUtil;

public class Test1 {
	public static void main(String[] args) {
		JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();
		// factory.setAddress("http://180.169.84.46:7002/pre4/api/tradeServices?wsdl");
		// //本地
		// factory.setAddress("http://10.56.81.77:7002/pre4/api/tradeServices?wsdl");
		// //本地
		factory.setAddress("http://10.56.81.19:8001/surrender/api/calPolicyCashValueService?wsdl");
		factory.setServiceClass(CalPolicyCashValueService.class);
		CalPolicyCashValueService client = (CalPolicyCashValueService) factory.create();
		PolicyCashValueReqDTO req = new PolicyCashValueReqDTO();
		req.setApalyPlatform("YBT");
		req.setApplyDate(DateUtil.getCurrentDate());
		req.setApplyTime("00:00:00");
		req.setBankCode("05");
		req.setBrNo("37005038");
		req.setZoneNo("3700");
		req.setContNo("8828000000327138");
		req.setEdorType("CT");
		req.setTellerNo("18030502");
		req.setSourceType("4");
		req.setTransNo("132546546");
		PolicyCashValueResDTO calPolicyCashValue = client.calPolicyCashValue(req);
		

		System.out.println(calPolicyCashValue.getResultMsg());
	}
}
