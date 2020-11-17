package com.sinosoft.surrender.surrconfirm.bo.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.constant.ENUM_PROCESS_STATUS_CODE;
import com.sinosoft.surrender.db.dao.YcSendMessageDAO;
import com.sinosoft.surrender.db.dao.YcSurrenderDAO;
import com.sinosoft.surrender.db.model.YcSendMessage;
import com.sinosoft.surrender.db.model.YcSurrender;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderConfirmBO;
import com.sinosoft.surrender.surrconfirm.dao.YcSurrenderSpecDAO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

@Component
public class SurrenderConfirmBOImpl implements SurrenderConfirmBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private YcSurrenderDAO ycSurrenderDAO;
	@Autowired
	private YcSurrenderSpecDAO ycSurrenderSpecDAO;
	@Autowired
	private YcSendMessageDAO ycSendMessageDAO;
	

	@Override
	public void saveSurrender(SurrenderConfirmReqDTO surrenderConfirmReqDTO, BigDecimal policyCashValue,
			String edoracceptno) {
		// 封装数据
		YcSurrender ycSurrender = fillYcSurrender(surrenderConfirmReqDTO, policyCashValue, edoracceptno);
		// 保存退表中间表
		logger.debug("保存退表中间表");
		ycSurrenderDAO.insert(ycSurrender);
		YcSendMessage ycSendMessage = fillYcSendMessage(surrenderConfirmReqDTO);
		logger.debug("保存短信中间表");
		ycSendMessageDAO.insert(ycSendMessage);

	}

	@Override
	public YcSurrender findSurrenderByContNo(String contNo) {
		return ycSurrenderSpecDAO.findSurrenderByContNo(contNo);
	}

	@Override
	public void updateYcSurrenderByKey(YcSurrender ycSurrender) {
		ycSurrenderSpecDAO.updateYcSurrenderByKey(ycSurrender);
		
	}
	
	/**
	 * 
	 * 封装退保中间表的数据
	 * 
	 * @history: 2018年4月8日
	 * @author: zhongdw_sinosoft
	 * @param surrenderConfirmReqDTO
	 * @param policyCashValue
	 * @return
	 */

	private YcSurrender fillYcSurrender(SurrenderConfirmReqDTO surrenderConfirmReqDTO, BigDecimal policyCashValue,
			String edorAcceptNo) {
		YcSurrender ycSurrender = new YcSurrender();
		// 封装数据
		ycSurrender.setTransno(surrenderConfirmReqDTO.getTransNo());
		ycSurrender.setContno(surrenderConfirmReqDTO.getContNo());
		ycSurrender.setApplyPlatform(surrenderConfirmReqDTO.getApplyPlatform());
		ycSurrender.setEdortype(surrenderConfirmReqDTO.getEdorType());
		// 申请金额
		ycSurrender.setApplyPrem(policyCashValue);
		ycSurrender.setFunctionflag(surrenderConfirmReqDTO.getFunctionFlag());
		ycSurrender.setBankcode(surrenderConfirmReqDTO.getBankCode());
		ycSurrender.setSourcetype(surrenderConfirmReqDTO.getSourceType());
		ycSurrender.setConfirmDate(surrenderConfirmReqDTO.getConfirmDate());
		// 保全受理号
		ycSurrender.setEdoracceptno(edorAcceptNo);
		ycSurrender.setBankaccno(surrenderConfirmReqDTO.getBankAccNo());
		ycSurrender.setBankaccname(surrenderConfirmReqDTO.getBankAccName());
		ycSurrender.setProcessStatus(ENUM_PROCESS_STATUS_CODE.PENDING.getValue());
		ycSurrender.setCreatedUser(surrenderConfirmReqDTO.getApplyPlatform());
		ycSurrender.setCreatedDate(new Date());
		ycSurrender.setModifiedUser(surrenderConfirmReqDTO.getApplyPlatform());
		ycSurrender.setModifiedDate(new Date());
		ycSurrender.setIsDeleted("0");
		ycSurrender.setEdorprtno(surrenderConfirmReqDTO.getEdorPrtNo());
		return ycSurrender;
	}

	private YcSendMessage fillYcSendMessage(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		YcSendMessage ycSendMessage =new YcSendMessage();
		ycSendMessage.setContno(surrenderConfirmReqDTO.getContNo());
		ycSendMessage.setBankcode(surrenderConfirmReqDTO.getBankCode());
		ycSendMessage.setSourcetype(surrenderConfirmReqDTO.getSourceType());
		ycSendMessage.setEdortype(surrenderConfirmReqDTO.getEdorType());
		ycSendMessage.setApplyPlatform(surrenderConfirmReqDTO.getApplyPlatform());
		ycSendMessage.setProcessStatus(ENUM_PROCESS_STATUS_CODE.PENDING.getValue());
		ycSendMessage.setCreatedUser(surrenderConfirmReqDTO.getApplyPlatform());
		ycSendMessage.setModifiedUser(surrenderConfirmReqDTO.getApplyPlatform());
		ycSendMessage.setCreatedDate(new Date());
		ycSendMessage.setModifiedDate(new Date());
		ycSendMessage.setIsDeleted("0");
		return ycSendMessage;
		
	}

}
