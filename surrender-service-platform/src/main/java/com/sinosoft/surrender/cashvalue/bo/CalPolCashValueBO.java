package com.sinosoft.surrender.cashvalue.bo;

import com.sinosoft.surrender.cashvalue.dto.CashValueDetailDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;

/**
 * 
 * 计算险种查层级的保单现价
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-下午2:14:29
 * @version:
 */
public interface CalPolCashValueBO {

	/**
	 * 计算险种查层级的保单现价
	 * 
	 * @history: 2018-3-7
	 * @author: wangwl_sinosoft
	 * @return
	 */
	CashValueDetailDTO calPolCashValueByCalType(SurrenderDTO endorseDTO);

}
