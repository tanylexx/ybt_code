package com.sinosoft.surrender.common.cache;

import java.math.BigDecimal;

public interface RedisCacheBO {

	/**
	 * 
	 * 根据 key = 保单号+计算日期 +保全类型，查询Redis
	 * 
	 * @history: 2018-4-4
	 * @author: wangwl_sinosoft
	 * @param Key
	 * @return
	 */
	BigDecimal getValueOfRedis(String Key);

	/**
	 * 
	 * 將数据存放至缓存中
	 * 
	 * @history: 2018-4-4
	 * @author: wangwl_sinosoft
	 * @param key
	 * @param value
	 * @return
	 */
	boolean putRedisCache(String key, String value);

	/**
	 * 
	 * 根据key和namespace 删除缓存
	 * 
	 * @history: 2018-4-12
	 * @author: wangwl_sinosoft
	 * @param key
	 * @param value
	 * @return
	 */
	boolean deleteRedisCache(String key);
	
	/**
	 * 
	 * 根据 key = 保单号+计算日期 +保全类型，查询Redis
	 * 
	 * @history: 2018-4-4
	 * @author: wangwl_sinosoft
	 * @param Key
	 * @return
	 */
	String getEndDateValueOfRedis(String Key);

}
