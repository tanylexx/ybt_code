package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.SurrenderTradeTraceBO;
import com.sinosoft.surrender.cashvalue.dao.SurrenderTraceSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_YES_NO_STATUS;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.YkSurrenderTraceDAO;
import com.sinosoft.surrender.db.model.YkSurrenderTrace;

@Component
public class SurrenderTradeTraceBOImpl implements SurrenderTradeTraceBO {
	@Autowired
	private YkSurrenderTraceDAO ykSurrenderTraceDAO;
	@Resource(name = "surrenderTraceSpecDAOImpl")
	private SurrenderTraceSpecDAO ykSurrenderTraceSpecDAO;
	@Override
	public BigDecimal createTradeTrace(PolicyCashValueReqDTO policyCashValueReqDTO) {
		YkSurrenderTrace ykSurrenderTrace = new YkSurrenderTrace();
		ykSurrenderTrace.setTransno(policyCashValueReqDTO.getTransNo());
		ykSurrenderTrace.setTransdate(policyCashValueReqDTO.getApplyDate());
		ykSurrenderTrace.setTranstime(policyCashValueReqDTO.getApplyTime());
		ykSurrenderTrace.setBankcode(policyCashValueReqDTO.getBankCode());
		ykSurrenderTrace.setBrno(policyCashValueReqDTO.getBrNo());
		ykSurrenderTrace.setZoneno(policyCashValueReqDTO.getZoneNo());
		ykSurrenderTrace.setFunctionflag(policyCashValueReqDTO.getFunctionFlag());
		ykSurrenderTrace.setTellerno(policyCashValueReqDTO.getTellerNo());
		ykSurrenderTrace.setContno(policyCashValueReqDTO.getContNo());
		ykSurrenderTrace.setSourcetype(policyCashValueReqDTO.getSourceType());
		ykSurrenderTrace.setTransStatus(ENUM_TRACE_STATUS.INIT.getValue());
		ykSurrenderTrace.setCreatedDate(DateUtil.getNowDate());
		ykSurrenderTrace.setModifiedDate(DateUtil.getNowDate());
		ykSurrenderTrace.setCreatedUser(policyCashValueReqDTO.getApalyPlatform());
		ykSurrenderTrace.setModifiedUser(policyCashValueReqDTO.getApalyPlatform());
		ykSurrenderTrace.setIsDeleted(ENUM_YES_NO_STATUS.NO.getValue());
		return ykSurrenderTraceDAO.insert(ykSurrenderTrace);
	}

	@Override
	public void updateTradeTrace(BigDecimal sid, long currentTime,PolicyCashValueResDTO policyCashValueResDTO) {
		if (BigDecimal.ZERO.compareTo(sid) == 0) {
			return;
		}

		YkSurrenderTrace ykSurrenderTrace = new YkSurrenderTrace();
		ykSurrenderTrace.setSid(sid);
		ykSurrenderTrace.setCashvalue(policyCashValueResDTO.getCashValue());
		ykSurrenderTrace.setTransStatus(policyCashValueResDTO.getResultStatus());
		ykSurrenderTrace.setDesr(policyCashValueResDTO.getResultMsg());
		long endTime = System.currentTimeMillis();
		ykSurrenderTrace.setRocessCost(BigDecimal.valueOf(endTime-currentTime));  
		ykSurrenderTraceDAO.updateByPrimaryKeySelective(ykSurrenderTrace);
	}

	@Override
	public List<YkSurrenderTrace> getYkSurrenderTraceInfo(PolicyCashValueReqDTO policyCashValueReqDTO) {
		List<YkSurrenderTrace> ykSurrenderTraceInfo = ykSurrenderTraceSpecDAO.getYkSurrenderTraceInfo(policyCashValueReqDTO);
		return ykSurrenderTraceInfo;
	}
}
