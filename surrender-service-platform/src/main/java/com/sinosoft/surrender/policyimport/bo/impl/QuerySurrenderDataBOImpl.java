package com.sinosoft.surrender.policyimport.bo.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.policyimport.bo.QuerySurrenderDataBO;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public class QuerySurrenderDataBOImpl implements QuerySurrenderDataBO{
	@Autowired
	private PolicyImportDealDAO policyImportDealDAO;
	
	@Override
	public List<SurrenderImportCoreDTO> querySurrenderData() {
		return policyImportDealDAO.querySurrenderDataToCore();
	}

}
