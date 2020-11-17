package com.sinosoft.surrender.cashvalue.dao.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.cashvalue.dao.YcPolicyCashValueSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

@Repository
public class YcPolicyCashValueSpecDAOImpl extends SurrenderBaseDAOImpl implements YcPolicyCashValueSpecDAO {

	@Override
	public List<YcPolicyCashValue> getYcPolicyCashValue(PolicyCashValueReqDTO policyCashValueReqDTO) {

		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("contno", policyCashValueReqDTO.getContNo());
		mapParam.put("applydate", policyCashValueReqDTO.getApplyDate());
		mapParam.put("edortype", policyCashValueReqDTO.getEdorType());
		@SuppressWarnings("unchecked")
		List<YcPolicyCashValue> ycPolicyCashValueList = this
				.queryForList("YCPOLICYCASHVALUESPECDAO.getYcPolicyCashValue", mapParam);
		return ycPolicyCashValueList;
	}

	@Override
	public BigDecimal findYcPolicyCashValue(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contNo", surrenderConfirmReqDTO.getContNo());
		map.put("confirmDate", surrenderConfirmReqDTO.getConfirmDate());
		map.put("edorType", surrenderConfirmReqDTO.getEdorType());
		Object obj = this.queryForObject("YCPOLICYCASHVALUESPECDAO.findYcPolicyCashValue", map);
		if (obj != null) {
			BigDecimal cashValue = (BigDecimal) obj;
			return cashValue;
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<YcPolicyCashValue> getAllYcPolicyCashValue() {
		List<YcPolicyCashValue> ycPolicyCashValueList = this
				.queryForList("YCPOLICYCASHVALUESPECDAO.getAllYcPolicyCashValue", null);
		return ycPolicyCashValueList;
	}

	@Override
	public void updateAllYcPolicyCashValue() {
		this.updateObject("YCPOLICYCASHVALUESPECDAO.updateAllYcPolicyCashValue", null);
	}

	@Override
	public void updateYcPolicyCashValueByKey(PolicyCashValueReqDTO policyCashValueReqDTO) {
		Map<String, Object> mapParam = new HashMap<String, Object>();
		mapParam.put("contno", policyCashValueReqDTO.getContNo());
		mapParam.put("applydate", policyCashValueReqDTO.getApplyDate());
		mapParam.put("edortype", policyCashValueReqDTO.getEdorType());
		this.updateObject("YCPOLICYCASHVALUESPECDAO.updateYcPolicyCashValueByKey", mapParam);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<YcPolicyCashValue> getDaysAgoYcPolicyCashValue(Date date) {
		
		return this.queryForList("YCPOLICYCASHVALUESPECDAO.getDaysAgoYcPolicyCashValue", date);
		 
	}

	@Override
	public BigDecimal findYcPolicyCashValueToConfirm(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("contNo", surrenderConfirmReqDTO.getContNo());
		map.put("confirmDate", surrenderConfirmReqDTO.getConfirmDate());
		map.put("edorType", surrenderConfirmReqDTO.getEdorType());
		Object obj = this.queryForObject("YCPOLICYCASHVALUESPECDAO.findYcPolicyCashValueToConfirm", map);
		if (obj != null) {
			BigDecimal cashValue = (BigDecimal) obj;
			return cashValue;
		}
		return null;
	}
	
}
