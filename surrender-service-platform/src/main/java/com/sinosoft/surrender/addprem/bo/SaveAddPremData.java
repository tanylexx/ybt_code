package com.sinosoft.surrender.addprem.bo;

import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;

public interface SaveAddPremData {
    /**
     * 保存数据到追加保费导核心中间表中
     * @param pEdorAddPremReqDto
     */
    public void saveAddPremData(PEdorAddPremReqDto pEdorAddPremReqDto);
}
