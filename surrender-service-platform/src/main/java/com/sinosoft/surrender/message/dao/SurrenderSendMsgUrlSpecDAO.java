package com.sinosoft.surrender.message.dao;

import java.util.List;

import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrReqlDTO;
/**
 * 
 * 短信链接发送处理DAO
 *
 * @author:  HASEE
 * @date: 2019年12月10日-上午11:55:04
 * @version:
 */
public interface SurrenderSendMsgUrlSpecDAO {
	/**
	 * 	
	 * 获取发送短信链接的保单号
	 *
	 * @history: 2019年12月10日
	 * @author: yangyb
	 * @return
	 */
	List<String> getSendMsgUrlOfContNoList();
	
	/**
	 * 	
	 * 根据保单号获取投保人手机号 
	 *
	 * @history: 2019年12月10日
	 * @author: HASEE
	 * @param surrenderSendShortUrlDTO
	 * @return
	 */
	public String queryAppntPhone(String contNo);
	
	/**
	 * 	
	 *  获取发送退保短信链接相关参数
	 *
	 * @history: 2019年12月10日
	 * @author: HASEE
	 * @param contno
	 * @return
	 */
	SurrenderSendMsgUrReqlDTO getSurrenderMsgUrlParam(String contno);
	/**
	 * 	
	 *根据保单号查询是否发送过带链接退保短信
	 *
	 * @history: 2020年03月20日
	 * @author: songjie
	 * @param contno 
	 * @return
	 */
	List<String> queryMessageUrlByContNo(String contno);
	/**
	 * 	
	 *根据保单号删除发送过带链接退保短信
	 *
	 * @history: 2020年03月20日
	 * @author: songjie
	 * @return
	 */
	void updateStatusByContNo(YcSendMessage ycSendMessage);


}
