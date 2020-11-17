package com.sinosoft.surrender.cashvalue.bo.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.bo.CalPolicyZTDateParamBO;
import com.sinosoft.surrender.cashvalue.dao.LcDutySpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.model.LcDuty;
import com.sinosoft.surrender.db.model.LcPol;

public class CalPolicyZTDateParamBOImplTest extends BaseTester {

	@Autowired
	private CalPolicyZTDateParamBO calPolicyZTDateParamBO;
	@Autowired
	private LcDutySpecDAO lcDutySpecDAO;
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;

	@Test
	public void testCalPolicyZTDateParam() {
		// int zTYear = 1;
		try {
			SurrenderDTO surrenderDTO = new SurrenderDTO();
			List<LcPol> LcPolList = lcPolSpecDAO.getLcPolByContNo("8828000000115188");
			surrenderDTO.setContNo("8828000000115188");
			for (LcPol lcPol : LcPolList) {
				surrenderDTO.setEdorValiDate(DateUtil.getDate("2018-02-28", "yyyy-MM-dd"));
				surrenderDTO.setcValiDate(lcPol.getCvalidate());
				List<LcDuty> lcDutylist = lcDutySpecDAO.getLcDutyList(lcPol.getPolno());
				surrenderDTO.setLcDuty(lcDutylist.get(0));
				surrenderDTO.setLcPol(lcPol);
				logger.info(XmlUtil.toXml(calPolicyZTDateParamBO.calPolicyZTDateParam(surrenderDTO)));
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
	}
}
