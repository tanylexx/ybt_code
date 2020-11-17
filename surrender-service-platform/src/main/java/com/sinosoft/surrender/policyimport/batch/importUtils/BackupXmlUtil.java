package com.sinosoft.surrender.policyimport.batch.importUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sinosoft.surrender.common.contant.DateConstant;
import com.sinosoft.surrender.common.exception.TradeException;
import com.sinosoft.surrender.common.util.ConfigInfo;
import com.sinosoft.surrender.common.util.ExceptionUtil;
import com.sinosoft.surrender.policyimport.dto.SurrenderImportCoreDTO;

public class BackupXmlUtil {
	private static  Logger logger = LoggerFactory.getLogger(BackupXmlUtil.class);

	/**
	 * 
	 * 保存Xml报文
	 * 
	 * @history: 2018年3月29日
	 * @author: zhongdw_sinosoft
	 * @param surrenderDataDTO
	 * @param xml
	 * @param suffixName
	 */
	public static void backupXML(SurrenderImportCoreDTO surrenderImportCoreDTO, String xml, String suffixName) {
		OutputStream xmlFileOutputStream = null;
		try {
			// 银行编码
			String BankCode = surrenderImportCoreDTO.getBankCode();
			// 交易类型
			String FunFunctionFlag = surrenderImportCoreDTO.getFunctionFlag();
			// 获取当前日期
			DateFormat df = new SimpleDateFormat(DateConstant.DATE_FORMAT_4);
			String date = df.format(new Date());
			// 年
			String Year = date.substring(0, 4);
			// 年月
			String YearMonth = date.substring(0, 6);
			// 年月日
			String YearMonthDay = date.substring(0, 8);
			// 报文名称
			String fileName = surrenderImportCoreDTO.getContNo() + "_" + suffixName;
			// 从配置文件中拿到报文的存储路径
			Object address = ConfigInfo.getContextProperty("XML_PATH");
			// 校验
			ExceptionUtil.checkNull(address, "获取的报文保存地址为空！");
			String MessageXmlBackupPath = (String) address;
			// 生成目录结构
			String fileDir = MessageXmlBackupPath + File.separator + Year + File.separator + YearMonth + File.separator
					+ YearMonthDay + File.separator + BankCode.trim() + File.separator + FunFunctionFlag.trim();
			File filePath = new File(fileDir);
			if (!filePath.exists()) {
				filePath.mkdirs();
			}
			// 将生成的xml文档保存在目录结构中
			File file = new File(fileDir + File.separator + fileName);
			if (!file.exists()) {
				file.createNewFile();
			}
			xmlFileOutputStream = new FileOutputStream(file);
			IOUtils.write(xml.getBytes(), xmlFileOutputStream);
		} catch (Exception e) {
			logger.error("保存报文有误:{}",e);
			throw new TradeException();
		} finally {
			// 关流
			try {
				if (xmlFileOutputStream != null) {
					IOUtils.closeQuietly(xmlFileOutputStream);
				}
			} catch (Exception e) {
				logger.error("关流失败，烦请核实：{}！",e);
			}
		}
	}
}
