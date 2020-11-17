package com.sinosoft.surrender.policyimport.service.impl;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.RulesCheckedException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.policyimport.bo.PolicyImportDealBO;
import com.sinosoft.surrender.policyimport.dto.req.PolicyApplyStateReviseReqDTO;
import com.sinosoft.surrender.policyimport.dto.req.QuerySurrenderReqDTO;
import com.sinosoft.surrender.policyimport.dto.res.PolicyApplyStateReviseResDTO;
import com.sinosoft.surrender.policyimport.dto.res.QuerySurrenderResDTO;
import com.sinosoft.surrender.policyimport.service.PolicyImportDealService;

/**
 * 
 * 退保导入接口
 *
 * @author:  zhongdw_sinosoft
 * @date: 2018年4月4日-下午5:00:22
 * @version:
 */
@Service
public class PolicyImportDealServiceImpl implements PolicyImportDealService{
	private  Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private PolicyImportDealBO policyImportDealBO;
	@Autowired
	private LockBO lockBO;
	
	@Override
	public PolicyApplyStateReviseResDTO revisePolicyApplyState(PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO) {
		String lockType = Constant.POLICYIMPORTDEALLOCKTYPE;
		String loker = Constant.POLICYIMPORTDEALLOCKER;
		StringBuffer sbf = new StringBuffer();
		sbf.append(policyApplyStateReviseReqDTO.getContNo());
		sbf.append("|");
		sbf.append(policyApplyStateReviseReqDTO.getTransNo());
		String key = sbf.toString();
		// 加锁，
		logger.info("====>加锁中...<====");
		try {
			lockBO.lock(lockType, key, loker);
		} catch (Exception e1) {
			logger.error("加锁异常:{}",e1);
		}
		
		//创建返回参数对象
		PolicyApplyStateReviseResDTO policyApplyStateReviseResDTO = new PolicyApplyStateReviseResDTO();
		try {
			//参数非空校验
			logger.info("====>参数非空校验...<====");
			revisePolicyApplyParamCheck(policyApplyStateReviseReqDTO);
			
			//状态修改
			logger.info("保单号：{}，状态修改",policyApplyStateReviseReqDTO.getContNo());
			//根据保单号修改受理状态
			policyImportDealBO.modifyProcessingState(policyApplyStateReviseReqDTO);
			
			//修改成功
			logger.info("保单号：{}，状态修改成功",policyApplyStateReviseReqDTO.getContNo());
			policyApplyStateReviseResDTO.setResultStatus(ENUM_TRACE_STATUS.SUCCESS.getValue());
			policyApplyStateReviseResDTO.setResultMsg(ENUM_TRACE_STATUS.SUCCESS.getName());
			
		} catch (RulesCheckedException e) {
			//修改失败，规则类校验
			logger.error("保单号：{}，状态修改失败:{}",policyApplyStateReviseReqDTO.getContNo(),e);
			policyApplyStateReviseResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			policyApplyStateReviseResDTO.setResultMsg(e.getMessage());
		} catch (Exception e) {
			//修改失败，非预期检查
			logger.error("保单号：{}，状态修改失败:{}",policyApplyStateReviseReqDTO.getContNo(),e);
			policyApplyStateReviseResDTO.setResultStatus(ENUM_TRACE_STATUS.FAIL.getValue());
			policyApplyStateReviseResDTO.setResultMsg(e.getMessage());
		}finally {
			//解锁
			logger.info("解锁");
			try {
				lockBO.unLock(lockType, key);
			} catch (Exception e) {
				logger.error("解锁异常:{}",e);
			}
		}
		return policyApplyStateReviseResDTO;
	}

	@Override
	public List<QuerySurrenderResDTO> querySurrender(QuerySurrenderReqDTO querySurrenderReqDTO) {
		
		return policyImportDealBO.querySurrender(querySurrenderReqDTO);
	
	}
	
	/**
	 * 	
	 *  理状态修改接口参数非空校验  
	 *
	 * @history: 2018年3月26日
	 * @author: zhongdw_sinosoft
	 * @param policyApplyStateReviseReqDTO
	 */
	public void revisePolicyApplyParamCheck(PolicyApplyStateReviseReqDTO policyApplyStateReviseReqDTO){
		ExceptionUtil.checkEmpty(policyApplyStateReviseReqDTO.getContNo(),"保单号不能为空！");
		//ExceptionUtil.checkNotEmpty(policyApplyStateReviseReqDTO.getTransNo(),"流水号不能为空！");
		ExceptionUtil.checkEmpty(policyApplyStateReviseReqDTO.getAcceptStatus(),"修改状态不能为空！");
	}
}
