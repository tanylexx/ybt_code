package com.sinosoft.surrender.cashvalue.bo;

import java.math.BigDecimal;

import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;

public interface CalAccountValueBO {
	/**
	 * 计算账户价值
	 * 
	 * @history: 2018-4-22 wangwl_sinosoft
	 * @return
	 */
	BigDecimal calAccountValue(SurrenderDTO surrenderDTO);
}
