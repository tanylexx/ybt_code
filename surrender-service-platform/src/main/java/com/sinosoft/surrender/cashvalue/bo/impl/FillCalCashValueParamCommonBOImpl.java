package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalPolicyYearEndAlivePayBO;
import com.sinosoft.surrender.cashvalue.bo.abs.AbsFillCalCashValueParamBO;
import com.sinosoft.surrender.cashvalue.dao.LcInsuredSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.cashvalue.dto.CalCashValueParamDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.model.LcInsured;

/**
 * 
 * 计算类型为按保额计算
 *
 * @author:  wangwl_sinosoft
 * @date: 2018-4-11-下午1:54:04
 * @version:
 */
@Component("fillCalCashValueParamCommonBOImpl")
public class FillCalCashValueParamCommonBOImpl extends AbsFillCalCashValueParamBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Resource(name = "calPolicyYearEndAlivePayCommonBOImpl")
	private CalPolicyYearEndAlivePayBO calPolicyYearEndAlivePayBO;
	@Autowired
	private LcInsuredSpecDAO lcInsuredSpecDAO;
	@Override
	protected void fillPolicyYearEndAlivePay(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO surrenderDTO) {
		BigDecimal policyYearEndAlivePay = calPolicyYearEndAlivePayBO.calPolicyYearEndAlivePay(surrenderDTO);
		calCashValueParamDTO.setPolicyYearEndAlivePay(policyYearEndAlivePay);
		logger.info("年末生存金为：{}",policyYearEndAlivePay);
	}

	@Override
	protected AlgorithElementDTO fillAlgorithmElementDTO(SurrenderDTO surrenderDTO) {
		AlgorithElementDTO algorithElementDTO = new AlgorithElementDTO();
		List<LcInsured> lcInsuredList = lcInsuredSpecDAO.getLcinsuredByContNo(surrenderDTO.getContNo());
		ExceptionUtil.checkCollEmpty(lcInsuredList, "此保单无受益人信息");
		// 只支持一个被保人
		int appAge = DateUtil.calIntervalOfAbandon(lcInsuredList.get(0).getBirthday(), surrenderDTO.getLcPol()
				.getPolapplydate(), DateUtil.DATE_TYPE_Y);
		algorithElementDTO.setAppAge(new BigDecimal(appAge));
		algorithElementDTO.setSex(lcInsuredList.get(0).getSex());
		algorithElementDTO.setInterval(surrenderDTO.getZtYear());
		algorithElementDTO.setGet(surrenderDTO.getLcPol().getAmnt());
		algorithElementDTO.setPayIntv(surrenderDTO.getLcDuty().getPayintv());
		algorithElementDTO.setPayEndYear(surrenderDTO.getLcDuty().getPayendyear());
		return algorithElementDTO;
	}

}
