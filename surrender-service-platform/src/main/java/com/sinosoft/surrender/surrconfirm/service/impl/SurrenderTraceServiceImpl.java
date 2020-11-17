package com.sinosoft.surrender.surrconfirm.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.surrender.surrconfirm.bo.SurrenderTraceBO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;
import com.sinosoft.surrender.surrconfirm.dto.res.SurrenderConfirmResDTO;
import com.sinosoft.surrender.surrconfirm.service.SurrenderTraceService;

@Service
public class SurrenderTraceServiceImpl implements SurrenderTraceService {
	@Autowired
	private SurrenderTraceBO surrenderTraceBO;

	@Override
	public BigDecimal saveSurrenderTrace(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {

		return surrenderTraceBO.saveSurrenderTrace(surrenderConfirmReqDTO);
	}

	@Override
	public void updateSurrenderTrace(SurrenderConfirmResDTO surrenderConfirmResDTO, BigDecimal surrenderTraceSid,
			long confirmStartTime) {
		surrenderTraceBO.updateSurrenderTrace(surrenderConfirmResDTO, surrenderTraceSid, confirmStartTime);
	}

}
