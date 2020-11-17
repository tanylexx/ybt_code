package com.sinosoft.surrender.addprem.bo;

import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.db.model.LcPol;

public interface AddPremCheckBO {
    /**
     * webservice的入参非空校验
     * @param pEdorAddPremReqDto
     */
    public void checkParam(PEdorAddPremReqDto pEdorAddPremReqDto);

    /**
     * 保费追加的规则校验
     * @param pEdorAddPremReqDto
     * @param algorithElementDTO
     * @param lcPol
     */
    public void checkUWRules(PEdorAddPremReqDto pEdorAddPremReqDto, AlgorithElementDTO algorithElementDTO, LcPol lcPol) throws IllegalAccessException;
}
