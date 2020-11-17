package com.sinosoft.surrender.cashvalue.bo;

import java.math.BigDecimal;

import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;

/**
 * 
 * 保存退保中间表数据 BO
 *
 * @author:  wangwl_sinosoft
 * @date: 2018-3-8-下午8:00:33
 * @version:
 */
public interface SurrenderSaveDataBO {

	/**
	 * 	
	 * 保存退保中间表数据  
	 *
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param policyCashValueReqDTO
	 */
	  void saveData(BigDecimal policyCashValue,PolicyCashValueReqDTO policyCashValueReqDTO);
}
