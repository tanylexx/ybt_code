package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.bo.CalculatorBO;
import com.sinosoft.surrender.cashvalue.dto.CalculatorDTO;

public class CalculatorBOImplTest extends BaseTester {
	@Autowired
	private CalculatorBO calculatorBO;

	@Test
	public void testCalCashValue() {

		try {
			CalculatorDTO calculatorDTO = new CalculatorDTO();
			calculatorDTO.setCalcode("3159");
			Map<String, String> mapParam = new HashMap<String, String>();
			mapParam.put("CV(t)", "1000");
			mapParam.put("SB", "200");
			mapParam.put("S", "100");
			mapParam.put("zTYearDays", "365");
			mapParam.put("CV(t-1)", "800");
			mapParam.put("NP(cv)", "200");
			mapParam.put("x", "10");
			mapParam.put("S'", "50");
			mapParam.put("SA", "10000");
			mapParam.put("SA0", "20000");
			calculatorDTO.setParamsObj(mapParam);
			String valueStr = calculatorBO.calCashValue(calculatorDTO);
			BigDecimal value = new BigDecimal(valueStr);
			logger.info(XmlUtil.toXml(value));
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
