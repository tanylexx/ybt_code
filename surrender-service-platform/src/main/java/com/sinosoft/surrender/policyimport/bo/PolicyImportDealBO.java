package com.sinosoft.surrender.policyimport.bo;

import java.util.List;

import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;

/**
 * 
 * TODO 退保导入接口的BO
 *
 * @author:  zhongdw_sinosoft
 * @date: 2018年3月26日-下午2:20:09
 * @version:
 */
public interface PolicyImportDealBO {
	/**
	 * 	
	 * 保全受理状态修改  
	 *
	 * @history: 2018年3月26日
	 * @author: zhongdw_sinosoft
	 * @param policyApplyStateReviseReqDTO
	 */
	public void modifyProcessingState(PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO);
	
	/**
	 * 根据传入得参数查询 
	 *
	 * @history: 2018年3月26日
	 * @author: zhongdw_sinosoft
	 * @param querySurrenderReqDTO
	 * @return
	 */
	public List<QuerySurrenderResDTO> querySurrender(QuerySurrenderReqDTO querySurrenderReqDTO);
}
