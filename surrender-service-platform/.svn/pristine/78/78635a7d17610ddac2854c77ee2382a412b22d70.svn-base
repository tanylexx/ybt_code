package com.sinosoft.surrender.common.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class ConfigInfo extends PropertyPlaceholderConfigurer {

    // 配置文件
    private static Map<String, Object> ctxPropertiesMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory arg0, Properties arg1) throws BeansException {
        super.processProperties(arg0, arg1);

        ctxPropertiesMap = new HashMap<String, Object>();

        for (Object key : arg1.keySet()) {
            String keyStr = key.toString();
            String value = arg1.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }

    }

    // 获取配置文件内容
    public static Object getContextProperty(String name) {
        return ctxPropertiesMap.get(name);
    }

}
