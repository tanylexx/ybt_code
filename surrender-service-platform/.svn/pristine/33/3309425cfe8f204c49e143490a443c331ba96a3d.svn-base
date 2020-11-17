package com.sinosoft.surrender.message.service.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.message.bo.MsgUrlDataSaveBO;
import com.sinosoft.surrender.message.bo.SurrenderSendMsgUrlDealBO;
import com.sinosoft.surrender.message.dao.SurrenderSendMsgUrlSpecDAO;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrReqlDTO;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrlResDTO;
import com.sinosoft.surrender.message.service.SurrenderSendMsgUrlService;
import com.sinosoft.surrender.surrconfirm.service.SurrenderTraceService;

@Service
public class SurrenderSendMsgUrlServiceImpl implements SurrenderSendMsgUrlService {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LockBO lockBO;
	@Autowired
	private SurrenderSendMsgUrlDealBO surrenderSendMsgUrlDealBO;
	@Autowired
	private SurrenderTraceService surrenderTraceService;
	@Autowired
	private MsgUrlDataSaveBO msgUrlDataSaveBO;
	@Autowired
	private SurrenderSendMsgUrlSpecDAO surrenderSendMsgUrlSpecDAO;
	
	@Override
	public void sendMsgUrl(String contNo) {
		long startTime = System.currentTimeMillis();
		// 加锁
		String lockType = Constant.SURRENDERSHORTURLLOCKTYPE;
		String loker = Constant.SURRENDERSHORTURLLOCKER;
		StringBuffer sbf = new StringBuffer();
		sbf.append(contNo);
		String key = sbf.toString();
		logger.debug("====>加锁中...<====");
		try {
			lockBO.lock(lockType, key, loker);
		} catch (LockFailedException e1) {
			logger.error("加锁失败：{}", e1);
			return;
		}
		BigDecimal surrenderTraceSid = BigDecimal.ZERO;
		SurrenderSendMsgUrlResDTO surrenderSendMsgUrlResDTO = new SurrenderSendMsgUrlResDTO();
		try {
			// 填充请求参数
			SurrenderSendMsgUrReqlDTO surrenderSendMsgUrReqlDTO = fillAndCheckMsgUrReqlDTO(contNo);
			// 保存日志
			surrenderTraceSid = surrenderTraceService.saveSurrenderTrace(surrenderSendMsgUrReqlDTO);
			// 调用接口来发送短链接
			surrenderSendMsgUrlResDTO = surrenderSendMsgUrlDealBO.sendMsgUrl(surrenderSendMsgUrReqlDTO);
			msgUrlDataSaveBO.saveYcSendMessage(surrenderSendMsgUrReqlDTO);
		} catch (Exception rce) {
			logger.error("发送短链接失败，失败原因：{}", rce);
			surrenderSendMsgUrlResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			String resultMsg = rce.getMessage();
			if (resultMsg.length() > 500) {
				resultMsg = resultMsg.substring(0, 500);
			}
			surrenderSendMsgUrlResDTO.setResultMsg(resultMsg);
		} finally {
			try {
				surrenderTraceService.updateSurrenderTrace(surrenderSendMsgUrlResDTO, surrenderTraceSid, startTime);
			} catch (Exception e1) {
				logger.error("退保发送短链接更新日志出现非预期异常:{}", e1);
			}
			try {
				// 解锁
				logger.info("====>解锁...<====");
				lockBO.unLock(lockType, key);
			} catch (Exception e) {
				logger.error("退保发送短信链接出现非预期异常:{}", e);
			}
		}
		logger.info("此保单号为：{}，退保发送短信链接耗时：{}毫秒", contNo, System.currentTimeMillis()-startTime);
	}

	/**
	 * 	
	 * 填充退保短信请求DTO 
	 *
	 * @history: 2019年12月10日
	 * @author: HASEE
	 * @param contNo
	 * @return
	 */
	private SurrenderSendMsgUrReqlDTO fillAndCheckMsgUrReqlDTO(String contNo) {
		SurrenderSendMsgUrReqlDTO surrenderSendMsgUrReqlDTO = surrenderSendMsgUrlSpecDAO.getSurrenderMsgUrlParam(contNo);
		ExceptionUtil.checkNull(surrenderSendMsgUrReqlDTO, "查询参数为空！");
		StringBuffer sbf =new StringBuffer();
		sbf.append("YBTMSG_");
		sbf.append(DateUtil.getDateStr(new Date(), "yyyyMMddhhmmss"));
		surrenderSendMsgUrReqlDTO.setTransNo(sbf.toString());
		surrenderSendMsgUrReqlDTO.setConfirmDate(DateUtil.getCurrentDate());
		//核心短信链接交易编码
		surrenderSendMsgUrReqlDTO.setFunctionFlag("63");
		surrenderSendMsgUrReqlDTO.setEdorAppDate(DateUtil.getCurrentDateForSurrenderMsgUrl());
		surrenderSendMsgUrReqlDTO.setEdorAppTime(DateUtil.getCurrentTime()); 
		String appntPhone = surrenderSendMsgUrlSpecDAO.queryAppntPhone(surrenderSendMsgUrReqlDTO.getContNo());
		surrenderSendMsgUrReqlDTO.setPhone(appntPhone);
		surrenderSendMsgUrReqlDTO.setApplyPlatform("YBTMSG");
		return surrenderSendMsgUrReqlDTO;
	}
	
}
