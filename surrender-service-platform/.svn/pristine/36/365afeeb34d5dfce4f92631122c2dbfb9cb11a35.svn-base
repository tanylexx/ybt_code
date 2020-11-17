package com.sinosoft.surrender.surrconfirm.batch;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.common.util.quartz.batch.MultThreadBatch;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderConfirmSendMessageBO;
import com.sinosoft.surrender.surrconfirm.dao.YcSendMessageSpecDAO;

@Component("confirmSendMessageBatchImpl")
public class ConfirmSendMessageBatchImpl implements MultThreadBatch {
	@Autowired
	private YcSendMessageSpecDAO ycSendMessageSpecDAO;
	@Autowired
	private SurrenderConfirmSendMessageBO surrenderConfirmSendMessageBO;

	@Override
	public void executeInit() throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public List<?> executeData() throws Exception {
		List<YcSendMessage> findConfirmList = ycSendMessageSpecDAO.getSendMessageInfo();
		return findConfirmList;
	}

	@Override
	public void execute(Object obj) throws Exception {
		YcSendMessage ycSendMessage = (YcSendMessage) obj;
		surrenderConfirmSendMessageBO.sendConfirmMessage(ycSendMessage);
	}

	@Override
	public void executeFinal() throws Exception {

	}

}
