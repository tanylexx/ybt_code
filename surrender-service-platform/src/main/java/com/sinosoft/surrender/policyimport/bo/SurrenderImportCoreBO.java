package com.sinosoft.surrender.policyimport.bo;

import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public interface SurrenderImportCoreBO {
	/**
	 * 导核心批处理操作
	 *
	 * @history: 2018年4月3日
	 * @author: zhongdw_sinosoft
	 * @param surrenderDataDTO
	 */
	public void doGiderCoreBatch(SurrenderImportCoreDTO surrenderImportCoreDTO);
}
