package com.sinosoft.surrender.cashvalue.bo.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sinosoft.surrender.cashvalue.bo.CallCoreCalCashValueBO;
import com.sinosoft.surrender.cashvalue.bo.CashValueDTOtoXMLBO;
import com.sinosoft.surrender.cashvalue.dto.req.PolicyCashValueReqDTO;
import com.sinosoft.surrender.cashvalue.dto.res.PolicyCashValueResDTO;
import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.FailurePatternConstant;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.BackupXmlUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.CheckFailureUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.ImportCoreUtil;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;


@Component
public class CallCoreCalCashValueBOImpl implements CallCoreCalCashValueBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Autowired
	private CashValueDTOtoXMLBO cashValueDTOtoXMLBO;

	@Override
	public PolicyCashValueResDTO calCashValue(PolicyCashValueReqDTO policyCashValueReqDTO) {
		PolicyCashValueResDTO policyCashValueResDTO = fillPolicyCashValueResDTO(policyCashValueReqDTO);
		try {
			SurrenderImportCoreDTO surrenderImportCoreDTO = fillSurrenderImportCoreDTO(policyCashValueReqDTO);

			// DTO转XML
			String reqXml = cashValueDTOtoXMLBO.dealDTOTOXML(policyCashValueReqDTO);
			BackupXmlUtil.backupXML(surrenderImportCoreDTO, reqXml, "_试算req.xml");

			// 获得servlet的URL
			Object address = ConfigInfo.getContextProperty("ybtCoreAddress");
			ExceptionUtil.checkNull(address, "获取的核心servlet地址为空！");
			String conURL = (String) address;

			// 调用核心Servlet
			String resXml = ImportCoreUtil.importCore(reqXml, conURL);
			logger.info("核心返回的报文：{}", resXml);
			// 保存核心返回的报文xml
			BackupXmlUtil.backupXML(surrenderImportCoreDTO, resXml, "_试算res.xml");

			// 检查报文xml，是否是错误结果格式，
			logger.debug("====>检查报文xml，是否是错误结果格式..<====");
			Map<String, String> failureMap = CheckFailureUtil.checkFailure(resXml);
			String rescode = failureMap.get("respcode");
			String resdesc = failureMap.get("respdesc");
			
			// 判断返回的报文是否是错误结果
			if (StringUtils.isNotBlank(rescode) && !Constant.ONE.equals(rescode)) {
				// 核心试算失败
				logger.info("====>试算失败...<====");
				throw new TradeException(resdesc);
			} else {
				// 试算成功
				logger.info("====>试算成功...<====");
				Document doc = DocumentHelper.parseText(resXml);
				String GetMoneyXPath = FailurePatternConstant.GETMONEY_PATH;
				String getMoney = CheckFailureUtil.getNodeValue(doc, GetMoneyXPath);
				BigDecimal cashValue = new BigDecimal(getMoney);
				cashValue = cashValue.setScale(2, BigDecimal.ROUND_HALF_UP);
				policyCashValueResDTO.setCashValue(cashValue);
			}

		} catch (IOException e) {
			logger.error("保存报文发生异常：{}",e);
		} catch (DocumentException e) {
			throw new TradeException("解析报文时发生非预期异常!");
		}
		// 返回参数
		return policyCashValueResDTO;
	}

	/**
	 * 填充返回参数
	 * 
	 * @return
	 */
	private PolicyCashValueResDTO fillPolicyCashValueResDTO(PolicyCashValueReqDTO policyCashValueReqDTO) {
		PolicyCashValueResDTO policyCashValueResDTO = new PolicyCashValueResDTO();
		policyCashValueResDTO.setTransNo(policyCashValueReqDTO.getTransNo());
		policyCashValueResDTO.setContNo(policyCashValueReqDTO.getContNo());
		return policyCashValueResDTO;
	}

	/**
	 * 填充导核心DTO
	 * 
	 * @param policyCashValueReqDTO
	 * @return
	 */
	private SurrenderImportCoreDTO fillSurrenderImportCoreDTO(PolicyCashValueReqDTO policyCashValueReqDTO) {
		SurrenderImportCoreDTO surrenderImportCoreDTO = new SurrenderImportCoreDTO();
		surrenderImportCoreDTO.setBankCode(policyCashValueReqDTO.getBankCode());
		surrenderImportCoreDTO.setFunctionFlag(policyCashValueReqDTO.getFunctionFlag());
		surrenderImportCoreDTO.setContNo(policyCashValueReqDTO.getContNo());
		return surrenderImportCoreDTO;
	}

}
