package com.sinosoft.surrender.addprem.bo.impl;


import com.sinosoft.surrender.addprem.bo.QueryAddPremContDataBO;
import com.sinosoft.surrender.addprem.dao.AddPremDao;
import com.sinosoft.surrender.addprem.dto.AddPremImportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class QueryAddPremContDataBOImpl implements QueryAddPremContDataBO {
    @Autowired
    private AddPremDao addPremDao;

    @Override
    public List<AddPremImportDto> queryAddPremContData() {
        return addPremDao.queryImportCoreContData();
    }
}
