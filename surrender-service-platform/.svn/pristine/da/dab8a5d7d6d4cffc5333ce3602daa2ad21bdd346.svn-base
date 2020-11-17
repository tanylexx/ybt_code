package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.bo.CalPolicyYearEndAlivePayBO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.model.LcPol;

public class CalPolicyYearEndAlivePayBOTest extends BaseTester{
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;
	@Resource(name ="calPolicyYearEndAlivePayCommonBOImpl")
	private CalPolicyYearEndAlivePayBO calPolicyYearEndAlivePayBO;
	@Test
	public void testCalPolicyYearEndAlivePayBO(){
		
		try {
			List<LcPol> LcPolList = lcPolSpecDAO.getLcPolByContNo("");
			for (LcPol lcPol : LcPolList) {
				SurrenderDTO surrenderDTO = new SurrenderDTO();
				surrenderDTO.setLcPol(lcPol);
				Date startDate = DateUtil.getDate("2018-04-03","yyyy-mm-dd");
				Date payToDate = lcPol.getPaytodate();
				surrenderDTO.setzTPoint(startDate);
				surrenderDTO.setPayToDate(payToDate);
				BigDecimal calPolicyYearEndAlivePay = calPolicyYearEndAlivePayBO.calPolicyYearEndAlivePay(surrenderDTO);
				logger.info(XmlUtil.toXml(calPolicyYearEndAlivePay));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
