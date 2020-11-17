package com.sinosoft.surrender.policyimport.bo.impl;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.guohualife.platform.common.api.util.DateUtil;
import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.policyimport.bo.PolicyImportDealBO;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;

public class PolicyImportDealBOImplTest extends BaseTester{
	@Autowired
	private PolicyImportDealBO policyImportDealBO;
	
	@Test
	public void testModifyProcessingState(){
		PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO = new PolicyApplyStateReviseReqDTO();
		policyApplyStateReviseReqDTO.setContNo("889977665544");
		policyApplyStateReviseReqDTO.setTransNo("10000001");
		policyApplyStateReviseReqDTO.setAcceptStatus("01");
		try {
			logger.info("更新开始");
			policyImportDealBO.modifyProcessingState(policyApplyStateReviseReqDTO);
			logger.info("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuerySurrender(){
		QuerySurrenderReqDTO querySurrenderReqDTO = new QuerySurrenderReqDTO();
		//querySurrenderReqDTO.setBankCode("02");
		querySurrenderReqDTO.setContNo("889977665544");
		querySurrenderReqDTO.setEdorType("CT");
		querySurrenderReqDTO.setAcceptStatus("01");
		querySurrenderReqDTO.setStartDate(DateUtil.getDate("2017/01/01 00:00:00"));
		querySurrenderReqDTO.setEndDate(new Date());
		
		try {
			logger.info("c查询结果：{}",XmlUtil.toXml(policyImportDealBO.querySurrender(querySurrenderReqDTO)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
