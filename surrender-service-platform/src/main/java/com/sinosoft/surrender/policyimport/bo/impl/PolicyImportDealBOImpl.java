package com.sinosoft.surrender.policyimport.bo.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.policyimport.bo.PolicyImportDealBO;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;

@Component
public class PolicyImportDealBOImpl implements PolicyImportDealBO {
	@Autowired
	public PolicyImportDealDAO policyImportDealDAO;
	
	@Override
	public void modifyProcessingState(PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO) {
		policyImportDealDAO.modifyProcessingState(policyApplyStateReviseReqDTO);
	}

	@Override
	public List<QuerySurrenderResDTO> querySurrender(QuerySurrenderReqDTO querySurrenderReqDTO) {
		return policyImportDealDAO.querySurrender(querySurrenderReqDTO);
	}

}
