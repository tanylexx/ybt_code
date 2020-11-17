package com.sinosoft.surrender.policyimport.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;

@Repository
public class PolicyImportDealDAOImpl extends SurrenderBaseDAOImpl implements PolicyImportDealDAO {

	@Override
	public void modifyProcessingState(PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO) {
		this.updateObject("POLICYIMPORTDEALDAO.modifyProcessingState", policyApplyStateReviseReqDTO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<QuerySurrenderResDTO> querySurrender(QuerySurrenderReqDTO querySurrenderReqDTO) {
		return this.queryForList("POLICYIMPORTDEALDAO.querySurrender", querySurrenderReqDTO);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SurrenderImportCoreDTO> querySurrenderDataToCore() {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		// 从配置文件，拿到rownum,riskcode
		int rowNum = 1000;
		Object obj = ConfigInfo.getContextProperty("ROWNUM");
		if (obj != null) {
			rowNum = Integer.valueOf((String) ConfigInfo.getContextProperty("ROWNUM"));
		}
		mapParam.put("rowNum", rowNum);
		return this.queryForList("POLICYIMPORTDEALDAO.querySurrenderDataToCore", mapParam);
	}

	@Override
	public void modifyProcessingStateImportToCore(Map<String, Object> map) {
		this.updateObject("POLICYIMPORTDEALDAO.modifyProcessingStateImportToCore", map);
	}

}
