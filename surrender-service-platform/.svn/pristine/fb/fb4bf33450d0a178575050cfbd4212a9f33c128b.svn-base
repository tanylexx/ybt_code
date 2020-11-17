package com.sinosoft.surrender.surrconfirm.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.surrconfirm.dao.YkSurrenderTraceSpecDAO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;


public class YkSurrenderTraceSpecDAOImplTest extends BaseTester{
	@Autowired
	private YkSurrenderTraceSpecDAO ykSurrenderTraceSpecDAO;
	
	@Test
	public void testFindSurrenderTrace(){
	SurrenderConfirmReqDTO surrenderConfirmReqDTO = new SurrenderConfirmReqDTO();
		surrenderConfirmReqDTO.setTransNo("PSBCct2017051500652");
		surrenderConfirmReqDTO.setContNo("3101001890105628");
		surrenderConfirmReqDTO.setEdorType("CT");
		surrenderConfirmReqDTO.setFunctionFlag("27");
		surrenderConfirmReqDTO.setOldTransNo("1000002");
		surrenderConfirmReqDTO.setZoneNo("2103");
		surrenderConfirmReqDTO.setBrNo("210303003");
		surrenderConfirmReqDTO.setTellerNo("9310000039");
		surrenderConfirmReqDTO.setBankCode("05");
		surrenderConfirmReqDTO.setSourceType("2");
		surrenderConfirmReqDTO.setConfirmDate(DateUtil.getCurrentDate());
		surrenderConfirmReqDTO.setApplyPlatform("YBT");
		surrenderConfirmReqDTO.setBankAccNo("62220220151007111");
		surrenderConfirmReqDTO.setBankAccName("设计数2");
		surrenderConfirmReqDTO.setEdorPrtNo("2000000000000008");
		
		try {
			logger.info("开始查询");
			logger.info("c查询结果：{}",XmlUtil.toXml(ykSurrenderTraceSpecDAO.findSurrenderTrace(surrenderConfirmReqDTO)));
			logger.info("开始成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

		
		
	}
	
}
