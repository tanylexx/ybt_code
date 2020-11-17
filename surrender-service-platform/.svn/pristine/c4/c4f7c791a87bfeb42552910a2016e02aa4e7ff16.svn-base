package com.sinosoft.surrender.addprem.dao.impl;

import com.sinosoft.surrender.addprem.dao.AddPremDao;
import com.sinosoft.surrender.addprem.dto.AddPremImportDto;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class AddPremDaoImpl extends SurrenderBaseDAOImpl implements AddPremDao {
    @Override
    public String getCalCode(String contno, String polno) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("contno", contno);
        map.put("polno", polno);
        return (String) this.queryForObject("ADDPREMSPEC.getCalCode", map);
    }

    @Override
    public List<AddPremImportDto> queryImportCoreContData() {
        Map<String, Object> mapParam = new HashMap<String, Object>();
        // 从配置文件，拿到rownum,riskcode
        int rowNum = 1000;
        Object obj = ConfigInfo.getContextProperty("ROWNUM");
        if (obj != null) {
            rowNum = Integer.valueOf((String) ConfigInfo.getContextProperty("ROWNUM"));
        }
        mapParam.put("rowNum", rowNum);
        return this.queryForList("ADDPREMSPEC.querySurrenderDataToCore", mapParam);
    }

    @Override
    public void modifyImportCoreProcessStatus(Map<String, Object> modifyField) {
        this.updateObject("ADDPREMSPEC.modifyProcessingStateImportToCore", modifyField);
    }

    @Override
    public String calRiskAmnt(Map<String, Object> map) {
        return (String) this.queryForObject("ADDPREMSPEC.calRiskAmnt", map);
    }
}
