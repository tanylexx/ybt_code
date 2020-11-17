package com.sinosoft.surrender.addprem.bo;

import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.addprem.dto.res.PEdorAddPremResDto;

import java.math.BigDecimal;

public interface AddPremTraceBO {
    /**
     * 保存交易轨迹记录
     * @param pEdorAddPremReqDto
     */
    public BigDecimal saveAddPremTrace(PEdorAddPremReqDto pEdorAddPremReqDto);

    /**
     * 更新交易轨迹记录
     * @param pEdorAddPremResDto
     */
    void updateAddPremTrace(PEdorAddPremResDto pEdorAddPremResDto, BigDecimal addPremTraceId, long confirmStartTime);
}
