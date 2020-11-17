package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.FillCalCashValueParamBO;
import com.sinosoft.surrender.cashvalue.bo.abs.AbsCalPolCashValueBO;
import com.sinosoft.surrender.cashvalue.dto.CalCashValueParamDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.AlgorithUtil;

/**
 * 
 * 按险种,插值算法，计算保单现价通用
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-下午2:15:25
 * @version:
 */
@Component
public class CalPolCashValueCommonBOImpl extends AbsCalPolCashValueBO {
	@Resource(name = "fillCalCashValueParamCommonBOImpl")
	private FillCalCashValueParamBO fillCalCashValueParamBO;

	@Override
	protected CalCashValueParamDTO fillCalCashValueParam(SurrenderDTO surrenderDTO) {
		CalCashValueParamDTO calCashValueParamDTO = fillCalCashValueParamBO.fillCalCashValueParam(surrenderDTO);
		return calCashValueParamDTO;
	}

	@Override
	protected BigDecimal calDutyCashValue(CalCashValueParamDTO calCashValueParamDTO) {
		BigDecimal dutyCashValue = AlgorithUtil.calCashValueByDifference(calCashValueParamDTO);
		return dutyCashValue;
	}

}
