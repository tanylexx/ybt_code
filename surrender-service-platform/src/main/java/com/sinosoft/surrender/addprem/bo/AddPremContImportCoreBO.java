package com.sinosoft.surrender.addprem.bo;


import com.sinosoft.lis.specedor.dto.res.LisSpecEdorResDTO;
import com.sinosoft.surrender.addprem.dto.AddPremImportDto;

import java.util.Map;

public interface AddPremContImportCoreBO {
    /**
     * 调用核心webservice接口
     * @param addPremImportDto
     */
    public LisSpecEdorResDTO callWebService(AddPremImportDto addPremImportDto);

    void modifyImportCoreProcessStatus(Map<String, Object> map);
}
