package com.sinosoft.surrender.surrconfirm.bo;

import com.sinosoft.surrender.db.model.YcSendMessage;

public interface SurrenderConfirmSendMessageBO {
	/**
	 * 
	 * 发送退保确认短信
	 *
	 * @history: 2018年6月7日
	 * @author: HASEE
	 * @param comfirmMessageDTO
	 */
	void sendConfirmMessage(YcSendMessage ycSendMessage) ;
}
