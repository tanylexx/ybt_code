package com.sinosoft.surrender.common.mq.bo.impl;

import com.guohualife.common.util.mq.dto.MQMessageDTO;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_YES_NO_STATUS;
import com.sinosoft.surrender.common.mq.bo.RenewalUWResultSaveBO;
import com.sinosoft.surrender.common.mq.dao.RenewalUWResultSaveDAO;
import com.sinosoft.surrender.db.dao.YcRenewALStatusLogDAO;
import com.sinosoft.surrender.db.model.LcRenewalStatusLog;
import com.sinosoft.surrender.db.model.YcRenewALStatusLog;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class RenewalUWResultSaveBOImpl implements RenewalUWResultSaveBO {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private YcRenewALStatusLogDAO ycRenewalStatusLogDAO;
    @Autowired
    private RenewalUWResultSaveDAO renewalUWResultSaveDAO;

    @Override
    public void saveRenewalUWResult(Object object, MQMessageDTO mqMessageDTO) {
        long startTime = System.currentTimeMillis();
        try{
            LcRenewalStatusLog lcRenewalStatusLog = (LcRenewalStatusLog)object;
            YcRenewALStatusLog ycRenewalStatusLog = copyLToYcRenewalStatusLog(lcRenewalStatusLog);
            //先查询此条消息是否保存过，根据保单号核保时间
            YcRenewALStatusLog ycRenewalStatusLogold = renewalUWResultSaveDAO.queryUWResultByContnoAndUWDate(ycRenewalStatusLog.getOldcontno(), ycRenewalStatusLog.getUwDate(), ycRenewalStatusLog.getUwTime());
            //如果查到了，就不保存了
            if(ycRenewalStatusLogold != null){
                logger.info("此条数据已落表，不继续存表");
                return;
            }else{
                //如果没查到，用保单号查之前的核保记录，如果有，则将之前的记录置为无效状态
                List<YcRenewALStatusLog> oldRenewalUWResults = renewalUWResultSaveDAO.queryUWResultByContno(ycRenewalStatusLog.getOldcontno());
                if(CollectionUtils.isNotEmpty(oldRenewalUWResults)){
                    for(YcRenewALStatusLog ycRenewalStatusLog1 : oldRenewalUWResults){
                        ycRenewalStatusLog1.setModifiedDate(new Date());
                        ycRenewalStatusLog1.setIsDeleted(ENUM_YES_NO_STATUS.YES.getValue());
                        ycRenewalStatusLogDAO.updateByPrimaryKey(ycRenewalStatusLog1);
                    }
                }
            }
            logger.info("续保核保结论开始保存到云上，保单号为{}", ycRenewalStatusLog.getOldcontno());
            ycRenewalStatusLogDAO.insert(ycRenewalStatusLog);
        }catch (Exception e){
            e.printStackTrace();
            logger.error("核保结论存表发生异常！");
        }finally {
            logger.info("保存核保结论耗时{}ms", (System.currentTimeMillis()-startTime));
        }
    }

    /**
     * l的数据复制到y
     * @param lcRenewalStatusLog
     * @return
     */
    private YcRenewALStatusLog copyLToYcRenewalStatusLog(LcRenewalStatusLog lcRenewalStatusLog) {
        YcRenewALStatusLog ycRenewalStatusLog = new YcRenewALStatusLog();
        BeanUtils.copyProperties(lcRenewalStatusLog, ycRenewalStatusLog);
        if("9".equals(lcRenewalStatusLog.getUwflag())){
            //核保成功
            ycRenewalStatusLog.setPolstatus("1");
        }else {
            //核保失败
            ycRenewalStatusLog.setPolstatus("0");
        }
        ycRenewalStatusLog.setOldcontno(lcRenewalStatusLog.getContno());
        ycRenewalStatusLog.setIsDeleted(ENUM_YES_NO_STATUS.NO.getValue());
        ycRenewalStatusLog.setCreatedDate(new Date());
        ycRenewalStatusLog.setModifiedDate(new Date());
        ycRenewalStatusLog.setCreatedUser("YBT");
        ycRenewalStatusLog.setCreatedUser("YBT");
        return ycRenewalStatusLog;
    }
}
