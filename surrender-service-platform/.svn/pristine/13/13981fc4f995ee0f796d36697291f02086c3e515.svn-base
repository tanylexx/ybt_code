package com.sinosoft.surrender.cashvalue.bo;

import java.math.BigDecimal;
import java.util.Map;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;

/**
 * 计算保全金额
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-上午10:47:02
 * @version:
 */
public interface CalSurrenderBO {

	/**
	 * 
	 * 计算保全金额
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param zTYear
	 * @param surrenderDTO
	 * @return
	 */
	BigDecimal calGetSurrender(String calCode,AlgorithElementDTO algorithElementDTO);
	
	
	/**
	 * 	
	 *现价公式配置表,计算现价价值 
	 *
	 * @history: 2018-3-15
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 * @return
	 */
	BigDecimal calDutyCashValue(String riskCode,Map<String, Object> mapParam);
}
