package com.sinosoft.surrender.cashvalue.bo.impl;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.cashvalue.bo.SurrenderSaveDataBO;
import com.sinosoft.surrender.cashvalue.dao.YcPolicyCashValueSpecDAO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.db.dao.YcPolicyCashValueDAO;
import com.sinosoft.surrender.db.model.YcPolicyCashValue;

@Component
public class SurrenderSaveDataBOImpl implements SurrenderSaveDataBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private YcPolicyCashValueDAO ycPolicyCashValueDAO;
	@Autowired
	private YcPolicyCashValueSpecDAO ycPolicyCashValueSpecDAO;
	
	@Override
	public void saveData(BigDecimal policyCashValue, PolicyCashValueReqDTO policyCashValueReqDTO) {
		logger.info("保单为：{}，保存保单现价表", policyCashValueReqDTO.getContNo());
		saveYcPolicyCashValue(policyCashValue, policyCashValueReqDTO);
	}

	/**
	 * 
	 * 保存保单现价表
	 * 
	 * @history: 2018-3-21
	 * @author: wangwl_sinosoft
	 * @param policyCashValueReqDTO
	 */
	private void saveYcPolicyCashValue(BigDecimal policyCashValue, PolicyCashValueReqDTO policyCashValueReqDTO) {
		// 先删后插
		ycPolicyCashValueSpecDAO.updateYcPolicyCashValueByKey(policyCashValueReqDTO);
		YcPolicyCashValue ycPolicyCashValue = new YcPolicyCashValue();
		ycPolicyCashValue.setContno(policyCashValueReqDTO.getContNo());
		ycPolicyCashValue.setCashvalue(policyCashValue);
		ycPolicyCashValue.setApplyDate(policyCashValueReqDTO.getApplyDate());
		ycPolicyCashValue.setCalDate(policyCashValueReqDTO.getApplyDate());
		ycPolicyCashValue.setCreatedDate(DateUtil.getNowDate());
		ycPolicyCashValue.setModifiedDate(DateUtil.getNowDate());
		ycPolicyCashValue.setCreatedUser(policyCashValueReqDTO.getApalyPlatform());
		ycPolicyCashValue.setModifiedUser(policyCashValueReqDTO.getApalyPlatform());
		ycPolicyCashValue.setEdortype(policyCashValueReqDTO.getEdorType());
		ycPolicyCashValue.setIsDeleted(Constant.ZERO);
		ycPolicyCashValueDAO.insert(ycPolicyCashValue);
		
	}

}
