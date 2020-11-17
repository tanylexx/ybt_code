package com.sinosoft.surrender.policyimport.bo.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.constant.ENUM_PROCESS_STATUS_CODE;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.LockFailedException;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.lock.bo.LockBO;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.BackupXmlUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.CheckFailureUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.DtoToCoreXml;
import com.sinosoft.surrender.policyimport.batch.importUtils.ImportCoreUtil;
import com.sinosoft.surrender.policyimport.bo.SurrenderImportCoreBO;
import com.sinosoft.surrender.policyimport.dao.PolicyImportDealDAO;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

@Component
public class SurrenderImportCoreBOImpl implements SurrenderImportCoreBO {
	@Autowired
	private PolicyImportDealDAO policyImportDealDAO;
	@Autowired
	private LockBO lockBO;
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	public void doGiderCoreBatch(SurrenderImportCoreDTO surrenderImportCoreDTO) {
		long importStartTime = System.currentTimeMillis();
		//加锁
		String lockType = Constant.SURRENDERIMPORTCORELOCKTYPE;
		String loker = Constant.SURRENDERIMPORTCORELOCKER;
		StringBuffer sbf = new StringBuffer();
		sbf.append(surrenderImportCoreDTO.getContNo());
		String key = sbf.toString();
		logger.debug("====>加锁中...<====");
		try {
			lockBO.lock(lockType, key, loker);
		} catch (LockFailedException e1) {
			logger.error("加锁失败：{}", e1);
			return;
		}
		Map<String, Object> modifyField = new HashMap<String, Object>();
		try {
			// DTO转成核心报文XML
			logger.debug("====>DTO转成xml报文...<====");
			String reqXml = DtoToCoreXml.dtoToXml(surrenderImportCoreDTO);
			logger.info("导核心报文：{}", reqXml);
			// 保存导入核心报文XML
			logger.debug("====>保存xml报文...<====");
			BackupXmlUtil.backupXML(surrenderImportCoreDTO, reqXml, "_req.xml");
			// 获得servlet的URL
			Object address = ConfigInfo.getContextProperty("ybtCoreAddress");
			// 校验
			ExceptionUtil.checkNull(address, "获取的核心servlet地址为空！");
			String conURL = (String) address;
			// 将xml通过流的形式导入核心，得到核心返回的报文xml
			logger.debug("====>导入核心...<====");
			String resXml = ImportCoreUtil.importCore(reqXml, conURL);
			logger.info("核心返回的报文：{}", resXml);
			// 保存核心返回的报文xml
			logger.debug("====>保存返回报文...<====");
			BackupXmlUtil.backupXML(surrenderImportCoreDTO, resXml, "_res.xml");
			// 检查报文xml，是否是错误结果格式，
			logger.debug("====>检查报文xml，是否是错误结果格式..<====");
			Map<String, String> failureMap = CheckFailureUtil.checkFailure(resXml);
			String rescode = failureMap.get("respcode");
			String resdesc = failureMap.get("respdesc");
			// 封装部分属性
			modifyField.put("transNo", surrenderImportCoreDTO.getTransNo());
			modifyField.put("contNo", surrenderImportCoreDTO.getContNo());
			// 判断返回的报文是否是错误结果
			if (StringUtils.isNotBlank(rescode) && !Constant.ONE.equals(rescode)) {
				// 导核心失败
				logger.info("====>导核心失败...<====");
				modifyField.put("processStatus", ENUM_PROCESS_STATUS_CODE.FAIL.getValue());
				if (resdesc.length() >= 500) {
					resdesc = resdesc.substring(0, 500);
				}
				modifyField.put("processResult", resdesc);
			} else {
				// 导入成功
				logger.info("====>导入成功...<====");
				modifyField.put("processStatus", ENUM_PROCESS_STATUS_CODE.SUCESS.getValue());
				modifyField.put("processResult", ENUM_PROCESS_STATUS_CODE.SUCESS.getName());
			}
			
		} catch (TradeException e) {
			logger.error("导入失败：{}",e);
			modifyField.put("processStatus", ENUM_TRACE_STATUS.FAIL.getValue());
			String message = e.getMessage();
			if (message != null && message.length() >= 500) {
				message = message.substring(0, 500);
			}
			modifyField.put("processResult", message);
		} catch (Exception e) {
			logger.error("导入失败,发生非预期异常：{}",e);
			modifyField.put("processStatus", ENUM_TRACE_STATUS.FAIL.getValue());
			String message = e.getMessage();
			if (message != null && message.length() >= 500) {
				message = message.substring(0, 500);
			}
			modifyField.put("processResult", message);

		} finally {
			try {
				long importEndTime = System.currentTimeMillis();
				logger.debug("====>修改处理状态...<====");
				modifyField.put("processCost", BigDecimal.valueOf(importEndTime - importStartTime));
				// 解锁
				logger.info("====>解锁...<====");
				lockBO.unLock(lockType, key);
				policyImportDealDAO.modifyProcessingStateImportToCore(modifyField);
				logger.info("此保单号为：{}，导核心耗时：{}毫秒", surrenderImportCoreDTO.getContNo(),(importEndTime - importStartTime));
			} catch (Exception e) {
				logger.error("导入失败,发生非预期异常：{}",e);
			}

		}

	}
}
