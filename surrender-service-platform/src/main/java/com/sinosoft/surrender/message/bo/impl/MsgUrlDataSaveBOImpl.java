package com.sinosoft.surrender.message.bo.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.db.dao.YcSendMessageDAO;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.message.bo.MsgUrlDataSaveBO;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrReqlDTO;
@Component
public class MsgUrlDataSaveBOImpl implements MsgUrlDataSaveBO{
     @Autowired 
     private YcSendMessageDAO ycSendMessageDAO;
	@Override
	public YcSendMessage saveYcSendMessage(SurrenderSendMsgUrReqlDTO surrenderSendMsgUrReqlDTO) {
		YcSendMessage ycSendMessage = new YcSendMessage();
		ycSendMessage.setContno(surrenderSendMsgUrReqlDTO.getContNo());
		ycSendMessage.setApplyPlatform(Constant.YBTMSGBATCH);
		ycSendMessage.setEdortype(surrenderSendMsgUrReqlDTO.getEdorType());
		ycSendMessage.setBankcode(surrenderSendMsgUrReqlDTO.getBankCode());
		ycSendMessage.setSourcetype(surrenderSendMsgUrReqlDTO.getSourceType());
		ycSendMessage.setProcessResult("调用核心链接处理成功！");
		ycSendMessage.setIsDeleted("0");
		ycSendMessage.setModifiedUser(Constant.YBTMSGBATCH);
		ycSendMessage.setModifiedDate(new Date());
		ycSendMessage.setCreatedUser(Constant.YBTMSGBATCH);
		ycSendMessage.setCreatedDate(new Date());
		ycSendMessageDAO.insert(ycSendMessage);
		return ycSendMessage;
	}
}
