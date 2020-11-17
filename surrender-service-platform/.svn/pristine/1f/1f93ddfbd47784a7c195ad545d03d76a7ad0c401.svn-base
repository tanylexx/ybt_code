package com.sinosoft.surrender.common.notype.bo;

import java.math.BigDecimal;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.cache.RedisCacheBO;

public class RedisCacheBOTest extends BaseTester {
	@Autowired
	private RedisCacheBO redisCacheBO;

	@Test
	public void testPutRedisCache() {
		String key = "keyTest3";
		String value = "0";
		boolean putRedisCache = redisCacheBO.putRedisCache(key, value);

		if (putRedisCache) {
			BigDecimal valueOfRedis = redisCacheBO.getValueOfRedis(key);
			logger.info(valueOfRedis.toString());
		}

	}

	@Test
	public void testGetValueOfRedis() {
		String key = "keyTes3";
		//String value = "0";
		BigDecimal valueOfRedis = redisCacheBO.getValueOfRedis(key);
		logger.info(XmlUtil.toXml(valueOfRedis));

	}

	@Test
	public void testDeleteRedis() {
		String key = "CT|3101001590418288|2018-04-28";
		boolean deleteRedisCache = redisCacheBO.deleteRedisCache(key);
		if (deleteRedisCache) {
			logger.info("ture");
		}

	}

}
