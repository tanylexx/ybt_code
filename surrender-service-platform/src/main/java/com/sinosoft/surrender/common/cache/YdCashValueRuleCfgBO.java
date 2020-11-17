package com.sinosoft.surrender.common.cache;

import com.sinosoft.surrender.db.model.YdCashValueRule;

/**
 * 
 * 退保公式缓存配置
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-下午7:31:45
 * @version:
 */
public interface YdCashValueRuleCfgBO {

	/**
	 * 
	 * 初始化缓存
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 */
	void initCache();

	/**
	 * 
	 * 刷新缓存
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 */
	void refresh();

	/**
	 * 
	 * 根据riskCode查询对应的现价计算配置
	 * 
	 * @history: 2018-3-8
	 * @author: wangwl_sinosoft
	 * @param riskCode
	 * @return
	 */
	YdCashValueRule getYdCashValueRule(String riskCode);

}
