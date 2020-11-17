package com.sinosoft.surrender.cashvalue.bo;

import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;

/**
 * 
 * 计算时刻保单现价
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-下午2:12:59
 * @version:
 */
public interface CalPolicyCashValueBO {

	/**
	 * 
	 * 计算时刻保单现价
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param policyCashValueReqDTO
	 * @return
	 */
	PolicyCashValueResDTO calConstantlyPolicyCashValue(PolicyCashValueReqDTO policyCashValueReqDTO);

}
