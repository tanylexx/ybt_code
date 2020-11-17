package com.sinosoft.surrender.surrconfirm.bo;

import java.math.BigDecimal;

import com.sinosoft.surrender.db.model.YcSurrender;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

public interface SurrenderConfirmBO {

	/**
	 * 保存数据到退保中间表
	 * 
	 * @param surrenderConfirmReqDTO
	 */
	void saveSurrender(SurrenderConfirmReqDTO surrenderConfirmReqDTO, BigDecimal policyCashValue, String edoracceptno);

	/**
	 * 
	 * 根据保单号查询
	 *
	 * @history: 2018年4月8日
	 * @author: zhongdw_sinosoft
	 * @param contNo
	 * @return
	 */
	YcSurrender findSurrenderByContNo(String contNo);

	/**
	 * 
	 * 根据保单号和 保全确认时间以及保全类型备份退保中间表
	 *
	 * @history: 2018年5月14日
	 * @author: wangwl_sinosoft
	 * @param contNo
	 */
	void updateYcSurrenderByKey(YcSurrender ycSurrender);
}
