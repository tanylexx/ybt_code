package com.sinosoft.surrender.policyimport.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;

public class PolicyImportDealDAOImplTest extends BaseTester{
	@Autowired
	private PolicyImportDealDAO policyImportDealDAO;
	
	@Test
	public void testModifyProcessingState(){
		PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO = new PolicyApplyStateReviseReqDTO();
		policyApplyStateReviseReqDTO.setContNo("889977665544");
		policyApplyStateReviseReqDTO.setTransNo("10000001");
		policyApplyStateReviseReqDTO.setAcceptStatus("00");
		try {
			logger.info("更新开始");
			policyImportDealDAO.modifyProcessingState(policyApplyStateReviseReqDTO);
			logger.info("更新成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQuerySurrender(){
		QuerySurrenderReqDTO querySurrenderReqDTO = new QuerySurrenderReqDTO();
		/*querySurrenderReqDTO.setBankCode("102");
		querySurrenderReqDTO.setContNo("1001201803300920");
		querySurrenderReqDTO.setEdorType("CT");
		querySurrenderReqDTO.setAcceptStatus("01");
		querySurrenderReqDTO.setStartDate(DateUtil.getDate("2017/01/01 00:00:00"));
		querySurrenderReqDTO.setEndDate(new Date());*/
		String str = XmlUtil.toXml(policyImportDealDAO.querySurrender(querySurrenderReqDTO));
		System.out.println(str);
		
		/*try {
			logger.info("c查询结果：{}",XmlUtil.toXml(policyImportDealDAO.querySurrender(querySurrenderReqDTO)));
		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}
	
	@Test
	public void testQuerySurrenderDataToCore(){
		try {
			logger.info("c查询结果：{}",XmlUtil.toXml(policyImportDealDAO.querySurrenderDataToCore()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testModifyProcessingStateImportToCore(){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("transNo", "2000001");
		map.put("contNo", "1001201803300920");
		map.put("processStatus", "01");
		map.put("processResult", "导入成功");
		
		try {
			logger.info("开始修改");
			policyImportDealDAO.modifyProcessingStateImportToCore(map);
			logger.info("修改成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
