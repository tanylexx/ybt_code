package com.sinosoft.surrender.surrconfirm.bo.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.dao.YcPolicyCashValueSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.cashvalue.service.CalPolicyCashValueService;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.surrconfirm.bo.GetSurrenderMoneyBO;
import com.sinosoft.surrender.surrconfirm.dto.req.SurrenderConfirmReqDTO;

@Component
public class GetSurrenderMoneyBOImpl implements GetSurrenderMoneyBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private YcPolicyCashValueSpecDAO ycPolicyCashValueSpecDAO;
	@Autowired
	private CalPolicyCashValueService calPolicyCashValueService;

	@Override
	public BigDecimal getSurrenderMoney(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		BigDecimal policyCashValue = BigDecimal.ZERO;
		// 查询
		policyCashValue = ycPolicyCashValueSpecDAO.findYcPolicyCashValueToConfirm(surrenderConfirmReqDTO);
		if (policyCashValue != null) {
			// 账户价值不为空，返回
			return policyCashValue;
		}
		// 重新计算
		logger.debug("====>未查到退保金額，重新计算...<====");
		// 封装数据，计算申请金额
		PolicyCashValueReqDTO policyCashValueReqDTO = fillPolicyCashValueReqDTO(surrenderConfirmReqDTO);
		// 计算申请金额
		logger.debug("====>计算退保金額...====");
		PolicyCashValueResDTO policyCashValueResDTO = calPolicyCashValueService.calPolicyCashValue(policyCashValueReqDTO);
		ExceptionUtil.checkNull(policyCashValueResDTO, "计算退保金額返回DTO为空！");
		// 判断是否计算成功
		if(ENUM_TRACE_STATUS.FAIL.getValue().equals(policyCashValueResDTO.getResultStatus())){
			throw new RuntimeException(policyCashValueResDTO.getResultMsg());
		}
		policyCashValue = policyCashValueResDTO.getCashValue();
		return policyCashValue;
	}

	/**
	 * 封装重新计算需要的数据
	 * 
	 * @param surrenderConfirmReqDTO
	 * @return
	 */
	public PolicyCashValueReqDTO fillPolicyCashValueReqDTO(SurrenderConfirmReqDTO surrenderConfirmReqDTO) {
		// 创建返回参数对象
		PolicyCashValueReqDTO policyCashValueReqDTO = new PolicyCashValueReqDTO();
		// 封装数据
		policyCashValueReqDTO.setTransNo(surrenderConfirmReqDTO.getTransNo());
		policyCashValueReqDTO.setZoneNo(surrenderConfirmReqDTO.getZoneNo());
		policyCashValueReqDTO.setBrNo(surrenderConfirmReqDTO.getBrNo());
		policyCashValueReqDTO.setTellerNo(surrenderConfirmReqDTO.getTellerNo());
		policyCashValueReqDTO.setBankCode(surrenderConfirmReqDTO.getBankCode());
		policyCashValueReqDTO.setSourceType(surrenderConfirmReqDTO.getSourceType());
		// 退保查询
		policyCashValueReqDTO.setFunctionFlag("26");
		policyCashValueReqDTO.setContNo(surrenderConfirmReqDTO.getContNo());
		policyCashValueReqDTO.setEdorType(surrenderConfirmReqDTO.getEdorType());
		policyCashValueReqDTO.setApplyDate(surrenderConfirmReqDTO.getConfirmDate());
		policyCashValueReqDTO.setApalyPlatform(surrenderConfirmReqDTO.getApplyPlatform());
		// 返回参数
		return policyCashValueReqDTO;
	}
}
