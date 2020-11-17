package com.sinosoft.surrender.common.notype.bo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.common.contant.lisenum.ENUM_LIS_EDORNO_TYPE;

public class GetEdorNoBOTest extends BaseTester {
	@Autowired
	private GetEdorNoBO getEdorNoBO;

	@Test
	public void testgetEdorNo() {

		try {
			// 生成保全受理号
			String limitNo = "86420600";// 管理机构
			String edorNo = ENUM_LIS_EDORNO_TYPE.EDORACCEPTNO.getValue();// 保全受理号-90488
			String edorNo1 = getEdorNoBO.getEdorNo(limitNo, edorNo);
			logger.info(XmlUtil.toXml(edorNo1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
