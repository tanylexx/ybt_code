package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.CalAccountValueBO;
import com.sinosoft.surrender.cashvalue.bo.CalPolCashValueBO;
import com.sinosoft.surrender.cashvalue.bo.CalPolicyCashValueBO;
import com.sinosoft.surrender.cashvalue.dao.LcPolSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.CashValueDetailDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.common.util.BaseBeanFactory;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.model.LcPol;

@Component
public class CalPolicyCashValueBOImpl implements CalPolicyCashValueBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private BaseBeanFactory beanFactory;
	@Autowired
	private LcPolSpecDAO lcPolSpecDAO;
	@Autowired
	private CalAccountValueBO calAccountValueBO;

	@Override
	public PolicyCashValueResDTO calConstantlyPolicyCashValue(PolicyCashValueReqDTO policyCashValueReqDTO) {
		// 1.根据险种编码和保单号，查询险种信息 ，循环计算每个险种对应现价
		long currentTime = System.currentTimeMillis();
		List<LcPol> lcPolList = lcPolSpecDAO.getLcPolByContNo(policyCashValueReqDTO.getContNo());
		ExceptionUtil.checkCollEmpty(lcPolList, "此保单号无险种表信息，请核实！");
		BigDecimal cashValue = BigDecimal.ZERO;
		List<CashValueDetailDTO> calCashValueParamList = new ArrayList<CashValueDetailDTO>();
		for (LcPol lcPol : lcPolList) {
			// 2.根据险种的计算BO类。
			ExceptionUtil.checkEmpty(lcPol.getRiskcode(), "险种编码为空,请核实!");
			// modify by jinwc lisbug-6580-邮储犹退查询和犹退确认交易流程改变
			CalPolCashValueBO calCashValueBO = beanFactory.createCalCashValueBo(lcPol.getRiskcode(), policyCashValueReqDTO.getEdorType());
			ExceptionUtil.checkNull(calCashValueBO, "未生成对应的计算类，请核实！");
			logger.info("险种编码为：{},对应的计算类为：{}", lcPol.getRiskcode(), calCashValueBO.getClass());
			SurrenderDTO surrenderDTO = new SurrenderDTO();
			surrenderDTO.setLcPol(lcPol);
			surrenderDTO.setContNo(policyCashValueReqDTO.getContNo());
			surrenderDTO.setEdorValiDate(policyCashValueReqDTO.getApplyDate());
			surrenderDTO.setcValiDate(lcPol.getCvalidate());
			surrenderDTO.setEdorType(policyCashValueReqDTO.getEdorType());
			CashValueDetailDTO cashValueDetailDTO = calCashValueBO.calPolCashValueByCalType(surrenderDTO);
			ExceptionUtil.checkNull(cashValueDetailDTO, "计算现价明细有误请核实，请核实！");
			calCashValueParamList.add(cashValueDetailDTO);
			BigDecimal accountValue = calAccountValueBO.calAccountValue(surrenderDTO);
			cashValue = cashValue.add(cashValueDetailDTO.getMoney().add(accountValue));
		}
		cashValue = cashValue.setScale(2, BigDecimal.ROUND_HALF_UP);
		// 填充 返回参数
		PolicyCashValueResDTO policyCashValueResDTO = fillPolicyCashValueResDTO(calCashValueParamList, cashValue,
				policyCashValueReqDTO);
		logger.info("流水号为：{},保单号为：{},时刻现价为：{},耗时：{}毫秒", policyCashValueReqDTO.getTransNo(),
				policyCashValueReqDTO.getContNo(), cashValue, System.currentTimeMillis() - currentTime);
		return policyCashValueResDTO;
	}

	/**
	 * 
	 * 填充返回参数
	 * 
	 * @history: 2018-4-7
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamList
	 * @param cashValue
	 * @param policyCashValueReqDTO
	 * @return
	 */
	private PolicyCashValueResDTO fillPolicyCashValueResDTO(List<CashValueDetailDTO> calCashValueParamList,
			BigDecimal cashValue, PolicyCashValueReqDTO policyCashValueReqDTO) {
		PolicyCashValueResDTO policyCashValueResDTO = new PolicyCashValueResDTO();
		policyCashValueResDTO.setCalCashValueParamList(calCashValueParamList);
		policyCashValueResDTO.setTransNo(policyCashValueReqDTO.getTransNo());
		policyCashValueResDTO.setContNo(policyCashValueReqDTO.getContNo());
		policyCashValueResDTO.setCashValue(cashValue);
		return policyCashValueResDTO;
	}

}
