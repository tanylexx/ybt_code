package com.sinosoft.surrender.policyimport.batch.importUtils;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public class DtoToCoreXmlTest extends BaseTester {
	@Autowired
	private PolicyImportDealDAO policyImportDealDAO;
	
	
	@Test
	public void testDtoToXml(){
		try {
			List<SurrenderImportCoreDTO> list = policyImportDealDAO.querySurrenderDataToCore();
			SurrenderImportCoreDTO surrenderImportToCoreDTO = list.get(0);
			logger.info("c查询结果：{}", DtoToCoreXml.dtoToXml(surrenderImportToCoreDTO));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
