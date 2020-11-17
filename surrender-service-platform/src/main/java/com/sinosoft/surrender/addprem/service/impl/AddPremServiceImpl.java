package com.sinosoft.surrender.addprem.service.impl;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.addprem.bo.AddPremAmntCalBO;
import com.sinosoft.surrender.addprem.bo.AddPremCheckBO;
import com.sinosoft.surrender.addprem.bo.AddPremTraceBO;
import com.sinosoft.surrender.addprem.bo.SaveAddPremData;
import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.addprem.dto.res.PEdorAddPremResDto;
import com.sinosoft.surrender.addprem.service.AddPremService;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class AddPremServiceImpl implements AddPremService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LockBO lockBO;
    @Autowired
    private AddPremCheckBO addPremCheckBO;
    @Autowired
    private AddPremTraceBO addPremTraceBO;
    @Autowired
    private SaveAddPremData saveAddPremData;
    @Autowired
    private AddPremAmntCalBO addPremAmntCalBO;

    @Override
    public PEdorAddPremResDto doAddPrem(PEdorAddPremReqDto pEdorAddPremReqDto) {
        long confirmStartTime = System.currentTimeMillis();
        PEdorAddPremResDto pEdorAddPremResDto = new PEdorAddPremResDto();
        //加锁
        String lockType = Constant.ADD_PREM_LOCKTYPE;
        String loker = Constant.ADD_PREM_LOCKER;
        StringBuffer sbf = new StringBuffer();
        sbf.append(pEdorAddPremReqDto.getEdorType());
        sbf.append("|");
        sbf.append(pEdorAddPremReqDto.getContNo());
        String key = sbf.toString();
        // 加锁，
        logger.debug("====>加锁中...<====");
        try {
            lockBO.lock(lockType, key, loker);
        } catch (LockFailedException e1) {
            logger.error("加锁失败：{}", e1);
            pEdorAddPremResDto.setResultFlag(ENUM_TRACE_STATUS.FAIL.getValue());
            pEdorAddPremResDto.setDescr("保单正在加保处理中！");
            return pEdorAddPremResDto;
        }
        BigDecimal addPremTraceId = BigDecimal.ZERO;
        try{
            //保存交易日志记录
            addPremTraceId = addPremTraceBO.saveAddPremTrace(pEdorAddPremReqDto);
            //参数非空校验
            logger.info("====>参数非空校验：\n{}", XmlUtil.toXml(pEdorAddPremReqDto));
            addPremCheckBO.checkParam(pEdorAddPremReqDto);
            //幂等操作--追加保费没有重复确认的情况，不进行幂等操作

            //计算追加保费对应的保额和规则校验
            logger.info("====>计算保额和规则校验...<====");
            addPremAmntCalBO.calAmntAndUWRulesCheck(pEdorAddPremReqDto);

            //保存追加保费数据到中间表
            logger.info("====>保存到追加保费导核心中间表...<====");
            saveAddPremData.saveAddPremData(pEdorAddPremReqDto);

            //确认追加保费成功
            logger.info("====>加保成功...<====");
            pEdorAddPremResDto.setResultFlag(ENUM_TRACE_STATUS.SUCCESS.getValue());
            pEdorAddPremResDto.setDescr(ENUM_TRACE_STATUS.SUCCESS.getName());
        }catch(RulesCheckedException rce){
            // 非空校验出现异常，规则类校验
            logger.error("校验出现异常:{}", rce);
            pEdorAddPremResDto.setResultFlag(ENUM_TRACE_STATUS.FAIL.getValue());
            String resultMsg = rce.getMessage();
            if (resultMsg.length() > 500) {
                resultMsg = resultMsg.substring(0, 500);
            }
            pEdorAddPremResDto.setDescr(resultMsg);
        }catch(Exception e){
            // 出现异常，退保不成功
            logger.error("追加保费出现非预期异常:{}", e);
            pEdorAddPremResDto.setResultFlag(ENUM_TRACE_STATUS.FAIL.getValue());
            pEdorAddPremResDto.setDescr("追加保费出现非预期异常！");
        }finally{
            try {
                // 更新交易日志
                logger.info("====>更新交易日志...<====");
                addPremTraceBO.updateAddPremTrace(pEdorAddPremResDto, addPremTraceId, confirmStartTime);
            } catch (Exception e) {
                logger.error("追加保费出现非预期异常:{}", e);
                pEdorAddPremResDto.setResultFlag(ENUM_TRACE_STATUS.FAIL.getValue());
                pEdorAddPremResDto.setDescr("追加保费出现非预期异常！");
            }

            try {
                // 解锁
                logger.info("====>解锁...<====");
                lockBO.unLock(lockType, key);
            } catch (Exception e) {
                logger.error("追加保费出现非预期异常:{}", e);
                pEdorAddPremResDto.setResultFlag(ENUM_TRACE_STATUS.FAIL.getValue());
                pEdorAddPremResDto.setDescr("追加保费出现非预期异常！");
            }
        }
        logger.info("追加保费返回参数:\n{},\n耗时：{}毫秒", XmlUtil.toXml(pEdorAddPremResDto),
                System.currentTimeMillis() - confirmStartTime);
        return pEdorAddPremResDto;
    }


}
