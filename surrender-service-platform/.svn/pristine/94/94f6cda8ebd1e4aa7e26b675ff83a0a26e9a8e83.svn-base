package com.sinosoft.surrender.surrconfirm.dao.impl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.platform.common.api.util.XmlUtil;
import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.surrconfirm.dao.YcSendMessageSpecDAO;

public class YcSendMessageSpecDAOImplTest extends BaseTester{
	@Autowired
	private YcSendMessageSpecDAO ycSendMessageSpecDAO;
	
	@Test
	public void testGetSendMessageInfo() {
		List<YcSendMessage> sendMessageInfo = ycSendMessageSpecDAO.getSendMessageInfo();
		logger.info("c查询结果：{}",XmlUtil.toXml(sendMessageInfo));
	}
	
	
	@Test
	public void testUpdateYcSendMessageByContNo() {
		YcSendMessage ycSendMessage = new YcSendMessage();
		ycSendMessage.setContno("3101001890246558");
		ycSendMessage.setEdortype("CT");
		ycSendMessageSpecDAO.updateYcSendMessageByContNo(ycSendMessage);
	}
	
}
