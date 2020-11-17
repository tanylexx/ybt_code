package com.sinosoft.surrender.cashvalue.bo.abs;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.sinosoft.surrender.cashvalue.bo.CalPolCashValueBO;
import com.sinosoft.surrender.cashvalue.bo.FillCashValueDetailDTOBO;
import com.sinosoft.surrender.cashvalue.dao.LcDutySpecDAO;
import com.sinosoft.surrender.cashvalue.dto.CalCashValueParamDTO;
import com.sinosoft.surrender.cashvalue.dto.CashValueDetailDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.common.constant.ENUM_EDOR_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_FEE_TYPE;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.db.model.LcDuty;

public abstract class AbsCalPolCashValueBO implements CalPolCashValueBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private LcDutySpecDAO lcDutySpecDAO;
	@Autowired
	private FillCashValueDetailDTOBO fillCashValueDetailDTOBO;

	@Override
	public CashValueDetailDTO calPolCashValueByCalType(SurrenderDTO surrenderDTO) {
		// 根据保单险种号码， 查询责任信息，循环责任
		String polNo = surrenderDTO.getLcPol().getPolno();
		ExceptionUtil.checkEmpty(polNo, "保单险种号码为空，请核实！");
		BigDecimal polCashValue = BigDecimal.ZERO;
		String feeType = "";
		if (ENUM_EDOR_TYPE.WT.getValue().equals(surrenderDTO.getEdorType())) {
			logger.info("此保单;{}犹豫期退保，取退费", polNo);
			polCashValue = surrenderDTO.getLcPol().getPrem();
			// 加上补退费类型
			feeType = ENUM_FEE_TYPE.TF.getValue();
			surrenderDTO.setFeeType(feeType);
			CashValueDetailDTO cashValueDetailDTO = fillCashValueDetailDTOBO.fillCashValueDetailDTO(polCashValue,
					surrenderDTO);
			return cashValueDetailDTO;
		}
		logger.info("此保单;{}退保试算!", polNo);
		List<LcDuty> lcDutyList = lcDutySpecDAO.getLcDutyList(polNo);
		ExceptionUtil.checkCollEmpty(lcDutyList, "此保单号无责任表信息，请核实！");
		for (LcDuty lcDuty : lcDutyList) {
			surrenderDTO.setLcDuty(lcDuty);
			// 1.填充计算要素
			CalCashValueParamDTO calCashValueParamDTO = fillCalCashValueParam(surrenderDTO);
			BigDecimal dutyCashValue = calDutyCashValue(calCashValueParamDTO);
			polCashValue = polCashValue.add(dutyCashValue);
		}
		CashValueDetailDTO cashValueDetailDTO = fillCashValueDetailDTOBO.fillCashValueDetailDTO(polCashValue,
				surrenderDTO);
		return cashValueDetailDTO;
	}

	/**
	 * 填充计算现价要素
	 * 
	 * @history: 2018-5-3 wangwl_sinosoft
	 * @param surrenderDTO
	 * @return
	 */
	protected abstract CalCashValueParamDTO fillCalCashValueParam(SurrenderDTO surrenderDTO);

	/**
	 * 
	 * 计算责任层级时刻现价
	 * 
	 * @history: 2018-4-13
	 * @author: wangwl_sinosoft
	 * @param calCashValueParamDTO
	 * @return
	 */
	protected abstract BigDecimal calDutyCashValue(CalCashValueParamDTO calCashValueParamDTO);

}
