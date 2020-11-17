package com.sinosoft.surrender.cashvalue.bo.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.bo.CalAccountValueBO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.model.LcPol;

public class CalAccountInterestBOImplTest extends BaseTester {
	@Autowired
	private CalAccountValueBO calAccountInterestBO;
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;

	@Test
	public void testCalAccountInterest() {

		SurrenderDTO surrenderDTO = new SurrenderDTO();
		/*List<LcPol> LcPolList = lcPolSpecDAO.getLcPolByContNo("3101001690800668");
		surrenderDTO.setContNo("3101001690800668");
		for (LcPol lcPol : LcPolList) {
			surrenderDTO.setzTPoint(DateUtil.getDate("2018-02-28", "yyyy-MM-dd"));
			surrenderDTO.setcValiDate(lcPol.getCvalidate());
			surrenderDTO.setLcPol(lcPol);
			logger.debug(XmlUtil.toXml(calAccountInterestBO.calAccountValue(surrenderDTO)));

		}*/
		LcPol lcPol = new LcPol();
		lcPol.setPolno("9020000011128758");
		surrenderDTO.setzTPoint(DateUtil.getDate("2018-07-05", "yyyy-MM-dd"));
		surrenderDTO.setcValiDate(lcPol.getCvalidate());
		surrenderDTO.setLcPol(lcPol);
		logger.debug(XmlUtil.toXml(calAccountInterestBO.calAccountValue(surrenderDTO)));
	}

}
