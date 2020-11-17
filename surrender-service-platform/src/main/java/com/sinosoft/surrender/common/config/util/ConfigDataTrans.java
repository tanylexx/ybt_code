package com.sinosoft.surrender.common.config.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sinosoft.surrender.addprem.constant.AddPremConstant;
import com.sinosoft.surrender.addprem.dto.AddPremUWRulesDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.guohualife.ebiz.config.property.trans.DataTrans;
import com.sinosoft.surrender.cashvalue.dto.NewMSMDto;
import com.sinosoft.surrender.common.config.constant.ConfigConstant;
/**
 * @author yangyb_sinosoft
 *连接到配置中心 
 */
public class ConfigDataTrans implements DataTrans {

    private Log logger = LogFactory.getLog(getClass());

    @Override
    public Object transform(String path, byte[] data) {

        Map<String, Object> dataTransMap = new HashMap<String, Object>();

        try {
            if ((data == null) || (data.length == 0)) {
                dataTransMap = null;
                logger.info("配置内容为空");
                return dataTransMap;
            }
            String constr = new String(data, ConfigConstant.ENCODEING_UTF8);
            logger.info("路径：" + path + "\n" + "属性值：" + constr);
            if ((ConfigConstant.SENDNEWSMS_PATH).equals(path)) {
            	List<NewMSMDto> list = JSON.parseArray(constr, NewMSMDto.class);
            	dataTransMap.put(path, list);
                return dataTransMap;
            }else if(path.startsWith(AddPremConstant.ADD_PREM_UW_RULES_PATH_START) && path.endsWith(AddPremConstant.ADD_PREM_UW_RULES_PATH_END)){
                // add by jinwc 2020-11-02 seq-12633 关于【招商银行--招招保两全保险】前端开发及联调需求
                // 加保的核保规则配置在配置中心
                List<AddPremUWRulesDto> list =  JSON.parseArray(constr, AddPremUWRulesDto.class);
                dataTransMap.put(path, list);
                return dataTransMap;
            }else if(path.startsWith(AddPremConstant.ADD_PREM_UW_RULES_PATH_START) && path.endsWith(AddPremConstant.ADD_PREM_UW_RULES_RISKAMNTTYPE_END)){
                // add by jinwc 2020-11-02 seq-12633 关于【招商银行--招招保两全保险】前端开发及联调需求
                // 险种的风险保额类型配置在配置中心
                dataTransMap.put(path, constr);
                return dataTransMap;
            }
            
            logger.info("路径不存在");
            return null;

        } catch (Exception e) {
            logger.error("解析" + path + "失败", e);
            return null;
        }
    }
}
