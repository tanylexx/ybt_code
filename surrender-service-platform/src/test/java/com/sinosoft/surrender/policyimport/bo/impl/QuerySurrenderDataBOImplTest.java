package com.sinosoft.surrender.policyimport.bo.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.policyimport.bo.QuerySurrenderDataBO;

public class QuerySurrenderDataBOImplTest extends BaseTester{
	@Autowired
	private QuerySurrenderDataBO querySurrenderDataBO;
	
	@Test
	public void testQuerySurrenderData(){
		try {
			logger.info("c查询结果：\n{}",XmlUtil.toXml(querySurrenderDataBO.querySurrenderData()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}