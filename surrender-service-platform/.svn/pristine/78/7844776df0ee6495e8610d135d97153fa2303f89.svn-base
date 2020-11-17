package com.sinosoft.surrender.common.cache.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.dao.YdCashValueRuleSpecDAO;
import com.sinosoft.surrender.common.cache.YdCashValueRuleCfgBO;
import com.sinosoft.surrender.db.model.YdCashValueRule;

@Component
public class YdCashValueRuleCfgBOImpl implements YdCashValueRuleCfgBO {
	private static final String LDCASHVALUERULE_LOCK = "LDCASHVALUERULE_LOCK";
	// Log logger =
	private Map<String, YdCashValueRule> ydCashValueRuleParamMap = new HashMap<String, YdCashValueRule>();
	@Autowired
	private YdCashValueRuleSpecDAO ydCashValueRuleSpecDAO;

	private void loadYdCashValueRuleCfg() {
		Map<String, YdCashValueRule> ydCashValueRuleParamMapTemp = new HashMap<String, YdCashValueRule>();
		List<YdCashValueRule> ydCashValueRuleList = ydCashValueRuleSpecDAO.getLdCashValueRule();
		if (CollectionUtils.isNotEmpty(ydCashValueRuleList)) {
			for (YdCashValueRule ydCashValueRule : ydCashValueRuleList) {
				String key = ydCashValueRule.getRiskcode();
				ydCashValueRuleParamMapTemp.put(key, ydCashValueRule);
			}
		}

		synchronized (LDCASHVALUERULE_LOCK) {

			ydCashValueRuleParamMap = ydCashValueRuleParamMapTemp;
		}
	}

	@Override
	public void initCache() {
		loadYdCashValueRuleCfg();
	}

	@Override
	public void refresh() {
		loadYdCashValueRuleCfg();
	}

	@Override
	public YdCashValueRule getYdCashValueRule(String riskCode) {
		if (MapUtils.isEmpty(ydCashValueRuleParamMap)) {
			loadYdCashValueRuleCfg();
		}
		try {
			return ydCashValueRuleParamMap.get(riskCode);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
