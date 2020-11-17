package com.sinosoft.surrender.cashvalue.bo;

import com.sinosoft.surrender.cashvalue.dto.CalculatorDTO;

/**
 * 
 * TODO Types Comment
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-13-下午3:35:25
 * @version:
 */
public interface CalculatorBO {

	/**
	 * 
	 * LMCalMode算法计算
	 * 
	 * @history: 2018-3-13
	 * @author: wangwl_sinosoft
	 * @param calculatorDTO
	 * @return
	 */
	String calculate(CalculatorDTO calculatorDTO);

	/**
	 * LD_CASHVALUE_RULE 现价公式配置表 算法计算
	 * 
	 * @history: 2018-3-15
	 * @author: wangwl_sinosoft
	 * @param calculatorDTO
	 * @return
	 */
	String calCashValue(CalculatorDTO calculatorDTO);
}
