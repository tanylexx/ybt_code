package com.sinosoft.surrender.common.config.util;

import org.springframework.beans.factory.annotation.Autowired;

import com.guohualife.ebiz.config.property.cache.PropertyCache;
/**
 * 属性路径取配置
 * @author yangyb
 * @date 2019年11月7日
 */
public class PropertyCacheUtil {
    @Autowired
    private PropertyCache propertyCache;

    /**
     * 属性路径取配置
     * 
     * @param path
     * @return
     */
    public Object getProperty(String path) {
        int lastIndexOf = path.lastIndexOf('/');
        String modulePath = path.substring(0, lastIndexOf);
        String code = path.substring(lastIndexOf + 1);
        return propertyCache.getProperty(modulePath, code);
    }
}
