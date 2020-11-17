package com.sinosoft.surrender.cashvalue.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.SurrenderCheckBaseSpecDAO;
import com.sinosoft.surrender.db.model.LdCode;
import com.sinosoft.surrender.db.model.LdSysTrace;
import com.sinosoft.surrender.db.model.LpFreeze;

public class SurrenderCheckBaseSpecDAOImplTest extends BaseTester {
	@Autowired
	private SurrenderCheckBaseSpecDAO surrenderCheckBaseSpecDAO;
	@Test
	public void testGetEdorAcceptNo(){
		String contNo = "8000000001109638";
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getEdorAcceptNo(contNo)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLpedoritemInfo(){
		String contNo = "8000000001109638";
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getLpedoritemInfo(contNo)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetReportCount(){
		String contNo = "8000000001109638";
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getReportCount(contNo)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetBankOnTheWay(){
		String contNo = "3101001210012298";
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getBankOnTheWay(contNo)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLySendTobankCount(){
		String contNo = "3101001210012298";
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getLySendTobankCount(contNo)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLdsystraceInfo(){
		LdSysTrace ldSysTrace = new LdSysTrace();
		ldSysTrace.setPolno("1001010803080188");
		ldSysTrace.setPolstate("1002");
		ldSysTrace.setValiflag("1");
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getLdsystraceInfo(ldSysTrace)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLpFreezeInfo(){
		LpFreeze lpFreeze = new LpFreeze();
		lpFreeze.setContno("8828233466308128");
		lpFreeze.setFreezestate("0");
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getLpFreezeInfo(lpFreeze)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetLdCodeInfo(){
		LdCode ldCode = new LdCode();
		ldCode.setCodetype("bank_fenhang");
		ldCode.setCode("0000001562");
		try {
			logger.info("查询结果：{}",XmlUtil.toXml(surrenderCheckBaseSpecDAO.getLdCodeInfo(ldCode)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
