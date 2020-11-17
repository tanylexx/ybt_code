package com.sinosoft.surrender.addprem.service.impl;

import com.sinosoft.lis.specedor.dto.res.LisSpecEdorResDTO;
import com.sinosoft.surrender.addprem.bo.AddPremContImportCoreBO;
import com.sinosoft.surrender.addprem.bo.QueryAddPremContDataBO;
import com.sinosoft.surrender.addprem.dto.AddPremImportDto;
import com.sinosoft.surrender.addprem.service.AddPremImportCoreService;
import com.sinosoft.surrender.common.constant.ENUM_PROCESS_STATUS_CODE;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AddPremImportCoreServiceImpl implements AddPremImportCoreService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private QueryAddPremContDataBO queryAddPremContDataBO;
    @Autowired
    private AddPremContImportCoreBO addPremContImportCoreBO;
    @Autowired
    private LockBO lockBO;

    @Override
    public List<AddPremImportDto> getToBeImportedCont() {
        return queryAddPremContDataBO.queryAddPremContData();

    }

    @Override
    public void importCore(AddPremImportDto addPremImportDto) {
        long importStartTime = System.currentTimeMillis();
        //加锁
        String lockType = Constant.ADD_PREM_IMPORT_LOCKTYPE;
        String loker = Constant.ADD_PREM_IMPORT_LOCKER;
        StringBuffer sbf = new StringBuffer();
        sbf.append(addPremImportDto.getContNo());
        String key = sbf.toString();
        logger.debug("====>加锁中...<====");
        try {
            lockBO.lock(lockType, key, loker);
        } catch (LockFailedException e1) {
            logger.error("加锁失败：{}", e1);
            return;
        }
        Map<String, Object> modifyField = new HashMap<String, Object>();
        try {
            // 封装部分属性
            modifyField.put("transNo", addPremImportDto.getTransNo());
            modifyField.put("contNo", addPremImportDto.getContNo());
            //dto转xml或者是转调用webservice入参
            LisSpecEdorResDTO lisSpecEdorResDTO = addPremContImportCoreBO.callWebService(addPremImportDto);
            //返回结果处理
            String rescode = lisSpecEdorResDTO.getResultCode();
            String resdesc = lisSpecEdorResDTO.getResultMessage();
            // 判断返回的报文是否是错误结果
            if (StringUtils.isNotBlank(rescode) && !Constant.ONE.equals(rescode)) {
                // 导核心失败
                logger.info("====>加保导核心失败...<====");
                modifyField.put("processStatus", ENUM_PROCESS_STATUS_CODE.FAIL.getValue());
                if (resdesc.length() >= 500) {
                    resdesc = resdesc.substring(0, 500);
                }
                modifyField.put("processResult", resdesc);
            } else {
                // 导入成功
                logger.info("====>加保导核心成功...<====");
                modifyField.put("processStatus", ENUM_PROCESS_STATUS_CODE.SUCESS.getValue());
                modifyField.put("processResult", ENUM_PROCESS_STATUS_CODE.SUCESS.getName());
            }
        }catch (TradeException e){
            logger.error("导入失败：{}",e);
            modifyField.put("processStatus", ENUM_TRACE_STATUS.FAIL.getValue());
            String message = e.getMessage();
            if (message != null && message.length() >= 500) {
                message = message.substring(0, 500);
            }
            modifyField.put("processResult", message);
        }catch (Exception e){
            logger.error("导入失败,发生非预期异常：{}",e);
            modifyField.put("processStatus", ENUM_TRACE_STATUS.FAIL.getValue());
            String message = e.getMessage();
            if (message != null && message.length() >= 500) {
                message = message.substring(0, 500);
            }
            modifyField.put("processResult", message);
        }finally{
            //解锁
            try {
                long importEndTime = System.currentTimeMillis();
                logger.debug("====>修改处理状态...<====");
                modifyField.put("processCost", BigDecimal.valueOf(importEndTime - importStartTime));
                addPremContImportCoreBO.modifyImportCoreProcessStatus(modifyField);
                // 解锁
                logger.info("====>解锁...<====");
                try{
                    lockBO.unLock(lockType, key);
                }catch(LockFailedException e1){
                    logger.error("解锁失败：{}", e1);
                    return;
                }
                logger.info("此保单号为：{}，导核心耗时：{}毫秒", addPremImportDto.getContNo(),(importEndTime - importStartTime));
            } catch (Exception e) {
                logger.error("导入失败,发生非预期异常：{}",e);
            }
        }
    }

}
