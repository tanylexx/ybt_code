package com.sinosoft.surrender.policyimport.dao;

import java.util.List;
import java.util.Map;

import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;

public interface PolicyImportDealDAO {
	/**
	 * 修改处理状态 （前端调用，选择重导，还是保全撤销） 
	 *
	 * @history: 2018年3月26日
	 * @author: zhongdw_sinosoft
	 * @param policyApplyStateReviseReqDTO
	 */
	public void modifyProcessingState(PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO);

	/**
	 * 查询退保中间表的保单:对外，前端调用的
	 *
	 * @history: 2018年3月26日
	 * @author: zhongdw_sinosoft
	 * @param policyCashValueReqDTO
	 * @return
	 */
	public List<QuerySurrenderResDTO> querySurrender(QuerySurrenderReqDTO querySurrenderReqDTO);
	
	/**
	 * 	
	 * 查询导入核心的数据:对内，批处理导核心
	 *
	 * @history: 2018年3月29日
	 * @author: zhongdw_sinosoft
	 * @return
	 */
	public List<SurrenderImportCoreDTO> querySurrenderDataToCore();
	
	/**
	 * 	
	 * 修改处理状态 :对内，批处理导核心修改处理状态
	 *
	 * @history: 2018年3月30日
	 * @author: zhongdw_sinosoft
	 * @param map
	 */
	public void modifyProcessingStateImportToCore(Map<String,Object> map);
}
