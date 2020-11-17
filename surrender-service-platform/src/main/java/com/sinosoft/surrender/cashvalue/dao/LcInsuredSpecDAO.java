package com.sinosoft.surrender.cashvalue.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.LcInsured;

public interface LcInsuredSpecDAO {
	/**
	 * 
	 * 根据保单号查询被保人信息
	 * 
	 * @history: 2018-3-14
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @return
	 */
	List<LcInsured> getLcinsuredByContNo(String contNo);

	/**
	 * 
	 * 根据保单号和被人客户号查询被保人信息
	 *
	 * @history: 2018年3月26日
	 * @author: wangwl_sinosoft
	 * @param contNo
	 * @param insuredNo
	 * @return
	 */
	List<LcInsured> getLcinsuredInfos(String contNo, String insuredNo);
}
