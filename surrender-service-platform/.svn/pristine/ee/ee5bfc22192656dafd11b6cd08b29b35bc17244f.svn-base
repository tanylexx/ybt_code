package com.sinosoft.surrender.surrconfirm.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.YcSendMessage;

public interface YcSendMessageSpecDAO {

	/**
	 * 	
	 * 查询短信未处理的保单
	 *
	 * @history: 2018年6月12日
	 * @author: wangwl_sinosoft
	 * @return
	 */
	List <YcSendMessage> getSendMessageInfo(); 
	
	/**
	 * 	
	 * 根据保单号修改短信发送状态 
	 *
	 * @history: 2018年6月12日
	 * @author: wangwl
	 * @param ycSendMessage
	 */
	void updateYcSendMessageByContNo(YcSendMessage ycSendMessage);
	/**
	 * 	
	 * 根据保单号查询该保单是否做过退保
	 *
	 * @history: 2020年3月20日
	 * @author: songjie
	 * @param contno
	 */
	List <YcSendMessage> queryMessageByContNo(String contno);
	/**
	 * 	
	 * 根据保单号删除已发送过的短信
	 *
	 * @history: 2020年3月20日
	 * @author: songjie
	 * @param ycSendMessage
	 */
	void updateStatusByContNo(YcSendMessage ycSendMessage);
}
