package com.sinosoft.surrender.surrconfirm.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;
import com.sinosoft.surrender.surrconfirm.service.SurrenderConfirmService;

public class SurrenderConfirmServiceImplTest extends BaseTester {
	@Autowired
	private SurrenderConfirmService surrenderConfirmService;
	@Test
	public void testConfirmSurrender(){
		SurrenderConfirmReqDTO surrenderConfirmReqDTO = new SurrenderConfirmReqDTO();
		surrenderConfirmReqDTO.setTransNo("09175516666333456");
		surrenderConfirmReqDTO.setContNo("3101001690797668");
		surrenderConfirmReqDTO.setEdorType("CT");
		surrenderConfirmReqDTO.setFunctionFlag("24");
		surrenderConfirmReqDTO.setOldTransNo("20180409000001");
		surrenderConfirmReqDTO.setZoneNo("3700");
		surrenderConfirmReqDTO.setBrNo("37005038");
		surrenderConfirmReqDTO.setTellerNo("18030502");
		surrenderConfirmReqDTO.setBankCode("05");
		surrenderConfirmReqDTO.setSourceType("4");
		surrenderConfirmReqDTO.setConfirmDate(DateUtil.calDate(DateUtil.getCurrentDate(), 1,"D",null));
		surrenderConfirmReqDTO.setApplyPlatform("YBT");
		surrenderConfirmReqDTO.setBankAccNo("62220220151007111");
		surrenderConfirmReqDTO.setBankAccName("设计数2");
		surrenderConfirmReqDTO.setEdorPrtNo("2000000000000008");
		try {
			logger.info("返回参数结果：{}",XmlUtil.toXml(surrenderConfirmService.confirmSurrender(surrenderConfirmReqDTO)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
