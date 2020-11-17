package com.sinosoft.surrender.common.cache.impl;

import java.math.BigDecimal;
import java.util.HashMap;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.guohualife.common.util.cache.client.RedisCache;
import com.sinosoft.surrender.common.cache.RedisCacheBO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.util.ConfigInfo;

@Component
public class RedisCacheBOImpl implements RedisCacheBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private RedisCache redisCache;

	@Override
	public BigDecimal getValueOfRedis(String Key) {
		long current = System.currentTimeMillis();
		Object premLimit = redisCache.get(Key, Constant.NAMESPACE);
		BigDecimal value = new BigDecimal("-1");
		if (premLimit != null) {
			value = new BigDecimal((String) premLimit);
		}
		logger.debug("查询缓存数据结束，耗时:{}毫秒", System.currentTimeMillis() - current);
		return value;

	}

	@Override
	public boolean putRedisCache(String key, String value) {
		long current = System.currentTimeMillis();
		boolean putFlag = redisCache.put(key, Constant.NAMESPACE, value);
		Object redisCasheTime = ConfigInfo.getContextProperty("REDISCACHETIME");
		if (redisCasheTime != null && StringUtils.isNotBlank((String) redisCasheTime)) {
			boolean expire = redisCache.expire(key, Constant.NAMESPACE, Integer.parseInt((String) redisCasheTime));
			if (!expire) {
				return false;
			}
		}
		logger.debug("推缓存数据结束，耗时:{}毫秒", System.currentTimeMillis() - current);
		return putFlag;
	}

	@Override
	public boolean deleteRedisCache(String key) {
		long current = System.currentTimeMillis();
		boolean deleteFlag = redisCache.delete(key, Constant.NAMESPACE);
		logger.debug("刪除缓存数据结束，耗时:{}毫秒", System.currentTimeMillis() - current);
		return deleteFlag;
	}
	
	public static void main(String[] args) {
		HashMap<String,String> ldCustomerMobileList=new HashMap<String,String>();
		ldCustomerMobileList.put("1", "1");
		ldCustomerMobileList.put("1", "1");
		
	}

	@Override
	public String getEndDateValueOfRedis(String Key) {
		long current = System.currentTimeMillis();
		Object premLimit = redisCache.get(Key, Constant.NAMESPACE);
		String endDate = "";
		if (premLimit != null) {
			endDate = (String) premLimit;
		}
		logger.debug("查询缓存数据结束，耗时:{}毫秒", System.currentTimeMillis() - current);
		return endDate;
	}

}
