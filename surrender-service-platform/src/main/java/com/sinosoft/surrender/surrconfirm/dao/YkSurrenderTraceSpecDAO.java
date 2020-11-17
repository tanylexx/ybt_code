package com.sinosoft.surrender.surrconfirm.dao;

import com.sinosoft.surrender.db.model.YkSurrenderTrace;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

public interface YkSurrenderTraceSpecDAO {
	
	/**
	 * 查询交易日志记录
	 * @param surrenderConfirmReqDTO
	 * @return
	 */
	public YkSurrenderTrace findSurrenderTrace(SurrenderConfirmReqDTO surrenderConfirmReqDTO);
}
