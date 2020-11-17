package com.sinosoft.surrender.policyimport.bo.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.policyimport.bo.SurrenderImportCoreBO;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public class GuideCoreBOImplTest extends BaseTester{
	@Autowired
	private PolicyImportDealDAO policyImportDealDAO;
	@Autowired
	private SurrenderImportCoreBO surrenderImportCoreBO;
	@Test
	public void doGiderCoreBatch(){
		try {
			List<SurrenderImportCoreDTO> list = policyImportDealDAO.querySurrenderDataToCore();
			SurrenderImportCoreDTO surrenderImportToCoreDTO = list.get(0);
			
			surrenderImportCoreBO.doGiderCoreBatch(surrenderImportToCoreDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}