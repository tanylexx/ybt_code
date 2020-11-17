package com.sinosoft.surrender.common.util;

import java.util.Locale;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;

/**
 * 
 * @author wangwl_sinosoft
 *
 */
public class MessageSourceProvider implements MessageSourceAware {

	private static final Log logger = LogFactory
			.getLog(MessageSourceProvider.class);

	private static MessageSource ms = null;

	public void setMessageSource(MessageSource messageSource) {
		ms = messageSource;
	}

	public static MessageSource getMessageSource() {
		return ms;
	}

	public static String getMessage(String msgKey, String[] params) {

		if (null == ms) {
			logger.fatal("Check message source provider configuration in application context.");
		}

		return ms.getMessage(msgKey, params, Locale.SIMPLIFIED_CHINESE);

	}

}
