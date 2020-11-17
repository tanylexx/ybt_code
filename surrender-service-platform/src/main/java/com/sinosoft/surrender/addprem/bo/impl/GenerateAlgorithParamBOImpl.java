package com.sinosoft.surrender.addprem.bo.impl;

import com.guohualife.platform.common.api.util.DateUtil;
import com.sinosoft.surrender.addprem.bo.GenerateAlgorithParamBO;
import com.sinosoft.surrender.addprem.dao.AddPremDao;
import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.common.config.bo.ConfigInfoBO;
import com.sinosoft.surrender.db.model.LcPol;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.*;

/**
 *
 */
@Component
public class GenerateAlgorithParamBOImpl implements GenerateAlgorithParamBO {
    @Override
    public AlgorithElementDTO generateAlogrithParam(PEdorAddPremReqDto pEdorAddPremReqDto, LcPol lcPol) {
        AlgorithElementDTO algorithElementDTO = new AlgorithElementDTO();
        algorithElementDTO.setPrem(pEdorAddPremReqDto.getPrem());
        Date insuerdBirthday = lcPol.getInsuredbirthday();
        // 性别
        String insuredSex = lcPol.getInsuredsex();
        algorithElementDTO.setSex(insuredSex);
        // 交费方式
        BigDecimal payIntv = lcPol.getPayintv();
        algorithElementDTO.setPayIntv(payIntv);
        // 交费年期
        BigDecimal payEndYear = lcPol.getPayendyear();
        algorithElementDTO.setPayEndYear(payEndYear);
        // 计算加保的真实年龄并填充
        BigDecimal appAge = calAppAge(insuerdBirthday);
        algorithElementDTO.setAppAge(appAge);
        // 计算加保的剩余保险期间并填充
        BigDecimal remainInsuyear = calRemainInsuyear(pEdorAddPremReqDto, lcPol);
        algorithElementDTO.setInsuYear(remainInsuyear);
        pEdorAddPremReqDto.setInsuYear(remainInsuyear);
        // 保单号
        algorithElementDTO.setContNo(lcPol.getContno());
        // 险种号
        algorithElementDTO.setPolNo(lcPol.getPolno());
        // 被保人客户号
        algorithElementDTO.setInsuredNo(lcPol.getInsuredno());
        // 交易小渠道
        algorithElementDTO.setSaleChnlDetail(lcPol.getSalechnldetail());
        // 银行编号
        algorithElementDTO.setBankCode(pEdorAddPremReqDto.getBankCode());
        // 管理机构
        algorithElementDTO.setManageCom(lcPol.getManagecom());
        return algorithElementDTO;
    }



    /**
     * 计算加保的剩余保险期间
     * @param pEdorAddPremReqDto
     */
    private BigDecimal calRemainInsuyear(PEdorAddPremReqDto pEdorAddPremReqDto, LcPol lcPol) {
        // 原始保险期间
        BigDecimal originInsuyear = lcPol.getInsuyear();
        // 原始保单生效日
        Date oriCvalidate =  lcPol.getCvalidate();
        // 加保生效日
        Date apCvalidate = pEdorAddPremReqDto.getcValiDate();
        // 计算剩余保险期间的公式为：加保的保险期间 = 原保单的保险期间-加保生效日所在原保单的保单年度+1
        /*保单年度为：保险合同生效日或保单周年日零时起至下一年度的保单周年日零时止为一个保单年度，
        举个例子来说，保险合同生效日为2019年12月20号，那么2019年12月20号至2020年12月20号就是一个保单年度*/
        return originInsuyear.subtract(BigDecimal.valueOf(DateUtil.getDateDistance(
                apCvalidate,oriCvalidate,DateUtil.ACCURACY_YEAR)));
    }

    /**
     * 计算加保的真实年龄
     * @param insuerdBirthday
     * @return
     */
    private BigDecimal calAppAge(Date insuerdBirthday) {
        return BigDecimal.valueOf(DateUtil.getDateDistance(
                insuerdBirthday,DateUtil.getCurrentDate(),DateUtil.ACCURACY_YEAR));
    }
}
