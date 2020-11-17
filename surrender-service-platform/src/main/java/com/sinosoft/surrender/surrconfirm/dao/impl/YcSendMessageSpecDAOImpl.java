package com.sinosoft.surrender.surrconfirm.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.sinosoft.surrender.db.base.SurrenderBaseDAOImpl;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.surrconfirm.dao.YcSendMessageSpecDAO;

@Repository
public class YcSendMessageSpecDAOImpl extends SurrenderBaseDAOImpl implements YcSendMessageSpecDAO {

	@Override
	public List<YcSendMessage> getSendMessageInfo() {
		@SuppressWarnings("unchecked")
		List<YcSendMessage> ycSendMessageList = this.queryForList("YCSENDMESSAGESPECDAO.getSendMessageInfo", null);
		return ycSendMessageList;
	}

	@Override
	public void updateYcSendMessageByContNo(YcSendMessage ycSendMessage) {
		this.updateObject("YCSENDMESSAGESPECDAO.updateYcSendMessageByContNo", ycSendMessage);
	}

	@Override
	public List<YcSendMessage> queryMessageByContNo(String contno) {
		@SuppressWarnings("unchecked")
		List<YcSendMessage> ycSendMessageList = this.queryForList("YCSENDMESSAGESPECDAO.querySendMessageByContno",contno);
		return ycSendMessageList;
	}

	@Override
	public void updateStatusByContNo(YcSendMessage ycSendMessage) {
		this.updateObject("YCSENDMESSAGESPECDAO.updateStatusByContNo", ycSendMessage.getContno());
	}

}
