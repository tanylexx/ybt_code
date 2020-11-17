package com.sinosoft.surrender.cashvalue.bo.abs;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalSurrenderBO;
import com.sinosoft.surrender.cashvalue.bo.CalPolicyZTDateParamBO;
import com.sinosoft.surrender.cashvalue.bo.FillCalCashValueParamBO;
import com.sinosoft.surrender.cashvalue.dao.LlClaimDetailSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LmEdorZT1SpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LmPolDutyEdorCalSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.cashvalue.dto.CalCashValueParamDTO;
import com.sinosoft.surrender.cashvalue.dto.PolicyZTDateParamDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.model.LmEdorZT1;
import com.sinosoft.surrender.db.model.LmPolDutyEdorCal;

@Component
public abstract class AbsFillCalCashValueParamBO implements FillCalCashValueParamBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CalSurrenderBO calGetSurrenderBO;
	@Autowired
	private CalPolicyZTDateParamBO calPolicyZTDateParamBO;
	@Autowired
	private LmPolDutyEdorCalSpecDAO LmPolDutyEdorCalSpecDAO;
	@Autowired
	private LlClaimDetailSpecDAO llClaimDetailSpecDAO;
	@Autowired
	private LmEdorZT1SpecDAO lmEdorZT1SpecDAO;

	@Override
	public CalCashValueParamDTO fillCalCashValueParam(SurrenderDTO surrenderDTO) {
		long currentTime = System.currentTimeMillis();
		CalCashValueParamDTO calCashValueParamDTO = new CalCashValueParamDTO();
		// 1.退保计算现价相关日期
		fillPolicyZTDate(calCashValueParamDTO, surrenderDTO);
		// 2.计算保单年度末生存给付
		fillPolicyYearEndAlivePay(calCashValueParamDTO, surrenderDTO);
		// 3.计算当前保单年度末现价
		fillCurPolicyYearEndCashValue(calCashValueParamDTO, surrenderDTO);
		// 4.填充基本保额
		BigDecimal baseAmnt = surrenderDTO.getLcPol().getAmnt();
		ExceptionUtil.checkZero(baseAmnt, "基本保额为空或者为零，请核实！");
		calCashValueParamDTO.setBaseAmnt(baseAmnt);
		// 5.填充有效保额
		fillValidAmnt(calCashValueParamDTO, surrenderDTO);
		// 6.计算现金价值采纳的评估净保费
		fillAssessmentOfNetPremiums(calCashValueParamDTO, surrenderDTO);
		// 7.计算上一个保单年度末现价
		fillLastPolicyYearEndCashValue(calCashValueParamDTO, surrenderDTO);
		logger.info("填充计算要素参数，总耗时为：{}毫秒", System.currentTimeMillis() - currentTime);
		return calCashValueParamDTO;
	}

	/**
	 * 
	 * 填充退保计算现价相关日期
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 */
	private void fillPolicyZTDate(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO surrenderDTO) {
		PolicyZTDateParamDTO policyZTDateParamDTO = calPolicyZTDateParamBO.calPolicyZTDateParam(surrenderDTO);
		surrenderDTO.setzTPoint(policyZTDateParamDTO.getzTPoint());
		surrenderDTO.setZtYear(new BigDecimal(policyZTDateParamDTO.getzTYear()));
		calCashValueParamDTO.setzTDays(new BigDecimal(policyZTDateParamDTO.getzTDays()));
		calCashValueParamDTO.setzTYearDays(new BigDecimal(policyZTDateParamDTO.getzTYearDays()));
		calCashValueParamDTO.setCurrentDuringDays(new BigDecimal(policyZTDateParamDTO.getCurrentDuringDays()));
		calCashValueParamDTO.setPaymentDays(new BigDecimal(policyZTDateParamDTO.getPaymentDays()));
	}

	/**
	 * 
	 * 填充计算当前保单年度末现价
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 */
	private void fillCurPolicyYearEndCashValue(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO surrenderDTO) {
		// 根据退保年度,计算当前保单年度末现价和上一个保单年度现价
		// 1.准备计算要素
		List<LmEdorZT1> lmEdorZT1List = lmEdorZT1SpecDAO.getLmEdorZT1Infos(surrenderDTO.getLcPol().getRiskcode(),
				surrenderDTO.getLcDuty().getDutycode());
		ExceptionUtil.checkCollEmpty(lmEdorZT1List, "获取生存退保计算描述表1数据失败！");
		// surrenderDTO.setLmEdorZT1(lmEdorZT1List.get(0));
		// 判断LMEdorZT1,是否有退保现金价值险种描述编码
		ExceptionUtil.checkEmpty(lmEdorZT1List.get(0).getCashvaluecode(), "缺少退保现金价值险种描述编码!");
		String calCode = lmEdorZT1List.get(0).getCashvaluecode();
		surrenderDTO.setCalCode(calCode);
		AlgorithElementDTO algorithElementDTO = fillAlgorithmElementDTO(surrenderDTO);
		// 退保年度
		BigDecimal curPolicyYearEndCashValue = calGetSurrenderBO.calGetSurrender(calCode, algorithElementDTO);
		calCashValueParamDTO.setCurPolicyYearEndCashValue(curPolicyYearEndCashValue);
		logger.info("本保单年度末现价为：{}",curPolicyYearEndCashValue);
	}

	/**
	 * 
	 * 填充计算上一个保单年度末现价
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 */
	private void fillLastPolicyYearEndCashValue(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO surrenderDTO) {
		// 上一年的退保年度
		BigDecimal lastPolicyYearEndCashValue = BigDecimal.ZERO;
		if (BigDecimal.ZERO.compareTo(surrenderDTO.getZtYear()) != 0) {
			BigDecimal ztYear = surrenderDTO.getZtYear().subtract(BigDecimal.ONE);
			surrenderDTO.setZtYear(ztYear);
			String calCode = surrenderDTO.getCalCode();
			AlgorithElementDTO algorithElementDTO = fillAlgorithmElementDTO(surrenderDTO);
			lastPolicyYearEndCashValue = calGetSurrenderBO.calGetSurrender(calCode, algorithElementDTO);
		}
		calCashValueParamDTO.setLastPolicyYearEndCashValue(lastPolicyYearEndCashValue);
		logger.info("上一个保单年度末现价为：{}",lastPolicyYearEndCashValue);
	}

	/**
	 * 
	 * 填充计算现金价值采纳的评估净保费
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 */
	private void fillAssessmentOfNetPremiums(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO surrenderDTO) {
		// 1.根据riskcode，dutycode，EdorType为CT和FeeType为PREMCV查询
		List<LmPolDutyEdorCal> lmPolDutyEdorCalList = LmPolDutyEdorCalSpecDAO.getLmPolDutyEdorCalInfos(surrenderDTO
				.getLcPol().getRiskcode(), surrenderDTO.getLcDuty().getDutycode());
		ExceptionUtil.checkCollEmpty(lmPolDutyEdorCalList, "该险种未定义计算现金价值采纳的评估净保费算法!");
		ExceptionUtil.checkEmpty(lmPolDutyEdorCalList.get(0).getChgpremcalcode(), "缺少评估净保费险种描述编码");
		logger.info("计算现金价值采纳的评估净保费");
		AlgorithElementDTO algorithElementDTO = fillAlgorithmElementDTO(surrenderDTO);
		String calCode = lmPolDutyEdorCalList.get(0).getChgpremcalcode();
		BigDecimal prem_cv = calGetSurrenderBO.calGetSurrender(calCode, algorithElementDTO);
		calCashValueParamDTO.setPrem_cv(prem_cv);
		logger.info("现金价值采纳的评估净保费为：{}",prem_cv);
	}

	/**
	 * 
	 * 填充有效保额 (退保时剩余保额)
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 */
	private void fillValidAmnt(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO surrenderDTO) {
		// 1.查询已查询已赔付金额的金额;,若有些险种不扣除已赔付金额,可改动计算时刻现价公式
		//logger.info("");
		BigDecimal realPay = BigDecimal.ZERO;
		// 2.有效保额=基本保额-已赔付金额
		String realPayStr = llClaimDetailSpecDAO.getSumRealPay(surrenderDTO.getLcDuty());
		if (StringUtils.isNotBlank(realPayStr)) {
			realPay = new BigDecimal(realPayStr);
		}
		BigDecimal validAmnt = calCashValueParamDTO.getBaseAmnt().subtract(realPay);
		calCashValueParamDTO.setValidAmnt(validAmnt);
		logger.info("有效保额为：{}",validAmnt);
	}

	/**
	 * 
	 * 填充保单年度末生存给付
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 */
	protected abstract void fillPolicyYearEndAlivePay(CalCashValueParamDTO calCashValueParamDTO, SurrenderDTO endorseDTO);

	/**
	 * 
	 * 填充计算要素
	 * 
	 * @history: 2018-3-29
	 * @author: wangwl_sinosoft
	 * @param surrenderDTO
	 * @return
	 */
	protected abstract AlgorithElementDTO fillAlgorithmElementDTO(SurrenderDTO surrenderDTO);
}
