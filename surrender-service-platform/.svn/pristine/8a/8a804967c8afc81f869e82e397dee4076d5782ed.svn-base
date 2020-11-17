package com.sinosoft.surrender.common.util;

import java.math.BigDecimal;
import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;

import com.sinosoft.surrender.common.exception.RulesCheckedException;

/**
 * 
 * 异常抛出工具类
 * 
 * @author: wangwl_sinosoft
 * @date: 2018-3-8-上午11:17:19
 * @version:
 */
public class ExceptionUtil extends Assert {

	/**
	 * if(expression) throw new RulesCheckedException(message);
	 * 
	 * @param expression
	 * @param message
	 */
	public static void isFalse(boolean expression, String message) {
		if (expression) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * if(!expression) throw new RulesCheckedException(message);
	 * 
	 * @param expression
	 * @param message
	 */
	public static void isTrue(boolean expression, String message) {
		if (!expression) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * 对象空判断
	 * 
	 * @param Object
	 *            object
	 * @param String
	 *            message
	 */
	public static void checkNull(Object object, String message) {
		if (object == null) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * 字符串空判断
	 * 
	 * @param String
	 *            reference
	 * @param String
	 *            message
	 */
	public static void checkEmpty(String reference, String message) {
		if (StringUtils.isBlank(reference)) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * 集合非空判断
	 * 
	 * @param coll
	 * @param message
	 */
	public static void checkCollEmpty(@SuppressWarnings("rawtypes") Collection coll, String message) {
		if (CollectionUtils.isEmpty(coll)) {
			throw new RulesCheckedException(message);
		}
	}

	
	/**
	 * 对象非空判断
	 * 
	 * @param Object
	 *            object
	 * @param String
	 *            message
	 */
	public static void checkNotNull(Object object, String message) {
		if (object != null) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * 字符串非空判断
	 * 
	 * @param String
	 *            reference
	 * @param String
	 *            message
	 */
	public static void checkNotEmpty(String reference, String message) {
		if (StringUtils.isNotBlank(reference)) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * 集合非空判断
	 * 
	 * @param coll
	 * @param message
	 */
	public static void checkCollNotEmpty(@SuppressWarnings("rawtypes") Collection coll, String message) {
		if (CollectionUtils.isNotEmpty(coll)) {
			throw new RulesCheckedException(message);
		}
	}
	
	/**
	 * 
	 * 判断BigDecimal不为空不能为零
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param reference
	 * @param message
	 */
	public static void checkZero(BigDecimal reference, String message) {
		if (reference == null || reference.compareTo(BigDecimal.ZERO) == 0) {
			throw new RulesCheckedException(message);
		}
	}
	
	
	
	
	/**
	 * 
	 * 判断BigDecimal是否大于零且不为空
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param reference
	 * @param message
	 */
	public static void checkBigDecimal(BigDecimal reference, String message) {
		if (reference != null && reference.compareTo(BigDecimal.ZERO) > 0) {
			throw new RulesCheckedException(message);
		}
	}

	/**
	 * 
	 * 校验字符串开头
	 * 
	 * @history: 2018-3-24
	 * @author: wangwl_sinosoft
	 * @param polState
	 * @param prefix
	 * @param message
	 */
	public static void checkStartWith(String reference, String prefix, String message) {
		if (reference.startsWith(prefix)) {
			throw new RulesCheckedException(message);
		}
	}
}
