package com.sinosoft.surrender.addprem.bo.impl;

import com.sinosoft.surrender.addprem.bo.AddPremAmntCalBO;
import com.sinosoft.surrender.addprem.bo.AddPremCheckBO;
import com.sinosoft.surrender.addprem.bo.GenerateAlgorithParamBO;
import com.sinosoft.surrender.addprem.dao.AddPremDao;
import com.sinosoft.surrender.addprem.dto.req.PEdorAddPremReqDto;
import com.sinosoft.surrender.cashvalue.bo.CalculatorBO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.cashvalue.dto.CalculatorDTO;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.db.model.LcPol;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class AddPremAmntCalBOImpl implements AddPremAmntCalBO {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private LcPolSpecDAO lcPolSpecDAO;
    @Autowired
    private GenerateAlgorithParamBO generateAlgorithParamBO;
    @Autowired
    private CalculatorBO calculatorBO;
    @Autowired
    private AddPremDao addPremDao;
    @Autowired
    private AddPremCheckBO addPremCheckBO;

    @Override
    public void calAmntAndUWRulesCheck(PEdorAddPremReqDto pEdorAddPremReqDto) throws IllegalAccessException {
        String contno = pEdorAddPremReqDto.getContNo();
        List<LcPol> lcPols = lcPolSpecDAO.getLcPolByContNo(contno);
        if(CollectionUtils.isEmpty(lcPols)){
            throw new RulesCheckedException("未查询到险种信息！");
        }
        BigDecimal resultMoney = new BigDecimal(0);
        for(LcPol lcPol : lcPols){
            // 组装计算参数
            AlgorithElementDTO algorithElementDTO = generateAlgorithParamBO.generateAlogrithParam(pEdorAddPremReqDto, lcPol);
            // 获取计算编码
            String calCode = addPremDao.getCalCode(contno, lcPol.getPolno());
            logger.info("=====调用核心配置表算法开始====");
            CalculatorDTO calculatorDTO = new CalculatorDTO();
            calculatorDTO.setCalcode(calCode);
            calculatorDTO.setParamsObj(algorithElementDTO);
            // 调用计算接口
            String result = calculatorBO.calculate(calculatorDTO);
            resultMoney = resultMoney.add(BigDecimal.valueOf(Double.valueOf(result)));
            // 核保规则
            logger.info("=====险种核保规则开始====");
            addPremCheckBO.checkUWRules(pEdorAddPremReqDto, algorithElementDTO, lcPol);
        }
        // 得到的保额与传入的保额进行对比
        if(pEdorAddPremReqDto.getAmnt().compareTo(resultMoney)!= 0){
            throw new RulesCheckedException("核保错误：本次加保传入的保额"+pEdorAddPremReqDto.getAmnt()+"与计算得到的保额"+resultMoney+"不符！");
        }
    }
}
