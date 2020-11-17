package com.sinosoft.surrender.surrconfirm.bo.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.surrconfirm.bo.GetSurrenderMoneyBO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

public class GetSurrenderMoneyBOImplTest extends BaseTester{
	@Autowired
	private GetSurrenderMoneyBO getSurrenderMoneyBO;
	
	@Test
	public void testGetAccountValue(){
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
			logger.info("开始查询");
			logger.info("c查询结果：{}",XmlUtil.toXml(getSurrenderMoneyBO.getSurrenderMoney(surrenderConfirmReqDTO)));
			logger.info("开始成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
