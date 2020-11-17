package com.sinosoft.surrender.policyimport.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.constant.ENUM_PROCESS_STATUS_CODE;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.service.PolicyImportDealService;

public class PolicyImportDealServiceTest extends BaseTester {
	@Autowired
	private PolicyImportDealService policyImportDealService;

	@Test
	public void testQuerySurrender() {
		QuerySurrenderReqDTO querySurrenderReqDTO = new QuerySurrenderReqDTO();

		try {
			logger.info(XmlUtil.toXml(policyImportDealService.querySurrender(querySurrenderReqDTO)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	@Test
	public void testRevisePolicyApplyState(){
		PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO = new PolicyApplyStateReviseReqDTO();
		policyApplyStateReviseReqDTO.setTransNo("2000002");
		policyApplyStateReviseReqDTO.setContNo("1001201804041018");
		//保全撤销
		policyApplyStateReviseReqDTO.setAcceptStatus(ENUM_PROCESS_STATUS_CODE.CANCEL.getValue());
		
		try {
			logger.info("处理状态修改，保单号：{}，交易流水号：{}",policyApplyStateReviseReqDTO.getContNo(),policyApplyStateReviseReqDTO.getTransNo());
			policyImportDealService.revisePolicyApplyState(policyApplyStateReviseReqDTO);
			logger.info("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
