package com.sinosoft.surrender.surrconfirm.dao;

import java.math.BigDecimal;
import java.util.List;

import com.sinosoft.surrender.db.model.YcSurrender;

public interface YcSurrenderSpecDAO {

	/**
	 * 根据保单号查询退保记录
	 * 
	 * @history: 2018-5-2 wangwl_sinosoft
	 * @param contNo
	 * @param comfirmDate
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
	
	List<YcSurrender> findConfirmList();

	/**
	 * 根据保单号查询退保或给付金额
	 * 
	 * @history: 2020-5-8 LISBUG-7811 银保通在线受理提醒短信调整
	 * @param contNo
	 * @return
	 */
	BigDecimal getApplyPremByContNo(String contno);
	
}
