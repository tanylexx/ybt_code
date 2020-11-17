package com.sinosoft.surrender.common.mq.dao;

import com.sinosoft.surrender.db.model.YcRenewALStatusLog;

import java.util.Date;
import java.util.List;

/**
 * jinwc 2020-4-1
 * 续保核保结论相关dao
 */
public interface RenewalUWResultSaveDAO {
    /**
     * 查询是否有本次记录
     */
    YcRenewALStatusLog queryUWResultByContnoAndUWDate(String contno, Date uwdate, String uwtime);
    /**
     * 查询是否有历史记录
     */
    List<YcRenewALStatusLog> queryUWResultByContno(String contno);
}
