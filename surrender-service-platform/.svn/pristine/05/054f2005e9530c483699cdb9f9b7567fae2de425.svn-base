package com.sinosoft.surrender.cashvalue.bo;

import java.math.BigDecimal;
import java.util.List;

import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.db.model.YkSurrenderTrace;

/**
 * 
 * 日志BO类
 *
 * @author:  wangwl_sinosoft
 * @date: 2018-3-8-下午2:17:15
 * @version:
 */
public interface SurrenderTradeTraceBO {
	/**
	 * 
	 * 创建日志
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 */
	BigDecimal createTradeTrace(PolicyCashValueReqDTO policyCashValueReqDTO);

	/**
	 * 
	 * 修改日志
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 */
	void updateTradeTrace(BigDecimal sid,long currentTime,PolicyCashValueResDTO policyCashValueResDTO);
	/**
	 * 
	 * 查询日志表
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @param applyDate
	 * @return
	 */
	List<YkSurrenderTrace> getYkSurrenderTraceInfo(PolicyCashValueReqDTO policyCashValueReqDTO);
}
