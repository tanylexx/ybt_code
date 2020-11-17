package com.sinosoft.surrender.surrconfirm.bo.impl;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderConfirmBO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

public class SurrenderConfirmBOImplTest extends BaseTester{
	@Autowired
	private SurrenderConfirmBO surrenderConfirmBO;
	
	SurrenderConfirmReqDTO surrenderConfirmReqDTO = new SurrenderConfirmReqDTO();
	
	@Test
	public void testSaveSurrender(){
		SurrenderConfirmReqDTO surrenderConfirmReqDTO = new SurrenderConfirmReqDTO();
		surrenderConfirmReqDTO.setTransNo("20180408000007");
		surrenderConfirmReqDTO.setContNo("3703001790000018");
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
			logger.info("updateSurrender方法开始调用");
			surrenderConfirmBO.saveSurrender(surrenderConfirmReqDTO, new BigDecimal("10000.00") ,"9048800000055888");
			logger.info("updateSurrender方法调用结束");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
}
