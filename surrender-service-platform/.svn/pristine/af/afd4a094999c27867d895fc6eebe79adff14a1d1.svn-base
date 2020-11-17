package com.sinosoft.surrender.cashvalue.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.surrender.cashvalue.bo.SurrenderTradeTraceBO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.cashvalue.service.SurrenderTradeTraceService;
@Service
public class SurrenderTradeTraceServiceImpl implements SurrenderTradeTraceService {
	@Autowired
	private SurrenderTradeTraceBO surrenderTradeTraceBO;

	@Override
	public BigDecimal createTradeTrace(PolicyCashValueReqDTO policyCashValueReqDTO) {
		
		return surrenderTradeTraceBO.createTradeTrace(policyCashValueReqDTO);
	}

	@Override
	public void updateTradeTrace(BigDecimal sid, long currentTime, PolicyCashValueResDTO policyCashValueResDTO) {
		surrenderTradeTraceBO.updateTradeTrace(sid, currentTime, policyCashValueResDTO);

	}

}
