package com.sinosoft.surrender.policyimport.batch.importUtils;

import org.junit.Test;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;

public class CheckFailureUtilTest extends BaseTester{
	@Test
	public void testCheckFailure(){
		String resXml = "<?xml version='1.0' encoding='GBK'?><SOAP-ENV:Fault xmlns:SOAP-ENV='http://schemas.xmlsoap.org/soap/envelope/'>  <faultcode>010004</faultcode>  <faultstring>执行交易程序时出错!! 渠道代码:[102], 交易号:[02]</faultstring></SOAP-ENV:Fault>";
		try {
			logger.info("c查询结果：{}",XmlUtil.toXml(CheckFailureUtil.checkFailure(resXml)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
