package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sinosoft.surrender.cashvalue.bo.CalSurrenderBO;
import com.sinosoft.surrender.cashvalue.bo.CalculatorBO;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.cashvalue.dto.CalculatorDTO;

@Component
public class CalSurrenderBOImpl implements CalSurrenderBO {
	private Log loger = LogFactory.getLog(getClass());
	@Autowired
	private CalculatorBO calculatorBO;

	@Override
	public BigDecimal calGetSurrender(String calCode, AlgorithElementDTO algorithmElementDTO) {
		// 1.填充计算要素
		loger.info("=====调用核心配置表算法开始====");
		CalculatorDTO calculatorDTO = new CalculatorDTO();
		calculatorDTO.setCalcode(calCode);
		calculatorDTO.setParamsObj(algorithmElementDTO);
		// 2.调用核心算法
		String valueStr = calculatorBO.calculate(calculatorDTO);
		if (StringUtils.isBlank(valueStr)) {
			return BigDecimal.ZERO;
		}
		BigDecimal value = new BigDecimal(valueStr);
		loger.info("=====调用核心配置表算法结束====");
		return value;
	}

	@Override
	public BigDecimal calDutyCashValue(String riskCode, Map<String, Object> mapParam) {
		loger.info("=====调用现价配置表算法开始====");
		CalculatorDTO calculatorDTO = new CalculatorDTO();
		calculatorDTO.setCalcode(riskCode);
		calculatorDTO.setParamsObj(mapParam);
		// 2.调用核心算法
		String valueStr = calculatorBO.calCashValue(calculatorDTO);
		if (StringUtils.isBlank(valueStr)) {
			return BigDecimal.ZERO;
		}
		BigDecimal dutyCashValue = new BigDecimal(valueStr);
		loger.info("=====调用现价配置表算法结束====");
		return dutyCashValue;
	}

}
