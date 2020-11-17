package com.sinosoft.surrender.cashvalue.dao.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.cashvalue.dao.YcPolicyCashValueSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.common.util.DateUtil;

public class YcPolicyCashValueSpecDAOImplTest extends BaseTester {
	//private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private YcPolicyCashValueSpecDAO ycPolicyCashValueSpecDAO;
	@Test
	public void testYcPolicyCashValue() {
		long current = System.currentTimeMillis();
		PolicyCashValueReqDTO policyCashValueReqDTO = new PolicyCashValueReqDTO();
		try {
			policyCashValueReqDTO.setContNo("4101001590000788");
			policyCashValueReqDTO.setApplyDate(DateUtil.getDate("2018-04-19", "yyyy-MM-dd"));
			policyCashValueReqDTO.setEdorType("CT");
			logger.info("c查询结果：{}",XmlUtil.toXml(ycPolicyCashValueSpecDAO.getYcPolicyCashValue(policyCashValueReqDTO)));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("校验结果:{}耗时:{}毫秒", 
				"1", System.currentTimeMillis() - current);
	}
	
	
	@Test
	public void testUpdateYcPolicyCashValueByKey() {
		
		long current = System.currentTimeMillis();
		PolicyCashValueReqDTO policyCashValueResDTO = new PolicyCashValueReqDTO();
		try {
			policyCashValueResDTO.setContNo("4101001590000788");
			policyCashValueResDTO.setApplyDate(DateUtil.getDate("2018-04-19", "yyyy-MM-dd"));
			policyCashValueResDTO.setEdorType("CT");
			ycPolicyCashValueSpecDAO.updateYcPolicyCashValueByKey(policyCashValueResDTO);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("校验结果:{}耗时:{}毫秒", 
				"1", System.currentTimeMillis() - current);
		
	}
	
	
	
}
