package com.sinosoft.surrender.batchRenew.bo.impl;

import com.sinosoft.surrender.batchRenew.bo.RenewContImportBo;
import com.sinosoft.surrender.batchRenew.importUtils.ENUM_RENEW_STATUS;
import com.sinosoft.surrender.db.dao.YcRenewALStatusLogDAO;
import com.sinosoft.surrender.db.model.YcRenewALStatusLog;
import com.sinosoft.surrender.db.model.YcRenewALStatusLogExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RenewContImportBoImpl implements RenewContImportBo {


    @Autowired
    YcRenewALStatusLogDAO ycRenewALstatusLogDAO;

    /**
     * @return 返回每天需要导入核心的保单YCRENEWALSTATUSLOG
     */
    @Override
    public List<YcRenewALStatusLog> getRenewContDatas() {
        YcRenewALStatusLogExample ycRenewALstatusLogExample = new YcRenewALStatusLogExample();
        ycRenewALstatusLogExample.createCriteria().andPolstatusEqualTo(ENUM_RENEW_STATUS.WAIT_TO_IMPORT.getStatus()).andIsDeletedEqualTo("0");
        List<YcRenewALStatusLog> ycRenewALstatusLogList = ycRenewALstatusLogDAO.selectByExample(ycRenewALstatusLogExample);
        return ycRenewALstatusLogList;
    }
}
