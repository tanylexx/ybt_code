package com.sinosoft.surrender.surrconfirm.bo.impl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.YkSurrenderTraceDAO;
import com.sinosoft.surrender.db.model.YkSurrenderTrace;
import com.sinosoft.surrender.surrconfirm.bo.SurrenderTraceBO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;
import com.sinosoft.surrender.surrconfirm.dto.res.SurrenderConfirmResDTO;

@Component
public class SurrenderTraceBOImpl implements SurrenderTraceBO {
	@Autowired
	private YkSurrenderTraceDAO ykSurrenderTraceDAO;
	@Override
	public BigDecimal saveSurrenderTrace(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		//封装日志记录Model的部分属性
		YkSurrenderTrace ykSurrenderTrace = fillykSurrenderTrace(surrenderConfirmReqDTO);
		
		return ykSurrenderTraceDAO.insertSelective(ykSurrenderTrace);
	}

	@Override
	public void updateSurrenderTrace(SurrenderConfirmResDTO surrenderConfirmResDTO , BigDecimal surrenderTraceSid,long confirmStartTime) {
		YkSurrenderTrace ykSurrenderTrace = new YkSurrenderTrace();
		ykSurrenderTrace.setSid(surrenderTraceSid);
		ykSurrenderTrace.setTransStatus(surrenderConfirmResDTO.getResultStatus());
		ykSurrenderTrace.setDesr(surrenderConfirmResDTO.getResultMsg());
		long confirmEndTime = System.currentTimeMillis();
		ykSurrenderTrace.setRocessCost(BigDecimal.valueOf(confirmEndTime-confirmStartTime));
		ykSurrenderTraceDAO.updateByPrimaryKeySelective(ykSurrenderTrace);
	}
	
	/**
	 * 封装交易日志Model
	 * @param surrenderConfirmReqDTO
	 * @return
	 */
	private YkSurrenderTrace fillykSurrenderTrace(SurrenderConfirmReqDTO surrenderConfirmReqDTO){
		//创建对象
		YkSurrenderTrace ykSurrenderTrace = new YkSurrenderTrace();
		//封装属性
		ykSurrenderTrace.setTransno(surrenderConfirmReqDTO.getTransNo());
		ykSurrenderTrace.setTransdate(surrenderConfirmReqDTO.getConfirmDate());
		ykSurrenderTrace.setTranstime(DateUtil.getCurrentTime());
		ykSurrenderTrace.setTellerno(surrenderConfirmReqDTO.getTellerNo());
		ykSurrenderTrace.setZoneno(surrenderConfirmReqDTO.getZoneNo());
		ykSurrenderTrace.setBrno(surrenderConfirmReqDTO.getBrNo());
		ykSurrenderTrace.setBankcode(surrenderConfirmReqDTO.getBankCode());
		ykSurrenderTrace.setSourcetype(surrenderConfirmReqDTO.getSourceType());
		ykSurrenderTrace.setContno(surrenderConfirmReqDTO.getContNo());
		ykSurrenderTrace.setFunctionflag(surrenderConfirmReqDTO.getFunctionFlag());
		ykSurrenderTrace.setCreatedDate(new Date());
		ykSurrenderTrace.setCreatedUser(surrenderConfirmReqDTO.getApplyPlatform());
		ykSurrenderTrace.setModifiedUser(surrenderConfirmReqDTO.getApplyPlatform());
		ykSurrenderTrace.setModifiedDate(new Date());
		ykSurrenderTrace.setIsDeleted("0");
		ykSurrenderTrace.setTransStatus("00");
		
		//返回参数
		return ykSurrenderTrace;
	}

}
