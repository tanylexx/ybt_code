package com.sinosoft.surrender.cashvalue.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

public interface YcPolicyCashValueSpecDAO {
	/**
	 * 
	 * 根据计算日期和保单号查询现价
	 * 
	 * @history: 2018-4-13
	 * @author: wangwl_sinosoft
	 * @param policyCashValueReqDTO
	 * @return
	 */
	List<YcPolicyCashValue> getYcPolicyCashValue(PolicyCashValueReqDTO policyCashValueReqDTO);

	/**
	 * 
	 * 根据 保单号+计算时间+ 保全类型 查询现价
	 * 
	 * @history: 2018年4月18日
	 * @author: zhongdw_sinosoft
	 * @param map
	 * @return
	 */
	BigDecimal findYcPolicyCashValue(SurrenderConfirmReqDTO surrenderConfirmReqDTO);
	
	/**
	 * 用于退保确认查询
	 * @param surrenderConfirmReqDTO
	 * @return
	 */
	BigDecimal findYcPolicyCashValueToConfirm(SurrenderConfirmReqDTO surrenderConfirmReqDTO);

	/**
	 * 
	 * 查询现价表中的所有数据
	 * 
	 * @history: 2018年4月23日
	 * @author: zhongdw_sinosoft
	 * @return
	 */
	List<YcPolicyCashValue> getAllYcPolicyCashValue();

	/**
	 * 
	 * 删除所有的数据
	 * 
	 * @history: 2018年4月23日
	 * @author: zhongdw_sinosoft
	 */
	void updateAllYcPolicyCashValue();

	/**
	 * 删除key=保单号+计算日期+保全类型的数据
	 * 
	 * @history: 2018-5-2 wangwl_sinosoft
	 * @param policyCashValueReqDTO
	 */
	void updateYcPolicyCashValueByKey(PolicyCashValueReqDTO policyCashValueReqDTO);
	
	/**
	 * 
	 * 查询几天前的有效数据
	 * 
	 * @history: 2018年10月23日
	 * @author: zhongdw_sinosoft
	 */
	List<YcPolicyCashValue> getDaysAgoYcPolicyCashValue(Date date);
}
