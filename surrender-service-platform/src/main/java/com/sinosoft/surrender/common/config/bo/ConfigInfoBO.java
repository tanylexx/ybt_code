package com.sinosoft.surrender.common.config.bo;

import java.util.List;

import com.sinosoft.surrender.addprem.dto.AddPremUWRulesDto;
import com.sinosoft.surrender.cashvalue.dto.NewMSMDto;

/**
 * 获取配置中心数据并返回一个集合
 * @author yangyb_sionsoft
 *
 */
public interface ConfigInfoBO {
	/**
	 * 获取新短信配置 
	 * @return
	 */
	 List<NewMSMDto> getNewMSG();
	/**
	 * 获取该险种所有的加保核保规则
	 * @return
	 */
	List<AddPremUWRulesDto> getUWRulesByRiskCode(String riskCode);
	/**
	 * 获取该险种累计风险保额类型
	 * @return
	 */
	String getRiskAmntTypeByRiskCode(String riskCode);
}
