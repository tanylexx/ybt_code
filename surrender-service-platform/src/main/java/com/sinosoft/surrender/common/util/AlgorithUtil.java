package com.sinosoft.surrender.common.util;

import java.math.BigDecimal;

import com.sinosoft.surrender.cashvalue.dto.CalCashValueParamDTO;

public class AlgorithUtil {
	// 默认除法运算精度
	private static final int DEF_DIV_SCALE = 10;

	/**
	 * 
	 * 使用差值算法进行计算，公式：CV(t+s)=((CV(t)+SB)*S/365+CV(t-1)*(1-S/365)+NP(cv)/（365/x）*
	 * (1-S'/x))*SA/SA0，参数说明如下：
	 * 
	 * CV(t+s):时刻现价；CV(t)：t保单年度末现价； x: 可垫天数；SB：t保单年度末生存给付； S'退保时所在交费期间经过的天数；
	 * CV(t-1)：t-1保单年度末现价； SA0：基本保额；SA：退保时剩余保额（有效保额）； S：退保时所在保单年度经过的天数；GP：期交毛保费；
	 * 计算用 退保点距保单生效对应日的天数
	 * NP(cv)：计算现金价值采纳的评估净保费，若S≦x，以S点插值计算现价及计息天数；若S﹥x，以垫至日计算现价及计息天数
	 * 
	 * @history: 2018-4-12
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 * @return
	 */
	public static BigDecimal calCashValueByDifference(CalCashValueParamDTO calCashValueParamDTO) {

		BigDecimal curCashValue = calCurCashValue(calCashValueParamDTO);

		BigDecimal lastCashValue = calLastCashValue(calCashValueParamDTO);

		BigDecimal curPrem_cv = calCurPrem_cv(calCashValueParamDTO);

		/**
		 * (curCashValue+lastCashValue+curPrem_cv)*SA/SA0
		 */
		BigDecimal constantlyCashValue = curCashValue.add(lastCashValue).add(curPrem_cv)
				.multiply(calCashValueParamDTO.getValidAmnt())
				.divide(calCashValueParamDTO.getBaseAmnt(), DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
		return constantlyCashValue;

	}

	/**
	 * 
	 * 计算本保单年度末现价以及生存金
	 * 
	 * curCashValue = (CV(t)+SB)*S/365
	 * 
	 * @history: 2018-4-16 wangwl_sinosoft
	 * @param calCashValueParamDTO
	 * @return
	 */
	public static BigDecimal calCurCashValue(CalCashValueParamDTO calCashValueParamDTO) {
		BigDecimal curCashValue = calCashValueParamDTO.getCurPolicyYearEndCashValue()
				.add(calCashValueParamDTO.getPolicyYearEndAlivePay()).multiply(calCashValueParamDTO.getzTDays())
				.divide(calCashValueParamDTO.getzTYearDays(), DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
		return curCashValue;
	}

	/**
	 * 计算上一保单年度末现价
	 * 
	 * lastCashValue= CV(t-1)*(1-S/365);
	 * 
	 * @history: 2018-4-16 wangwl_sinosoft
	 * @param calCashValueParamDTO
	 * @return
	 */
	public static BigDecimal calLastCashValue(CalCashValueParamDTO calCashValueParamDTO) {

		BigDecimal lastCashValue = calCashValueParamDTO.getLastPolicyYearEndCashValue().multiply(
				BigDecimal.ONE.subtract(calCashValueParamDTO.getzTDays().divide(calCashValueParamDTO.getzTYearDays(),
						DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP)));
		return lastCashValue;
	}

	/**
	 * 计算评估净保费
	 * 
	 * curPrem_cv = NP(cv)* (x-S')/365
	 * 
	 * @history: 2018-4-16 wangwl_sinosoft
	 * @param calCashValueParamDTO
	 * @return
	 */
	public static BigDecimal calCurPrem_cv(CalCashValueParamDTO calCashValueParamDTO) {
		BigDecimal curPrem_cv = calCashValueParamDTO.getPrem_cv()
				.multiply(calCashValueParamDTO.getPaymentDays().subtract(calCashValueParamDTO.getCurrentDuringDays()))
				.divide(calCashValueParamDTO.getzTYearDays(), DEF_DIV_SCALE, BigDecimal.ROUND_HALF_UP);
		return curPrem_cv;
	}
	

}
