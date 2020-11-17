package com.sinosoft.surrender.cashvalue.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * 
 * 测试基类
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-12-下午2:54:00
 * @version:
 */

@ContextConfiguration(locations = "classpath:spring/applicationContext.xml")
public class BaseTester extends AbstractJUnit4SpringContextTests {

	protected Logger logger = LoggerFactory.getLogger(getClass());

}
