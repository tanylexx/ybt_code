package com.sinosoft.surrender.cashvalue.dao.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.DateUtil;

public class LcPolSpecDAOImplTest extends BaseTester {
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;

	@Test
	public void testGetLcPolByContNo() {
		try {
			logger.info(XmlUtil.toXml(lcPolSpecDAO.getLcPolByContNo("3101000810003968")));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testQueryPrecalculatedParam() {
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 从配置文件，拿到riskcode
			Object riskCodeObj = ConfigInfo.getContextProperty("RISKCODE");
			// 从配置文件，拿到rownum,riskcode
			int rowNum = 1000;
			Object obj = ConfigInfo.getContextProperty("ROWNUM");
			if (obj != null) {
				rowNum = Integer.valueOf((String) ConfigInfo.getContextProperty("ROWNUM"));
			}
			String riskCode = (String) riskCodeObj;
			String[] rcarr = riskCode.split("\\|");
			List<String> riskCodes = new ArrayList<String>(Arrays.asList(rcarr));
			map.put("rowNum", rowNum);
			map.put("riskCodes", riskCodes);
			map.put("edorAppDate", DateUtil.getCurrentDate());
			logger.info(XmlUtil.toXml(lcPolSpecDAO.queryPrecalculatedParam(map)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
}
