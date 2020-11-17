package com.sinosoft.surrender.common.config.bo.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.sinosoft.surrender.addprem.constant.AddPremConstant;
import com.sinosoft.surrender.addprem.dto.AddPremUWRulesDto;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.dto.NewMSMDto;
import com.sinosoft.surrender.common.config.bo.ConfigInfoBO;
import com.sinosoft.surrender.common.config.constant.ConfigConstant;
import com.sinosoft.surrender.common.config.util.PropertyCacheUtil;
/**
 * 获取配置中心数据并返回一个集合 
 * @author yangyb_sionsoft 
 *
 */
@Component
public class ConfigInfoBOImpl implements ConfigInfoBO { 
	@Resource
	private PropertyCacheUtil propertyCacheUtil;
	@Override
	@SuppressWarnings("unchecked")
	public List<NewMSMDto> getNewMSG() {
		Object object = propertyCacheUtil.getProperty(ConfigConstant.SENDNEWSMS_PATH);//获取配置中心数据
		List<NewMSMDto> newMSMDtolist = null;//初始化配置中心的数据集合
		if(object!=null){	
			newMSMDtolist = ((Map<String,List<NewMSMDto>>)object).get(ConfigConstant.SENDNEWSMS_PATH);//通过键值对的键（地址）取值（数据）
		}
		return newMSMDtolist;
	}

	@Override
	public List<AddPremUWRulesDto> getUWRulesByRiskCode(String riskCode) {
		Object property = propertyCacheUtil.getProperty(AddPremConstant.ADD_PREM_UW_RULES_PATH_START + riskCode + "/uwRules");
		List<AddPremUWRulesDto> addPremUWRulesDtoList = null;
		if(property!=null){
			addPremUWRulesDtoList = ((Map<String,List<AddPremUWRulesDto>>)property).get(AddPremConstant.ADD_PREM_UW_RULES_PATH_START + riskCode + "/uwRules");//通过键值对的键（地址）取值（数据）
		}
		return addPremUWRulesDtoList;
	}

	@Override
	public String getRiskAmntTypeByRiskCode(String riskCode) {
		Object property = propertyCacheUtil.getProperty(AddPremConstant.ADD_PREM_UW_RULES_PATH_START + riskCode + "/riskAmntType");
		String riskAmntList = null;
		if(property!=null){
			riskAmntList = ((Map<String,String>)property).get(AddPremConstant.ADD_PREM_UW_RULES_PATH_START + riskCode + "/riskAmntType");//通过键值对的键（地址）取值（数据）
		}
		return riskAmntList;
	}
}
