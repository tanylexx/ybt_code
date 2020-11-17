package com.sinosoft.surrender.addprem.bo;

import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.addprem.dto.res.PEdorAddPremResDto;
import com.sinosoft.surrender.db.model.LcPol;

import java.util.List;

public interface AddPremAmntCalBO {
    /**
     * 计算追加保费对应的保额
     * @param pEdorAddPremReqDto
     */
    public void calAmntAndUWRulesCheck(PEdorAddPremReqDto pEdorAddPremReqDto) throws IllegalAccessException;
}
