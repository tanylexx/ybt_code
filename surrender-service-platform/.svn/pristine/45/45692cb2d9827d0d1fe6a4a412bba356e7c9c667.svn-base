package com.sinosoft.surrender.cashvalue.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.db.model.LcPol;

public interface LcPolSpecDAO {

	/**
	 * 
	 * 根据保单号获取险种表信息
	 * 
	 * @history: 2018-3-12
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	List<LcPol> getLcPolByContNo(String contNo);
	
	
	/**
	 * 	
	 * 查询试算批处理所需的DTO
	 *
	 * @history: 2018年4月18日
	 * @author: zhongdw_sinosoft
	 * @param rowNum
	 * @return
	 */
	List<PolicyCashValueReqDTO> queryPrecalculatedParam(Map<String, Object> map);

	/**
	 * 	
	 * 查询调核心试算批处理所需的DTO
	 *
	 * @history: 2018年4月18日
	 * @author: zhongdw_sinosoft
	 * @param rowNum
	 * @return
	 */
	List<PolicyCashValueReqDTO> queryCallCorePrecalculatedParam(Map<String, Object> map);
}
