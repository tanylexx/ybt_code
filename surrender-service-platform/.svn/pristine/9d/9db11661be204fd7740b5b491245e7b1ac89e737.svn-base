package com.sinosoft.surrender.surrconfirm.service;

import java.math.BigDecimal;

import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;
import com.sinosoft.surrender.surrconfirm.dto.res.SurrenderConfirmResDTO;

public interface SurrenderTraceService {

	/**
	 * 保存交易记录到日志表中
	 * 
	 * @param ykSurrenderTrace
	 */
	BigDecimal saveSurrenderTrace(SurrenderConfirmReqDTO surrenderConfirmReqDTO);

	/**
	 * 
	 * 更新交易日志
	 *
	 * @history: 2018年4月8日
	 * @author: zhongdw_sinosoft
	 * @param ykSurrenderTrace
	 */
	void updateSurrenderTrace(SurrenderConfirmResDTO surrenderConfirmResDTO, BigDecimal surrenderTraceSid,
			long confirmStartTime);
}
