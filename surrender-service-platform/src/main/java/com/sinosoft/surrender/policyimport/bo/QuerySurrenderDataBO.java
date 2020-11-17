package com.sinosoft.surrender.policyimport.bo;

import java.util.List;

import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public interface QuerySurrenderDataBO {
	
	/**
	 * 	
	 * 查询导入核心的数据  
	 *
	 * @history: 2018年4月1日
	 * @author: zhongdw_sinosoft
	 * @return
	 */
	public List<SurrenderImportCoreDTO> querySurrenderData();
}
