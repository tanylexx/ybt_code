package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalPolicyZTDateParamBO;
import com.sinosoft.surrender.cashvalue.dto.PolicyZTDateParamDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.contant.DateConstant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_PAYINTV_TYPE;
import com.sinosoft.surrender.common.exception.CalPolicyDateParamException;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.dao.LcContDAO;
import com.sinosoft.surrender.db.model.LcCont;
import com.sinosoft.surrender.db.model.LcDuty;
import com.sinosoft.surrender.db.model.LcPol;

@Component
public class CalPolicyZTDateParamBOImpl implements CalPolicyZTDateParamBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean debug = logger.isDebugEnabled();
	@Autowired
	private LcContDAO lcContDAO;

	@Override
	public PolicyZTDateParamDTO calPolicyZTDateParam(SurrenderDTO surrenderDTO) {
		long currentTime = System.currentTimeMillis();
		PolicyZTDateParamDTO policyZTDateParamDTO = new PolicyZTDateParamDTO();
		//计算退保年度
		calZtYear(surrenderDTO, policyZTDateParamDTO);
		//计算本保单年度已经过天数
		calZtDays(surrenderDTO.getcValiDate(), policyZTDateParamDTO);
		//计算退保所在保单年度的天数
		calZtYearDays(policyZTDateParamDTO);
		//实际交至日和上次交至日间隔 以及退保在该交费期间经过的天数
		calPayIntervalAndCurrentDuringDays(policyZTDateParamDTO);
		logger.info("填充退保相关日期参数，总耗时为：{}毫秒", System.currentTimeMillis() - currentTime);
		return policyZTDateParamDTO;
	}

	/**
	 * 
	 * 计算退保的保单年度,
	 * ①退保点默认为保全申请日期, 
	 * ②根据保单生效日期和退保点计算退保年度，其中需要注意保单生效日和退保点为闰年且为2月29或2月28时的特殊处理
	 * ③若保全申请日期大于交至日期的话,退保點取交至日期，且需要重新计算退保年度
	 * 
	 * @history: 2018-3-19
	 * @author: wangwl_sinosoft
	 * @param surrenderDTO
	 * @param policyZTDateParamDTO
	 */
	private void calZtYear(SurrenderDTO surrenderDTO, PolicyZTDateParamDTO policyZTDateParamDTO) {
		LcPol lcPol = surrenderDTO.getLcPol();
		LcCont lccont = lcContDAO.selectByPrimaryKey(lcPol.getContno());
		ExceptionUtil.checkNull(lccont, "保单信息为空，请核实！");
		if (debug) {
			logger.debug("此保单号为:{},计算退保年度！", lcPol.getContno());
		}
		String polState = surrenderDTO.getLcPol().getPolstate();
		ExceptionUtil.checkNull(polState, "保单状态不能为空！");
		LcDuty lcDuty = surrenderDTO.getLcDuty();
		Date paytoSpecDate = lcDuty.getPaytodate();
		// 默认退保点为保全申请日
		policyZTDateParamDTO.setzTPoint(surrenderDTO.getEdorValiDate());
		int ztYear = DateUtil.calSpecZtYear(surrenderDTO.getcValiDate(), policyZTDateParamDTO.getzTPoint());
		// 舍弃法，比较保全生效日与交至日期的大小，如发生自垫，则比较保全生效日期与自垫交至日期的大小
		int interval = DateUtil.calIntervalOfAbandon(paytoSpecDate, surrenderDTO.getEdorValiDate(),
				DateUtil.DATE_TYPE_D);
		Date cValiDate = surrenderDTO.getcValiDate();
		BigDecimal payIntv = lcDuty.getPayintv();
		ExceptionUtil.checkNull(payIntv, "交费频率为空,请核实");
		policyZTDateParamDTO.setPayIntv(payIntv.intValue());
		// 两年内保全生效日大于、等于交至日期退保,若发生自垫，退保点为自垫交至日期
		if (interval >= 0 && !checkPayIntv(lcDuty)) {
			if (debug) {
				logger.debug("两年内保全生效日大于、等于交至日期退保,若发生自垫，退保点为自垫交至日期,重新计算退保年度！");
			}
			policyZTDateParamDTO.setzTPoint(paytoSpecDate);
			// 重新算退保年度
			ztYear = DateUtil.calSpecZtYear(cValiDate, paytoSpecDate);
		}
		surrenderDTO.setPayToDate(paytoSpecDate);
		policyZTDateParamDTO.setPayToDate(paytoSpecDate);
		policyZTDateParamDTO.setzTYear(ztYear);
		logger.info("此保单号：{},退保时间为：{}，保单年度为：{}", lcPol.getContno(),
				DateUtil.getDateStr(policyZTDateParamDTO.getzTPoint(), DateConstant.DATE_FORMAT_7),
				policyZTDateParamDTO.getzTYear());
	}

	/**
	 * 
	 * 计算本保单年度已经过天数, 
	 * ①上一个保单周年日默认为保单生效日, 
	 * ②若退保年度大于0,根据退保年度重新计算上一个保单周年日.
	 * ③最后根据退保点和上一个保单周年日计算本保单年度已经过天数。
	 * 
	 * @history: 2018-3-19
	 * @author: wangwl_sinosoft
	 * @param cValiDate
	 * @param policyZTDateParamDTO
	 */
	private void calZtDays(Date cValiDate, PolicyZTDateParamDTO policyZTDateParamDTO) {
		if (debug) {
			logger.debug("计算上一个保单周年日，退保年度为:{}", policyZTDateParamDTO.getzTYear());
		}
		int zTYear = policyZTDateParamDTO.getzTYear();
		Date lastYear = cValiDate;
		if (zTYear > 0) {
			lastYear = DateUtil.calDate(cValiDate, zTYear - 1, DateUtil.DATE_TYPE_Y, null);
			if (debug) {
				logger.debug("退保年度大于0，保单周年日为：{}", DateUtil.getDateStr(cValiDate, DateConstant.DATE_FORMAT_7));
			}
		}

		int ztDays = DateUtil.calIntervalOfAbandon(lastYear, policyZTDateParamDTO.getzTPoint(), DateUtil.DATE_TYPE_D);
		policyZTDateParamDTO.setzTDays(ztDays);
		logger.info("本保单年度已经过天数为{}", ztDays);
	}

	/**
	 * 
	 * 计算退保所在保单年度的天数 
	 * ①计算上一个保单年度生效日 。
	 * ②计算本保单年度生效日。
	 * ③根据上一个保单年度生效日和本保单年度生效日计算退保所在保单年度的天数。
	 * 
	 * @history: 2018-3-19
	 * @author: wangwl_sinosoft
	 * @param policyZTDateParamDTO
	 */
	private void calZtYearDays(PolicyZTDateParamDTO policyZTDateParamDTO) {
		// 计算上个保单年度生效对应日
		Date lastPolCValiDate = DateUtil.calDate(policyZTDateParamDTO.getzTPoint(), -policyZTDateParamDTO.getzTDays(),
				DateUtil.DATE_TYPE_D, null);
		if (debug) {
			logger.debug("计算上一个保单年度的生效日:{}", DateUtil.getDateStr(lastPolCValiDate, DateConstant.DATE_FORMAT_7));
		}
		Date polCValiDate = DateUtil.calDate(lastPolCValiDate, 12, DateConstant.PERIOD_UNITS_MONTH, null);
		if (debug) {
			logger.debug("计算本保单年度的生效日:{}", DateUtil.getDateStr(polCValiDate, DateConstant.DATE_FORMAT_7));
		}
		int zTYearDays = DateUtil.calIntervalOfAbandon(lastPolCValiDate, polCValiDate, DateUtil.DATE_TYPE_D);
		if (zTYearDays <= 0) {
			throw new CalPolicyDateParamException("退保所在保单年度的天数小于等于0,计算有误,请核实!");
		}
		policyZTDateParamDTO.setzTYearDays(zTYearDays);
		logger.info("计算退保所在保单年度的天数:{}", zTYearDays);
	}

	/**
	 * 
	 * 计算 实际交至日和上次交至日间隔 以及退保在该交费期间经过的天数
	 * ①首先判断交费间隔是不是趸交，如果是趸交，实际交至日和上次交至日间隔为退保所在保单年度的天数， 交费期间经过的天数为本保单年度已经过天数。
	 * ②根据交费间隔和交至日期，计算上一个交至日期。 ③然后根据本保单交至日期和上一个交至日期计算实际交至日和上次交至日间隔。
	 * ④最后根据上一个交至日期和退保点计算交费期间经过的天数。
	 * 
	 * @history: 2018-3-19
	 * @author: wangwl_sinosoft
	 * @param policyZTDateParamDTO
	 */
	private void calPayIntervalAndCurrentDuringDays(PolicyZTDateParamDTO policyZTDateParamDTO) {
		Date payToDate = policyZTDateParamDTO.getPayToDate();
		int payIntv = policyZTDateParamDTO.getPayIntv();
		if (ENUM_PAYINTV_TYPE.SINGLE.getValue().equals(String.valueOf(payIntv))) {
			policyZTDateParamDTO.setCurrentDuringDays(policyZTDateParamDTO.getzTDays());
			policyZTDateParamDTO.setPaymentDays(policyZTDateParamDTO.getzTYearDays());
			logger.info("该保单交费间隔为趸交，退保在该交费期间经过的天数：{}，实际交至日和上次交至日间隔：{}", policyZTDateParamDTO.getzTDays(),
					policyZTDateParamDTO.getzTYearDays());
			return;
		}
		if (debug) {
			logger.debug("保单的交至日:{},交费间隔为:{}", DateUtil.getDateStr(payToDate, DateConstant.DATE_FORMAT_7), payIntv);
		}

		Date lastPayTodate = DateUtil.calDate(payToDate, -payIntv, DateUtil.DATE_TYPE_M, payToDate);
		if (debug) {
			logger.debug("上一次的交至日期为：{}", DateUtil.getDateStr(lastPayTodate, DateConstant.DATE_FORMAT_7));
		}

		int payInterval = DateUtil.calIntervalOfAbandon(lastPayTodate, payToDate, DateUtil.DATE_TYPE_D);
		if (payInterval <= 0) {
			throw new CalPolicyDateParamException("实际交至日和上次交至日间隔小于等于0,计算有误,请核实!");
		}
		policyZTDateParamDTO.setPaymentDays(payInterval);
		if (debug) {
			logger.debug("实际交至日和上次交至日间隔：{}", payInterval);
		}

		int currentDuringDays = DateUtil.calIntervalOfAbandon(lastPayTodate, policyZTDateParamDTO.getzTPoint(),
				DateUtil.DATE_TYPE_D);
		logger.info("退保在该交费期间经过的天数：{}", currentDuringDays);
		policyZTDateParamDTO.setCurrentDuringDays(currentDuringDays);
	}

	/**
	 * 
	 * 校验交费频率是否为趸交或者满期
	 * 
	 * @history: 2018-4-3
	 * @author: wangwl_sinosoft
	 * @param lcDuty
	 * @return
	 */
	private boolean checkPayIntv(LcDuty lcDuty) {
		String payIntvStr = lcDuty.getPayintv().toString();
		Date payToDate = lcDuty.getPaytodate();
		Date payEndDate = lcDuty.getPayenddate();
		int interval = DateUtil.calIntervalOfAbandon(payToDate, payEndDate, DateUtil.DATE_TYPE_D);
		if (ENUM_PAYINTV_TYPE.SINGLE.getValue().equals(payIntvStr)
				|| ENUM_PAYINTV_TYPE.IRREGULAR.getValue().equals(payIntvStr) || interval == 0) {
			return true;
		}
		return false;
	}

}
