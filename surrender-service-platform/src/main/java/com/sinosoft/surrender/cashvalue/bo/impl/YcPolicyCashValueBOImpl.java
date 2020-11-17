package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sinosoft.surrender.cashvalue.bo.YcPolicyCashValueBO;
import com.sinosoft.surrender.cashvalue.dao.YcPolicyCashValueSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

@Component
public class YcPolicyCashValueBOImpl implements YcPolicyCashValueBO {
	@Autowired
	private YcPolicyCashValueSpecDAO ycPolicyCashValueSpecDAO;

	@Override
	public void updateYcPolicyCashValueByKey(PolicyCashValueReqDTO policyCashValueReqDTO) {
		ycPolicyCashValueSpecDAO.updateYcPolicyCashValueByKey(policyCashValueReqDTO);
	}

	@Override
	public List<YcPolicyCashValue> getYcPolicyCashValue(PolicyCashValueReqDTO policyCashValueReqDTO) {
		return ycPolicyCashValueSpecDAO.getYcPolicyCashValue(policyCashValueReqDTO);
	}

	@Override
	public BigDecimal findYcPolicyCashValue(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		
		return ycPolicyCashValueSpecDAO.findYcPolicyCashValue(surrenderConfirmReqDTO);
	}

	@Override
	public List<YcPolicyCashValue> getAllYcPolicyCashValue() {
		return ycPolicyCashValueSpecDAO.getAllYcPolicyCashValue();
	}

	@Override
	public void updateAllYcPolicyCashValue() {
		ycPolicyCashValueSpecDAO.updateAllYcPolicyCashValue();		
	}

	@Override
	public List<YcPolicyCashValue> getDaysAgoYcPolicyCashValue(Date date) {
		
		return ycPolicyCashValueSpecDAO.getDaysAgoYcPolicyCashValue(date);
	}

}
