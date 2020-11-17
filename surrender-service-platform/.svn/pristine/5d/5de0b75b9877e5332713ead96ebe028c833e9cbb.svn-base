package com.sinosoft.surrender.cashvalue.bo.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.QueryPrecalculatedParamBO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.DateUtil;

@Component
public class QueryPrecalculatedParamBOImpl implements QueryPrecalculatedParamBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;

	@Override
	public List<PolicyCashValueReqDTO> queryPrecalculatedParam() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 从配置文件，拿到riskcode
		Object riskCodeObj = ConfigInfo.getContextProperty("RISKCODE");
		if (riskCodeObj == null) {
			logger.debug("未在配置文件中拿到riskCode，请检查");
			return null;
		}

		// 从配置文件，拿到rownum,riskcode
		int  flag = 0;
		Object obj = ConfigInfo.getContextProperty("SPCEFLAG");
		if (obj != null) {
			flag = Integer.valueOf((String) obj);
		}
		String riskCode = (String) riskCodeObj;
		String[] rcarr = riskCode.split("\\|");
		List<String> riskCodes = new ArrayList<String>(Arrays.asList(rcarr));
		map.put("riskCodes", riskCodes);
		map.put("edorAppDate", DateUtil.calDate(DateUtil.getCurrentDate(), flag, DateUtil.DATE_TYPE_D, null));
		return lcPolSpecDAO.queryPrecalculatedParam(map);
	}

	@Override
	public List<PolicyCashValueReqDTO> queryCallCorePrecalculatedParam() {
		Map<String, Object> map = new HashMap<String, Object>();
		// 从配置文件，拿到riskcode
		Object riskCodeObj = ConfigInfo.getContextProperty("OTHERRISKCODE");
		if (riskCodeObj == null) {
			logger.debug("未在配置文件中拿到riskCode，请检查");
			return null;
		}

		// 从配置文件，拿到rownum,riskcode
		int  flag = 0;
		Object obj = ConfigInfo.getContextProperty("SPCEFLAG");
		if (obj != null) {
			flag = Integer.valueOf((String) obj);
		}
		int rowNum = 1000;
		Object rowNumObj = ConfigInfo.getContextProperty("ROWNUM");
		if (rowNumObj != null) {
			rowNum = Integer.valueOf((String) ConfigInfo.getContextProperty("ROWNUM"));
		}
		String riskCode = (String) riskCodeObj;
		String[] rcarr = riskCode.split("\\|");
		List<String> riskCodes = new ArrayList<String>(Arrays.asList(rcarr));
		map.put("riskCodes", riskCodes);
		map.put("rowNum", rowNum);
		map.put("edorAppDate", DateUtil.calDate(DateUtil.getCurrentDate(), flag, DateUtil.DATE_TYPE_D, null));
		
		return lcPolSpecDAO.queryCallCorePrecalculatedParam(map);
	}

}
