package com.sinosoft.surrender.addprem.dao;

import com.sinosoft.surrender.addprem.dto.AddPremImportDto;

import java.util.List;
import java.util.Map;

public interface AddPremDao {
    /**
     * 查询该险种下的保额计算代码
     * @param contno
     * @param polno
     * @return
     */
    String getCalCode(String contno, String polno);

    /**
     * 查询待导入核心的追加保费保单数据
     * @return
     */
    public List<AddPremImportDto> queryImportCoreContData();

    /**
     *更改导核心的状态
     * @param modifyField
     */
    public void modifyImportCoreProcessStatus(Map<String, Object> modifyField);

    /**
     *更改导核心的状态
     * @param map
     */
    public String calRiskAmnt(Map<String, Object> map);
}
