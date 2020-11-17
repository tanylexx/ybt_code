package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.FillCashValueDetailDTOBO;
import com.sinosoft.surrender.cashvalue.dto.CashValueDetailDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_FEE_TYPE;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.LmRiskAppDAO;
import com.sinosoft.surrender.db.model.LmRiskApp;
@Component
public class FillCashValueDetailDTOBOImpl implements FillCashValueDetailDTOBO{
	@Autowired
	private LmRiskAppDAO lmRiskAppDAO;
	@Override
	public CashValueDetailDTO fillCashValueDetailDTO(BigDecimal polCashValue, SurrenderDTO surrenderDTO) {
		
		CashValueDetailDTO cashValueDetailDTO = new CashValueDetailDTO();
		cashValueDetailDTO.setMoney(polCashValue.setScale(2, BigDecimal.ROUND_HALF_UP));
		cashValueDetailDTO.setPolNo(surrenderDTO.getLcPol().getPolno());
		cashValueDetailDTO.setRiskCode(surrenderDTO.getLcPol().getRiskcode());
		cashValueDetailDTO.setFeeOperationType(surrenderDTO.getEdorType());
		if (StringUtils.isNotBlank(surrenderDTO.getFeeType())) {
			cashValueDetailDTO.setFeeFinaType(surrenderDTO.getFeeType());
			return cashValueDetailDTO;
		}
		LmRiskApp lmRiskApp = lmRiskAppDAO.selectByPrimaryKey(surrenderDTO.getLcPol().getRiskcode());
		ExceptionUtil.checkNull(lmRiskApp, "在计算解约退费时获取固定贴现利率失败");
		cashValueDetailDTO.setFeeFinaType(ENUM_FEE_TYPE.TF.getValue());
		if (Constant.LONG.equals(lmRiskApp.getRiskperiod())) {
			cashValueDetailDTO.setFeeFinaType(ENUM_FEE_TYPE.TB.getValue());
		}
		return cashValueDetailDTO;
	}

}
