package com.sinosoft.surrender.policyimport.batch.importUtils;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public class BackupXmlUtilTest extends BaseTester{
	@Autowired
	private PolicyImportDealDAO policyImportDealDAO;

	@Test
	public void testBackupXML(){
		try {
			List<SurrenderImportCoreDTO> list = policyImportDealDAO.querySurrenderDataToCore();
			SurrenderImportCoreDTO surrenderImportToCoreDTO = list.get(0);
			String reqXml = DtoToCoreXml.dtoToXml(surrenderImportToCoreDTO);
			logger.info(XmlUtil.toXml(reqXml));
			BackupXmlUtil.backupXML(surrenderImportToCoreDTO, reqXml, "_req.xml");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
