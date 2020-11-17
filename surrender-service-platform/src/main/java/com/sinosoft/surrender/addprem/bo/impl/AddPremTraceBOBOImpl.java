package com.sinosoft.surrender.addprem.bo.impl;

import com.sinosoft.surrender.addprem.bo.AddPremTraceBO;
import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.addprem.dto.res.PEdorAddPremResDto;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_YES_NO_STATUS;
import com.sinosoft.surrender.db.dao.YkSurrenderTraceDAO;
import com.sinosoft.surrender.db.model.YkSurrenderTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

@Component
public class AddPremTraceBOBOImpl implements AddPremTraceBO {
    @Autowired
    private YkSurrenderTraceDAO ykSurrenderTraceDAO;

    @Override
    public BigDecimal saveAddPremTrace(PEdorAddPremReqDto pEdorAddPremReqDto) {
        //封装日志记录Model的部分属性
        YkSurrenderTrace ykSurrenderTrace = fillykSurrenderTrace(pEdorAddPremReqDto);
        return ykSurrenderTraceDAO.insertSelective(ykSurrenderTrace);
    }

    /**
     * 组装轨迹表参数
     * @param pEdorAddPremReqDto
     * @return
     */
    private YkSurrenderTrace fillykSurrenderTrace(PEdorAddPremReqDto pEdorAddPremReqDto) {
        YkSurrenderTrace ykSurrenderTrace = new YkSurrenderTrace();
        ykSurrenderTrace.setBankcode(pEdorAddPremReqDto.getBankCode());
        ykSurrenderTrace.setTransdate(pEdorAddPremReqDto.getApplyDate());
        ykSurrenderTrace.setTransno(pEdorAddPremReqDto.getTransNo());
        ykSurrenderTrace.setBrno(pEdorAddPremReqDto.getBrNo());
        ykSurrenderTrace.setZoneno(pEdorAddPremReqDto.getZoneNo());
        ykSurrenderTrace.setSourcetype(pEdorAddPremReqDto.getAppType()); //渠道
        ykSurrenderTrace.setContno(pEdorAddPremReqDto.getContNo());
        ykSurrenderTrace.setFunctionflag(pEdorAddPremReqDto.getEdorType()); //交易代码
        ykSurrenderTrace.setCreatedDate(new Date());
        ykSurrenderTrace.setModifiedDate(new Date());
        ykSurrenderTrace.setTransStatus("00");
        ykSurrenderTrace.setCreatedUser(pEdorAddPremReqDto.getApplyPlatform());
        ykSurrenderTrace.setModifiedUser(pEdorAddPremReqDto.getApplyPlatform());
        ykSurrenderTrace.setIsDeleted(ENUM_YES_NO_STATUS.NO.getValue());
        return ykSurrenderTrace;
    }

    @Override
    public void updateAddPremTrace(PEdorAddPremResDto pEdorAddPremResDto, BigDecimal addPremTraceId, long confirmStartTime) {
        YkSurrenderTrace ykSurrenderTrace = new YkSurrenderTrace();
        ykSurrenderTrace.setSid(addPremTraceId);
        ykSurrenderTrace.setTransStatus(pEdorAddPremResDto.getResultFlag());
        ykSurrenderTrace.setDesr(pEdorAddPremResDto.getDescr());
        long confirmEndTime = System.currentTimeMillis();
        ykSurrenderTrace.setRocessCost(BigDecimal.valueOf(confirmEndTime-confirmStartTime));
        ykSurrenderTraceDAO.updateByPrimaryKeySelective(ykSurrenderTrace);
    }

}
