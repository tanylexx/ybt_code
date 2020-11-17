package com.sinosoft.surrender.cashvalue.bo.impl;

import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.abs.AbsCalPolicyYearEndAlivePayBO;
import com.sinosoft.surrender.cashvalue.dto.AlgorithElementDTO;
import com.sinosoft.surrender.cashvalue.dto.SurrenderDTO;
import com.sinosoft.surrender.db.model.LcPol;

@Component("calPolicyYearEndAlivePayCommonBOImpl")
public class CalPolicyYearEndAlivePayCommonBOImpl extends AbsCalPolicyYearEndAlivePayBO {

	@Override
	protected AlgorithElementDTO fillAlgorithmElementDTO(SurrenderDTO surrenderDTO) {
		AlgorithElementDTO algorithElementDTO = new AlgorithElementDTO();
		LcPol lcPol = surrenderDTO.getLcPol();
		// 目前只针对3159和3160 填充计算要素，
		algorithElementDTO.setSumPrem(lcPol.getSumprem());
		algorithElementDTO.setPolNo(lcPol.getPolno());
		return algorithElementDTO;
	}
}
