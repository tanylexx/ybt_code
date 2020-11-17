package com.sinosoft.surrender.surrconfirm.bo.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.base.BaseTester;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderConfirmSendMessageBO;

public class SurrenderConfirmSendMessageBOTest extends BaseTester {
	@Autowired
	private SurrenderConfirmSendMessageBO surrenderConfirmSendMessageBO;

	@Test
	public void testSendConfirmMessage() {
		YcSendMessage ycSurrender = new YcSendMessage();
		ycSurrender.setContno("3101001690797668");
		ycSurrender.setEdortype("CT");
		surrenderConfirmSendMessageBO.sendConfirmMessage(ycSurrender);

	}

}
