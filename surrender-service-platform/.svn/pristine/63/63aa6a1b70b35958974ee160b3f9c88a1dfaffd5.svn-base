package com.sinosoft.surrender.common.mq.dao.impl;

import com.sinosoft.surrender.common.mq.dao.RenewalUWResultSaveDAO;
import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.dao.YcRenewALStatusLogDAO;
import com.sinosoft.surrender.db.model.YcRenewALStatusLog;
import com.sinosoft.surrender.db.model.YcRenewALStatusLogExample;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public class RenewalUWResultSaveDAOImpl extends SurrenderBaseDAOImpl implements RenewalUWResultSaveDAO {

    @Autowired
    YcRenewALStatusLogDAO ycRenewalStatusLogDAO;

    @Override
    public YcRenewALStatusLog queryUWResultByContnoAndUWDate(String contno, Date uwdate, String uwtime) {
        YcRenewALStatusLogExample ycRenewalStatusLogExample =new YcRenewALStatusLogExample();
        ycRenewalStatusLogExample.createCriteria().andOldcontnoEqualTo(contno).andUwDateEqualTo(uwdate).andUwTimeEqualTo(uwtime);
        List<YcRenewALStatusLog> ycRenewalStatusLogList=ycRenewalStatusLogDAO.selectByExample(ycRenewalStatusLogExample);
        if(CollectionUtils.isNotEmpty(ycRenewalStatusLogList)){
            return ycRenewalStatusLogList.get(0);
        }
        return null;
    }

    @Override
    public List<YcRenewALStatusLog> queryUWResultByContno(String contno) {
        YcRenewALStatusLogExample ycRenewalStatusLogExample =new YcRenewALStatusLogExample();
        ycRenewalStatusLogExample.createCriteria().andOldcontnoEqualTo(contno).andIsDeletedEqualTo("0");
        return ycRenewalStatusLogDAO.selectByExample(ycRenewalStatusLogExample);
    }
}
