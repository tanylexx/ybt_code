package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.bo.CalSurrenderBO;
import com.sinosoft.surrender.cashvalue.dao.LcDutySpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dao.LmEdorZT1SpecDAO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.util.MessageSourceProvider;
import com.sinosoft.surrender.db.model.LcDuty;
import com.sinosoft.surrender.db.model.LcPol;
import com.sinosoft.surrender.db.model.LmEdorZT1;

public class CalGetEndorseBOImplTest extends BaseTester {
	@Resource(name = "calPolicyCashAndPremBOImpl")
	private CalSurrenderBO calGetEndorseBO;
	@Autowired
	private LcDutySpecDAO lcDutySpecDAO;
	@Autowired
	private LmEdorZT1SpecDAO lmEdorZT1SpecDAO;
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;

	@Test
	public void testGetSurrender() {
		try {
			//int zTYear = 1;
			SurrenderDTO surrenderDTO = new SurrenderDTO();
			List<LcPol> LcPolList = lcPolSpecDAO.getLcPolByContNo("3101001539001128");
			surrenderDTO.setContNo("3101001539001128");
			for (LcPol lcPol : LcPolList) {
				List<LcDuty> lcDutylist = lcDutySpecDAO.getLcDutyList(lcPol.getPolno());
				List<LmEdorZT1> lmEdorZT1List = lmEdorZT1SpecDAO.getLmEdorZT1Infos(lcPol.getRiskcode(),
						lcDutylist.get(0).getDutycode());
				surrenderDTO.setLcDuty(lcDutylist.get(0));
				surrenderDTO.setLcPol(lcPol);
				surrenderDTO.setCalCode(lmEdorZT1List.get(0).getCashvaluecode());
				surrenderDTO.setZtYear(new BigDecimal("1"));
			/*	BigDecimal value = calGetEndorseBO.calGetSurrender(surrenderDTO);*/
				/*logger.info(XmlUtil.toXml(value));*/
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}

	}
	@Test
	public void test1(){
		/*List<String> list =new ArrayList<String>();
		list.add("1");
		list.add("2");
		JSONArray jsonArray = (JSONArray) JSONArray.toJSON(list);
		System.out.println(jsonArray.toString());*/
	String age	= MessageSourceProvider.getMessage("ybtCoreAddress", null);
	System.out.println(age);
	}
	

/*	@Test
	public void testGetSurrender() {
		try {
			//int zTYear = 1;
			SurrenderDTO surrenderDTO = new SurrenderDTO();
			List<LcPol> LcPolList = lcPolSpecDAO.getLcPolByContNo("1101001690000138");
			//surrenderDTO.setContNo("3101001539001128");
			for (LcPol lcPol : LcPolList) {
				List<LcDuty> lcDutylist = lcDutySpecDAO.getLcDutyList(lcPol.getPolno());
				List<LmEdorZT1> lmEdorZT1List = lmEdorZT1SpecDAO.getLmEdorZT1Infos(lcPol.getRiskcode(),
						lcDutylist.get(0).getDutycode());
				surrenderDTO.setLcDuty(lcDutylist.get(0));
				surrenderDTO.setLcPol(lcPol);
				surrenderDTO.setCalCode(lmEdorZT1List.get(0).getCashvaluecode());
				surrenderDTO.setZtYear(new BigDecimal("1"));
				BigDecimal value = calGetEndorseBO.calGetSurrender(lmEdorZT1List.get(0).getCashvaluecode(),surrenderDTO);
				logger.info(XmlUtil.toXml(value));
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
			e.printStackTrace();
		}

	}*/
	
	
	
	
	
	
	
}
