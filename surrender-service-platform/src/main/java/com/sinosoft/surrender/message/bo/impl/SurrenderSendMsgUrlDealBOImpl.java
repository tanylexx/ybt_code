package com.sinosoft.surrender.message.bo.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.sinosoft.surrender.common.contant.Constant;
import com.sinosoft.surrender.common.contant.XmlElementConstant;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_APP_TYPE;
import com.sinosoft.surrender.common.contant.surrenderenum.ENUM_TRACE_STATUS;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.DateUtil;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.message.batch.messageutils.BackupXmlUtil;
import com.sinosoft.surrender.message.bo.SurrenderSendMsgUrlDealBO;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrReqlDTO;
import com.sinosoft.surrender.message.dto.SurrenderSendMsgUrlResDTO;
import com.sinosoft.surrender.policyimport.batch.importUtils.CheckFailureUtil;
import com.sinosoft.surrender.policyimport.batch.importUtils.ImportCoreUtil;

@Component
public class SurrenderSendMsgUrlDealBOImpl implements SurrenderSendMsgUrlDealBO {
	private Logger logger = LoggerFactory.getLogger(getClass());
	@Override
	public SurrenderSendMsgUrlResDTO sendMsgUrl(SurrenderSendMsgUrReqlDTO surrenderSendMsgUrReqlDTO) {
		long startTime = System.currentTimeMillis();
		// DTO转成核心报文XML
		logger.info("====>组装调用核心短信链接请求xml<====");
		String reqXml = assembleRequestXml(surrenderSendMsgUrReqlDTO);
		logger.info("流水号为：{}，保单号为{}，导核心报文：{}", surrenderSendMsgUrReqlDTO.getTransNo(),
				surrenderSendMsgUrReqlDTO.getContNo(), reqXml);
		// 保存导入核心报文XML
		logger.info("====>保存核心短信链接请求xml<====");
		BackupXmlUtil.backupXML(surrenderSendMsgUrReqlDTO, reqXml, "_msgurl_req.xml");
		// 获得servlet的URL
		Object address = ConfigInfo.getContextProperty("ybtCoreAddress");
		// 校验
		ExceptionUtil.checkNull(address, "获取的核心servlet地址为空！");
		String conURL = (String) address;
		// 将xml通过流的形式导入核心，得到核心返回的报文xml
		logger.info("====>调用核心短信链接接口<====");
		String resXml;
		try {
			resXml = ImportCoreUtil.importCore(reqXml, conURL);
		} catch (IOException e) {
			logger.error("调用核心短信链接接口失败，失败原因：{}", e);
			throw new TradeException("调核心失败，失败原因：" + e.getMessage());
		}
		logger.info("调用核心短信链接接口的报文：{}", resXml);
		// 保存核心返回的报文xml
		logger.info("====>保存返回报文...<====");
		BackupXmlUtil.backupXML(surrenderSendMsgUrReqlDTO, resXml, "_msgurl_res.xml");
		// 解析返回报文
		SurrenderSendMsgUrlResDTO surrenderSendMsgUrlResDTO = parseResponseXml(resXml);
		long endTime = System.currentTimeMillis();
		logger.info("保单号为{},调用核心短信链接接口耗时：", surrenderSendMsgUrReqlDTO.getContNo(),endTime - startTime);
		return surrenderSendMsgUrlResDTO;
	}

	/**
	 * 
	 * 组装调用核心短信链接请求xml
	 *
	 * @history: 2019年12月10日
	 * @author: HASEE
	 * @param surrenderSendMsgUrReqlDTO
	 * @return
	 */
	private String assembleRequestXml(SurrenderSendMsgUrReqlDTO surrenderSendMsgUrReqlDTO) {
		Document doc = DocumentHelper.createDocument();
		Element TranData = doc.addElement(XmlElementConstant.TRAN_DATA);
		Element BaseInfo = TranData.addElement(XmlElementConstant.BASE_INFO);
		// 交易日期
		String transDate = DateUtil.getDateStr(surrenderSendMsgUrReqlDTO.getConfirmDate(), "yyyyMMdd");
		BaseInfo.addElement(XmlElementConstant.BANK_DATE).setText(transDate);
		//银行编码
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getBankCode(), "银行编码不能为空，请核实");
		BaseInfo.addElement(XmlElementConstant.BANK_CODE).setText(surrenderSendMsgUrReqlDTO.getBankCode().trim());
		//地区代码
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getZoneNo(), "地区代码不能为空，请核实");
		BaseInfo.addElement(XmlElementConstant.ZONE_NO).setText(surrenderSendMsgUrReqlDTO.getZoneNo().trim());
		//网点代码
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getBrNo(), "网点代码不能为空，请核实");
		BaseInfo.addElement(XmlElementConstant.BR_NO).setText(surrenderSendMsgUrReqlDTO.getBrNo().trim());
		// 交易流水号
		BaseInfo.addElement(XmlElementConstant.TRANSR_NO).setText(surrenderSendMsgUrReqlDTO.getTransNo());
		// 交易类型
		BaseInfo.addElement(XmlElementConstant.FUNCTION_FLAG).setText(surrenderSendMsgUrReqlDTO.getFunctionFlag());
		// 保险公司代码
		BaseInfo.addElement(XmlElementConstant.INSU_ID).setText(surrenderSendMsgUrReqlDTO.getInsuID()+"");
		// 柜员代码
		BaseInfo.addElement(XmlElementConstant.TELLER_NO).setText(surrenderSendMsgUrReqlDTO.getTellerNo() + "");
		Element EdorInfo = TranData.addElement(XmlElementConstant.EDOR_INFO);
		//保单号
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getContNo(), "保单号不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.CONT_NO).setText(surrenderSendMsgUrReqlDTO.getContNo());
		//保全受理号
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getEdoracceptno(), "保全受理号不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.EDOR_ACCEPT_NO).setText(surrenderSendMsgUrReqlDTO.getEdoracceptno());
		//保全受理日期
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getEdorAppDate(), "保全受理日期不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.EDORAPPDATE).setText(surrenderSendMsgUrReqlDTO.getEdorAppDate());
		//保全受理时间
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getEdorAppTime(), "保全受理时间不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.EDORAPPTIME).setText(surrenderSendMsgUrReqlDTO.getEdorAppTime());
		//保全类型
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getEdorType(), "保全类型不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.EDORTYPE).setText(surrenderSendMsgUrReqlDTO.getEdorType());
		// 申请方式 
		String appType = ENUM_APP_TYPE.getNameByValue(surrenderSendMsgUrReqlDTO.getSourceType().trim());
		ExceptionUtil.checkNull(appType, "申请方式不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.APPTYPE).setText(appType);
		ExceptionUtil.checkEmpty(surrenderSendMsgUrReqlDTO.getPhone(), "投保人手机号码不能为空，请核实");
		EdorInfo.addElement(XmlElementConstant.SEND_PHONE).setText(surrenderSendMsgUrReqlDTO.getPhone());
		return doc.asXML();
	}

	/**
	 * 解析调用核心短信发送链接返回报文
	 * 
	 * @author yangyb
	 * @date 2019年12月3日
	 * @param resXml
	 * @return
	 */
	private SurrenderSendMsgUrlResDTO parseResponseXml(String resXml) {
		SurrenderSendMsgUrlResDTO returnDTO = new SurrenderSendMsgUrlResDTO();
		// 检查报文xml，是否是错误结果格式，
		logger.debug("====>检查报文xml，是否是错误结果格式..<====");
		Map<String, String> failureMap = CheckFailureUtil.checkFailure(resXml);
		String rescode = failureMap.get("respcode");
		String resdesc = failureMap.get("respdesc");
		// 判断返回的报文是否是错误结果
		if (StringUtils.isNotBlank(rescode) && !Constant.ONE.equals(rescode)) {
			// 导核心失败
			logger.info("调用核心链接处理失败，失败信息为：{}",resdesc);
		   throw new TradeException(resdesc);
		}else {
			returnDTO.setResultStatus(ENUM_TRACE_STATUS.SUCCESS.getValue());
			returnDTO.setResultMsg("调用核心链接处理成功！");	
		}
		return returnDTO;

	}

}
