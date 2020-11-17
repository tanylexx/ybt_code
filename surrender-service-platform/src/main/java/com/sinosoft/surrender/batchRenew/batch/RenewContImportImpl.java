package com.sinosoft.surrender.batchRenew.batch;

import com.sinosoft.common.util.quartz.batch.MultThreadBatch;

import com.sinosoft.surrender.batchRenew.service.RenewContImportService;
import com.sinosoft.surrender.db.model.YcRenewALStatusLog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component("renewContImportImpl")
public class RenewContImportImpl implements MultThreadBatch {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private RenewContImportService renewContImportService;

    @Override
    public void executeInit() throws Exception {
        logger.debug("==>续保导核心批处理执行开始<==");
    }

    @Override
    public List executeData() throws Exception {
        logger.debug("==>续保导核心批处理获取数据开始<==");
        return renewContImportService.getRenewContDatas();
    }

    @Override
    public void execute(Object obj) throws Exception {
        logger.debug("==>续保导核心批处理保单信息开始<=="+obj);
        YcRenewALStatusLog ycRenewALstatusLog = (YcRenewALStatusLog) obj;
        if (ycRenewALstatusLog!=null){
            renewContImportService.contImport(ycRenewALstatusLog);
        }
    }

    @Override
    public void executeFinal() throws Exception {
        logger.debug("==>续保导核心批处理执行结束<==");

    }
}
