package com.sinosoft.surrender.addprem.bo.impl;

import com.sinosoft.surrender.addprem.bo.SaveAddPremData;
import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_LIS_EDORNO_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_YES_NO_STATUS;
import com.sinosoft.surrender.common.notype.bo.GetEdorNoBO;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.LcContDAO;
import com.sinosoft.surrender.db.dao.YcAddPremDAO;
import com.sinosoft.surrender.db.dao.YcSendMessageDAO;
import com.sinosoft.surrender.db.model.LcCont;
import com.sinosoft.surrender.db.model.YcAddPrem;
import com.sinosoft.surrender.db.model.YcSendMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SaveAddPremDataImpl implements SaveAddPremData {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private YcAddPremDAO ycAddPremDAO;
    @Autowired
    private YcSendMessageDAO ycSendMessageDAO;
    @Autowired
    private LcContDAO lcContDAO;
    @Autowired
    private GetEdorNoBO getEdorNoBO;

    @Override
    public void saveAddPremData(PEdorAddPremReqDto pEdorAddPremReqDto) {
        // 封装数据
        YcAddPrem ycAddPrem = fillYcAddPrem(pEdorAddPremReqDto);
        // 保存退表中间表
        logger.debug("保存追加保费导核心中间表");
        ycAddPremDAO.insert(ycAddPrem);
        /*YcSendMessage ycSendMessage = fillYcSendMessage(pEdorAddPremReqDto);
        logger.debug("保存短信中间表");
        ycSendMessageDAO.insert(ycSendMessage);*/
    }

    private YcSendMessage fillYcSendMessage(PEdorAddPremReqDto pEdorAddPremReqDto) {
        YcSendMessage ycSendMessage = new YcSendMessage();
        return ycSendMessage;
    }

    private YcAddPrem fillYcAddPrem(PEdorAddPremReqDto pEdorAddPremReqDto) {
        //生成保全受理号
        generateEdorAcceptNo(pEdorAddPremReqDto);
        YcAddPrem ycAddPrem = new YcAddPrem();
        ycAddPrem.setAccname(pEdorAddPremReqDto.getAccName());
        ycAddPrem.setAmnt(pEdorAddPremReqDto.getAmnt());
        ycAddPrem.setApplydate(pEdorAddPremReqDto.getApplyDate());
        ycAddPrem.setApptype(pEdorAddPremReqDto.getAppType());
        ycAddPrem.setBankaccno(pEdorAddPremReqDto.getBankAccNo());
        ycAddPrem.setBankcode(pEdorAddPremReqDto.getBankCode());
        ycAddPrem.setContno(pEdorAddPremReqDto.getContNo());
        ycAddPrem.setCreatedDate(new Date());
        ycAddPrem.setCreatedUser(pEdorAddPremReqDto.getApplyPlatform());
        ycAddPrem.setEdoracceptno(pEdorAddPremReqDto.getEdorAcceptNo());
        ycAddPrem.setEdortype(pEdorAddPremReqDto.getEdorType());
        ycAddPrem.setInsuyear(pEdorAddPremReqDto.getInsuYear());
        ycAddPrem.setInsuyearflag(pEdorAddPremReqDto.getInsuYearFlag());
        ycAddPrem.setIsDeleted(ENUM_YES_NO_STATUS.NO.getValue());
        ycAddPrem.setModifiedDate(new Date());
        ycAddPrem.setModifiedUser(pEdorAddPremReqDto.getApplyPlatform());
        ycAddPrem.setPrem(pEdorAddPremReqDto.getPrem());
        ycAddPrem.setCvalidate(pEdorAddPremReqDto.getcValiDate());
        ycAddPrem.setProcessStatus(ENUM_TRACE_STATUS.INIT.getValue());
        ycAddPrem.setTransrno(pEdorAddPremReqDto.getTransNo());
        return ycAddPrem;
    }

    /**
     * 生成保全受理号
     * @param pEdorAddPremReqDto
     */
    private void generateEdorAcceptNo(PEdorAddPremReqDto pEdorAddPremReqDto) {
        // 保全受理号
        LcCont lcCont = lcContDAO.selectByPrimaryKey(pEdorAddPremReqDto.getContNo());
        // 非空校验
        ExceptionUtil.checkNull(lcCont, "查询合同的数据失败!");
        String limitNo = lcCont.getManagecom();// 管理机构
        String edorNo = ENUM_LIS_EDORNO_TYPE.EDORACCEPTNO.getValue();// 保全受理号-90487
        String edorAcceptNo = getEdorNoBO.getEdorNo(limitNo, edorNo);
        pEdorAddPremReqDto.setEdorAcceptNo(edorAcceptNo);
    }
}
