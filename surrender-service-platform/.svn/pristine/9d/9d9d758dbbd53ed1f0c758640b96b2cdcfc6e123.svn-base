package com.sinosoft.surrender.cashvalue.dao.impl;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.SurrenderCheckBaseSpecDAO;

public class SurrenderCheckBaseSpecDAOTest1 extends BaseTester {
	@Autowired
	private SurrenderCheckBaseSpecDAO surrenderCheckBaseSpecDAO;

	@Test
	public void testGetClaimCount() {
		try {
			BigDecimal claimCount = surrenderCheckBaseSpecDAO.getClaimCount("9016000000003958");
			logger.info(XmlUtil.toXml(claimCount));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testGetLoloanCount() {
		try {
			BigDecimal loloanCount = surrenderCheckBaseSpecDAO.getLoloanCount("4115001310002888");
			logger.info(XmlUtil.toXml(loloanCount));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	@Test
	public void testGetConfigHesitateIntv(){
		
		/*try {
			LisEdorHesitateIntvDTO configHesitateIntv = surrenderCheckBaseSpecDAO.getConfigHesitateIntv("4115001310002888");
			logger.info(XmlUtil.toXml(configHesitateIntv));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
	}
	

}
